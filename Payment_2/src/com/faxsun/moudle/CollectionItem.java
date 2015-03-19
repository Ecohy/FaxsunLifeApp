package com.faxsun.moudle;

/*
 * 用户收藏事件
 */
public class CollectionItem {
	
	public int collection_item_id;		//订单ID
	public int collection_list_id;		//订单列表ID
	public int product_id;        //商品ID
	public int product_item_id;			//颜色型号ID
	public String date;			//生成时间
	
	public CollectionItem(){
		super();
	}
	public int getCollection_item_id() {
		return collection_item_id;
	}
	public void setCollection_item_id(int collection_item_id) {
		this.collection_item_id = collection_item_id;
	}
	public int getCollection_list_id() {
		return collection_list_id;
	}
	public void setCollection_list_id(int collection_list_id) {
		this.collection_list_id = collection_list_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_item_id() {
		return product_item_id;
	}
	public void setProduct_item_id(int product_item_id) {
		this.product_item_id = product_item_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
