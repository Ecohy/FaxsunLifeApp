package com.faxsun.moudle;

/**
 * 
 * @author hyy
 * ��Ʒ��ϸ��Ϣ
 *
 */
public class ProductItem {
	
	public int product_id;			//��ƷID
	public int product_item_id;			//producr_item id
//	public String URL;				//������Ʒ��ַ
	public float price;       		//��Ʒ�۸�
	public int size;				//��Ʒ�ͺ�
	public int color;				//��Ʒ��ɫ
//	public int product_num;			//��Ʒ�������
	
	
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

//	public String getURL() {
//		return URL;
//	}
//
//	public void setURL(String uRL) {
//		URL = uRL;
//	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	
	public ProductItem(){
		super();
	}
	
//	public Product(int id,String url,float p){
//		super();
//		product_id = id;
//		URL = url;
//		price = p;
//		
//	}

}
