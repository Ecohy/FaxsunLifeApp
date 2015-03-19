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
	public static int current_customer_id = 0;	//当前用户
	public static String current_customer_username = "0000";	//当前用户名称
	public static String SUCCESS = "SUCCESS"; //成功返回

	private static HttpXmlClient httpClient = new HttpXmlClient();
	
	
	/*
	 * 功能：根据参数catogory_id，返回商品列表List<RaidersCategoryItem>
	 */
	public static List<RaidersCategoryItem> getRaidersArrayListFromServer(int catogory_id,List<RaidersCategoryItem> l) 
	{
		//TODO 调用服务器端接口，返回夺宝商品列表
		return l;
		
	}
	
	/*
	 * 功能：根据参数catogory_id，返回商品列表List<CategoryItem>
	 */
	public static List<CategoryItem> getArrayListFromServer(int catogory_id,List<CategoryItem> l) 
	{
		//TODO 调用服务器端接口，返回商品列表
		return l;
		
	}
	
	/*
	 * 搜索函数：根据用户输入search_str，返回商品列表List<CategoryItem>
	 */
	public static List<CategoryItem> getArrayListFromServer(String search_str,List<CategoryItem> l) 
	{
		//TODO 调用服务器端接口，返回商品列表
		return l;
		
	}
	
	/*
	*排序函数
	*提供参数：order_type（排序关键字，价格由低到高，销量由高到低，商品来源地等）
	*返回：商品列表List<CategoryItem>
	*/
	
	public static List<CategoryItem> rankList(int order_type,List<CategoryItem> l) 
	{
		//TODO 调用服务器端接口，返回商品列表
		return l;
		
	}

	
	/*
	*获取用户购物车函数
	*提供参数：customer_id用户id
	*返回：购物车列表
	*/
	
	public static List<CategoryItem> orderList(int customer_id,List<CategoryItem> l) 
	{
		//TODO 调用服务器端接口，返回商品列表
		return l;
		
	}

	
	/*
	*获取用户收藏函数
	*提供参数：customer_id用户id
	*返回：收藏列表
	*/
	
	public static List<CategoryItem> collectionList(int customer_id,List<CategoryItem> l) 
	{
		//TODO 调用服务器端接口，返回商品列表
		return l;
		
	}
	
	/*
	*生成一笔订单
	*提供参数：customer_id用户id，product_id,商品id，product_item_id型号id,product_quantity购买数量
	*返回：订单生成成功返回true,否则返回false
	*/
	
	public static boolean addOrderItem(int customer_id,int order_item_id) 
	{
		//TODO 调用服务器端接口，返回商品列表
		return true ;
		
	}
/*
 * 登录函数
 * 参数说明:username 用户名
 * 			password 用户密码
 */

	public static String login(String username, String password) {
		Log.i(TAG,"login");
		//调用url访问
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
	 * 登录重置解析返回response	
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
 * 密码重置解析返回response	
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
	 * 注册函数
	 * 参数说明:username 用户名
	 * 			password 用户密码
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
	 * 密码重置
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
	 * 获取当前用户购物车信息
	 */
	public static String getCartInfo() {
		Log.i(TAG,"getCartInfo");
		
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();  
		params.add(new BasicNameValuePair("customerId",String.valueOf(current_customer_id))); 
		//对参数编码  
		String param = URLEncodedUtils.format(params, "UTF-8"); 
		//将URL与参数拼接  
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
		
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}

	/*
	 * 获取商品信息
	 */
	public String getProductInfo(String product_id) {
		Log.e(TAG, "getProductInfo:url = "+productUrl+product_id);
		String response = httpClient.get(productUrl+product_id);
		Log.e(TAG,"getProductInfo:response = "+ response);
		return response;
	}
	
	/*
	 * 获取商品SKU信息
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
	 * 添加到购物车
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
		
		//判断购物车是否存在
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
				//新增购物车
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
