package com.faxsun.moudle;


/**
 * 
 * @author hyy
 * 用户类
 *
 */
public class Customer {
	
	public int customer_id;         //用户id
	public String email_address;		//用户名即邮箱地址
	public String password;			//用户密码
	public boolean is_registed;		//是否注册用户（1为已注册，0为未注册）
	public int orderlist_id;		//订单列表id	
	public int collectionlist_id;	//收藏列表id
	public int current_customer_id;
			
	public int getCurrent_customer_id() {
		return current_customer_id;
	}

	public void setCurrent_customer_id(int current_customer) {
		this.current_customer_id = current_customer;
	}

	public Customer(){
		super();
	}
	
	public int getOrderlist_id() {
		return orderlist_id;
	}

	public void setOrderlist_id(int orderlist_id) {
		this.orderlist_id = orderlist_id;
	}

	public int getCollectionlist_id() {
		return collectionlist_id;
	}

	public void setCollectionlist_id(int collectionlist_id) {
		this.collectionlist_id = collectionlist_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_registed() {
		return is_registed;
	}

	public void setIs_registed(boolean is_registed) {
		this.is_registed = is_registed;
	}		

}
