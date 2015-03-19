package com.faxsun.controller;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.faxsun.util.LruBitmapCache;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

 
public class AppController extends Application {
 
    public static final String TAG = AppController.class.getSimpleName();
 
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
 
    private static AppController mInstance;
 
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
 
    public static synchronized AppController getInstance() {
        return mInstance;
    }
 
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
 
        return mRequestQueue;
    }
 
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }
 
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
 
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
 
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    
    /**
	 * 功能：检查当前是否有网络
	 * 
	 * */
	public static boolean isNetworkAvailable(Context context) { 
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		if (connectivity == null) { 
			Log.i("NetWorkState", "Unavailabel"); 
			return false; 
			} else { 
				NetworkInfo[] info = connectivity.getAllNetworkInfo(); 
				if (info != null) { 
					for (int i = 0; i < info.length; i++) { 
						if (info[i].getState() == NetworkInfo.State.CONNECTED) { 
							Log.i("NetWorkState", "Availabel");
							return true; 
						} 
					} 
				} 
			} 
			return false; 
		}
	
	public void showToast(Context context,String string) {
		// TODO Auto-generated method stub
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}
}
