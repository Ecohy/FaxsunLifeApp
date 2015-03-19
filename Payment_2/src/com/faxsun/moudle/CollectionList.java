package com.faxsun.moudle;

/*
 * 收藏列表
 */
public class CollectionList {
	
	public int collection_list_id;		//收藏列表id
	public int customer_id ;			//关联用户id

	public CollectionList(){
		super();
	}
	
	public int getCollection_list_id() {
		return collection_list_id;
	}
	public void setCollection_list_id(int collection_list_id) {
		this.collection_list_id = collection_list_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}	

}
