package com.faxsun.moudle;


/**
 * 
 * @author hyy
 * �û���
 *
 */
public class Customer {
	
	public int customer_id;         //�û�id
	public String email_address;		//�û����������ַ
	public String password;			//�û�����
	public boolean is_registed;		//�Ƿ�ע���û���1Ϊ��ע�ᣬ0Ϊδע�ᣩ
	public int orderlist_id;		//�����б�id	
	public int collectionlist_id;	//�ղ��б�id
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
