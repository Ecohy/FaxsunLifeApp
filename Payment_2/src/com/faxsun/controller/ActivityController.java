package com.faxsun.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.faxsun.http.HttpXmlClient;
import com.faxsun.moudle.Category.catogory_id_type;
import com.faxsun.moudle.CategoryItem;
import com.faxsun.moudle.RaidersCategoryItem;

public class ActivityController {
	
	private static String productUrl="http://192.168.1.57:8080/faxsun/api/v1/catalog/product/";
	private static String categoryUrl="http://192.168.1.57:8080/faxsun/api/v1/catalog/category/";
	private static String loginUrl="http://192.168.1.57:8080/faxsun/api/v1/fsResource/login";
	private static String registerUrl="http://192.168.1.57:8080/faxsun/api/v1/fsResource/register";
	private static String resetPasswordUrl="http://192.168.1.57:8080/faxsun/api/v1/fsResource/resetPassword";
	private static String cartUrl="http://192.168.1.57:8080/faxsun/api/v1/cart";
	private static String TAG = "ActivityController";
	public static int current_customer_id = 0;	//��ǰ�û�
	public static String current_customer_username = "0000";	//��ǰ�û�����
	public static String SUCCESS = "SUCCESS"; //�ɹ�����

	private static HttpXmlClient httpClient = new HttpXmlClient();
	
	
	/*
	 * ���ܣ����ݲ���catogory_id��������Ʒ�б�List<RaidersCategoryItem>
	 */
	public static List<RaidersCategoryItem> getRaidersArrayListFromServer(int catogory_id,List<RaidersCategoryItem> l) 
	{
		//TODO ���÷������˽ӿڣ����ضᱦ��Ʒ�б�
		return l;
		
	}
	
	/*
	 * ���ܣ����ݲ���catogory_id��������Ʒ�б�List<CategoryItem>
	 */
	public static List<CategoryItem> getArrayListFromServer(int catogory_id,List<CategoryItem> l) 
	{
		//TODO ���÷������˽ӿڣ�������Ʒ�б�
		return l;
		
	}
	
	/*
	 * ���������������û�����search_str��������Ʒ�б�List<CategoryItem>
	 */
	public static List<CategoryItem> getArrayListFromServer(String search_str,List<CategoryItem> l) 
	{
		//TODO ���÷������˽ӿڣ�������Ʒ�б�
		return l;
		
	}
	
	/*
	*������
	*�ṩ������order_type������ؼ��֣��۸��ɵ͵��ߣ������ɸߵ��ͣ���Ʒ��Դ�صȣ�
	*���أ���Ʒ�б�List<CategoryItem>
	*/
	
	public static List<CategoryItem> rankList(int order_type,List<CategoryItem> l) 
	{
		//TODO ���÷������˽ӿڣ�������Ʒ�б�
		return l;
		
	}

	
	/*
	*��ȡ�û����ﳵ����
	*�ṩ������customer_id�û�id
	*���أ����ﳵ�б�
	*/
	
	public static List<CategoryItem> orderList(int customer_id,List<CategoryItem> l) 
	{
		//TODO ���÷������˽ӿڣ�������Ʒ�б�
		return l;
		
	}

	
	/*
	*��ȡ�û��ղغ���
	*�ṩ������customer_id�û�id
	*���أ��ղ��б�
	*/
	
	public static List<CategoryItem> collectionList(int customer_id,List<CategoryItem> l) 
	{
		//TODO ���÷������˽ӿڣ�������Ʒ�б�
		return l;
		
	}
	
	/*
	*����һ�ʶ���
	*�ṩ������customer_id�û�id��product_id,��Ʒid��product_item_id�ͺ�id,product_quantity��������
	*���أ��������ɳɹ�����true,���򷵻�false
	*/
	
	public static boolean addOrderItem(int customer_id,int order_item_id) 
	{
		//TODO ���÷������˽ӿڣ�������Ʒ�б�
		return true ;
		
	}
/*
 * ��¼����
 * ����˵��:username �û���
 * 			password �û�����
 */

	public static String login(String username, String password) {
		Log.i(TAG,"login");
		//����url����
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		String response = httpClient.post(loginUrl,params); 
		Log.i(TAG,response);
		String body = null;
		body = loginPraseResponse(response);
		return body;
	}
	
	/*
	 * ��¼���ý�������response	
	 */

		private static String loginPraseResponse(String response) {
			int get_customer_id = 0;
			String get_customer_username = null;
			String login_message = null;
			try {
				JSONObject json = new JSONObject(response);
				if(json.has("id")){
					get_customer_id = json.getInt("id");
					get_customer_username = (String) json.get("username");
					Log.i(TAG, "get_customer_id="+get_customer_id);
					current_customer_id = get_customer_id;
					current_customer_username = get_customer_username;
					login_message = SUCCESS;
				}else{
					JSONArray jsonArray = new JSONArray();
					jsonArray = json.getJSONArray("messages");
					JSONObject jsonObject = (JSONObject)jsonArray.get(0);
//					String long_message = jsonArray.toString();
					login_message = (String) jsonObject.get("messageKey");
	//				login_message = long_message.substring(long_message.lastIndexOf(".") + 1); 
	//				Log.i(TAG, "long_message="+long_message);
					Log.i(TAG, "login_message="+login_message);
					}						
				} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return login_message;		
		}
		
	
/*
 * �������ý�������response	
 */

