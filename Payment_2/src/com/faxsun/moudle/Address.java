package com.faxsun.moudle;

/*
 * µØÖ·
 */
public class Address {
	
	public int address_id;
	public int customer_id;
	public String address_info;
	
	public Address(){
		super();
	}
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getAddress_info() {
		return address_info;
	}
	public void setAddress_info(String address_info) {
		this.address_info = address_info;
	}
}
