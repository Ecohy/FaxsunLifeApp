package com.faxsun.moudle;


/**
 * 
 * @author hyy
 * ���ﳵ
 *
 */
public class OrderList {
	
	public int orderlist_id;		//���ﳵid
	public int customer_id ;		//���ﳵ�����û�id
	public int product_num;			//���ﳵ����Ʒ��������
//	public int product_id;			//���ﳵ����Ʒid
	
	public OrderList(){
		super();
	}
	
	public int getOrderlist_id() {
		return orderlist_id;
	}
	public void setOrderlist_id(int orderlist_id) {
		this.orderlist_id = orderlist_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}	
}
