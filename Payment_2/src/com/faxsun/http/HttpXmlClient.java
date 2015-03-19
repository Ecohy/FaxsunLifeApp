package com.faxsun.http;  
  
import java.io.IOException;
  
import java.io.UnsupportedEncodingException;  
import java.net.SocketTimeoutException;
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.Set;  

import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.ParseException;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.methods.HttpUriRequest;  
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;  
import org.apache.http.util.EntityUtils;  

import android.util.Log;
  
  
public class HttpXmlClient {  
	
	private static final String TAG = "HttpXmlClient";
	private static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;
//    private static Logger log = Logger.getAnonymousLogger();  
      
    public static String post(String url, Map<String, String> params) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        // 设置ConnectionTimeout，定义了通过网络与服务器建立连接的超时时间，
        //Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间
        HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 5000);
        // 设置SocketTimeout，定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间
        HttpConnectionParams.setSoTimeout(httpclient.getParams(), 5000);
        
        String body = null;  
          
        Log.i(TAG,"create httppost:" + url);  
        HttpPost post = postForm(url, params);  
          
        body = invoke(httpclient, post);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
    
    public static String post(String url) {  
    	Log.i(TAG,"create post no form:" + url);
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        // 设置ConnectionTimeout，定义了通过网络与服务器建立连接的超时时间，
        //Httpclient包中通过一个异步线程去创建与服务器的socket连接，这就是该socket连接的超时时间
        HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 5000);
        // 设置SocketTimeout，定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间
        HttpConnectionParams.setSoTimeout(httpclient.getParams(), 5000);
        
        String body = null;  
          
        Log.i(TAG,"create httppost:" + url);  
        HttpPost post = new HttpPost(url);  
          
        body = invoke(httpclient, post);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }
    
   //           JSONObject jsonObj = new JSONObject();             jsonObj.put( "website" , "http://www.dutycode.com" );             jsonObj.put( "email" , "dutycode@gmail.com" );                          StringEntity entity = new StringEntity(jsonObj.toJSONString());             entity.setContentEncoding( "UTF-8" );             entity.setContentType( "application/json" );//设置为 json数据                          post.setEntity(entity);                          HttpResponse response = client.execute(post);                          HttpEntity resEntity = response.getEntity();             String res = EntityUtils. toString(resEntity);                          System. out .println(res);       }
      
    public static String get(String url) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
          
        Log.i(TAG,"create get:" + url);  
        HttpGet get = new HttpGet(url);  
        body = invoke(httpclient, get);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
    
    public String delete(String url) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
          
        Log.i(TAG,"create delete:" + url);  
        HttpDelete delete = new HttpDelete(url);  
        body = invoke(httpclient, delete);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    } 
          
      
    private static String invoke(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
          
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
          
        return body;  
    }  
  
    private static String paseResponse(HttpResponse response) {  
    	Log.i(TAG,"get response from http server..");
    	String body = null; 
    	try { 
        HttpEntity entity = response.getEntity();  

        Log.e(TAG,"response status: " + response.getStatusLine());  
        String charset = EntityUtils.getContentCharSet(entity);  
        Log.e(TAG,charset);          
            body = EntityUtils.toString(entity);  
            Log.i(TAG,body);  
        }
        catch (ConnectTimeoutException e)
        {
            // 捕获ConnectionTimeout
            Log.d(TAG,"与服务器建立连接超时");
        }catch (SocketTimeoutException e){
            // 捕获SocketTimeout
            Log.d("err", "从服务器获取响应数据超时");
        }catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return body;  
    }  
  
    private static HttpResponse sendRequest(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
 //   	Log.i(TAG,"execute post...");  
        HttpResponse response = null;  
          
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
  
    private static HttpPost postForm(String url, Map<String, String> params){  
          
        HttpPost httpost = new HttpPost(url);  
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();  
          
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
            nvps.add(new BasicNameValuePair(key, params.get(key)));  
        }  
          
        try {  
        	Log.i(TAG,"set utf-8 form entity to httppost");  
 //       	httpost.setHeader("Accept", "application/json");  
 //       	httpost.setHeader("Content-Type", "application/json"); 
        	httpost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8)); 

        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
          
        return httpost;  
    }
 
} 