	private static String praseResponse(String response) {
		int get_customer_id = 0;
		String login_message = null;
		try {
			JSONObject json = new JSONObject(response);
			if(json.has("id")){
				get_customer_id = json.getInt("id");
				Log.i(TAG, "get_customer_id="+get_customer_id);
				login_message = SUCCESS;
			}else{
				JSONArray jsonArray = new JSONArray();
				jsonArray = json.getJSONArray("messages");
				JSONObject jsonObject = (JSONObject)jsonArray.get(0);
//				String long_message = jsonArray.toString();
				login_message = (String) jsonObject.get("message");
//				login_message = long_message.substring(long_message.lastIndexOf(".") + 1); 
//				Log.i(TAG, "long_message="+long_message);
				Log.i(TAG, "login_message="+login_message);
				}						
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return login_message;		
	}
	
	/*
	 * ע�ắ��
	 * ����˵��:username �û���
	 * 			password �û�����
	 */
	public static String register(String username, String password) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		String response = HttpXmlClient.post(registerUrl,params); 
		Log.i(TAG,response);
		String body = null;
		body = praseResponse(response);
		return body;
	}
	
	/*
	 * ��������
	 */
	public static String reset_password(String user,String pass){
		String extracted = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", user);
		params.put("newPassword", pass);
		String response = HttpXmlClient.post(resetPasswordUrl,params); 
		Log.i(TAG,response);
		String body = null;
		body = praseResponse(response);
		return body;
	}

	/*
	 * ��ȡ��ǰ�û����ﳵ��Ϣ
	 */
	public static String getCartInfo() {
		Log.i(TAG,"getCartInfo");
		
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();  
		params.add(new BasicNameValuePair("customerId",String.valueOf(current_customer_id))); 
		//�Բ�������  
		String param = URLEncodedUtils.format(params, "UTF-8"); 
		//��URL�����ƴ��  
//		HttpGet getMethod = new HttpGet(cartUrl + "?" + param); 
		String response = HttpXmlClient.get(cartUrl + "?" + param); 
		Log.e(TAG, response);		
		return response;
	}

	public String getCategoryList(catogory_id_type c) {		
		Log.i(TAG,"getCartInfo");
		String url = null;
		switch(c){
		case FIRST_PAGE:
			url = categoryUrl+"2001";
			break;
		case MUM_AND_BABY:
			url = categoryUrl+"2002";
			break;
		case NOVEL:
			url = categoryUrl+"2003";
			break;
		case HEALTH:
			url = categoryUrl+"2004";
			break;
		case RAIDERS:
			url = categoryUrl+"9950";
			break;
		}
		String response = HttpXmlClient.get(url);
		Log.i(TAG, response);
		return response;
	}
	
	/**
	 * ���ܣ���鵱ǰ�Ƿ�������
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
		
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}

	/*
	 * ��ȡ��Ʒ��Ϣ
	 */
	public String getProductInfo(String product_id) {
		Log.e(TAG, "getProductInfo:url = "+productUrl+product_id);
		String response = httpClient.get(productUrl+product_id);
		Log.e(TAG,"getProductInfo:response = "+ response);
		return response;
	}
	
	/*
	 * ��ȡ��ƷSKU��Ϣ
	 */
	public String getSkuInfo(String product_id) {
		Log.e(TAG, "getSkuInfo:url = "+productUrl+product_id);
		String response = httpClient.get(productUrl+product_id+"/skus");
		Log.e(TAG,"getSkuInfo:response = "+ response);
		return response;
	}

	public void deleteCartItem(int id) {
		
		Log.e(TAG, "deleteCartItem"+String.valueOf(id));
		String url = cartUrl+"/items/"+String.valueOf(id)+"?customerId="+current_customer_id;
		String response = httpClient.delete(url);
		Log.e(TAG, "response = "+response);
	}

	
	/*
	 * ��ӵ����ﳵ
	 */
	public String addToCart(String product_id, String category_id, int i, boolean b) {
		
		Log.i(TAG,"addToCart");
		String HTTP_CART_NOT_FOUND = "cartNotFound";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("productId", product_id);
		params.put("categoryId", category_id);		
		params.put("quantity", "1");
		params.put("priceOrder", "true");
//		String param = URLEncodedUtils.format(params, "UTF-8"); 
		String url = cartUrl+"/"+product_id+"?customerId="+current_customer_id;
		String response = null;
		response = HttpXmlClient.post(url,params);
		
		//�жϹ��ﳵ�Ƿ����
		JSONObject json;
		try {
			json = new JSONObject(response);
		
		if(json.has("id")){
			return response;
		}
		else{
			JSONArray jsonArray = json.getJSONArray("messages");
			JSONObject jo = (JSONObject) jsonArray.get(0);
			String str = jo.getString("messageKey");
			if(str.equalsIgnoreCase(HTTP_CART_NOT_FOUND)){
				//�������ﳵ
				String create = createCart();
				JSONObject create_json = new JSONObject(create);
				String cus = create_json.getJSONObject("customer").getString("id");
				
				String new_url = cartUrl+"/"+product_id+"?customerId="+cus;
				String new_response = null;
				new_response = HttpXmlClient.post(new_url,params);
				
				
			}
			
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	private String createCart() {

		Log.i(TAG,"createCart");
		String url = cartUrl+"/";
		String response = null;
		response = HttpXmlClient.post(url);
		return response;
	}
		
}
