package com.faxsun.moudle;

/*
 * �û��ղ��¼�
 */
public class CollectionItem {
	
	public int collection_item_id;		//����ID
	public int collection_list_id;		//�����б�ID
	public int product_id;        //��ƷID
	public int product_item_id;			//��ɫ�ͺ�ID
	public String date;			//����ʱ��
	
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
