package com.faxsun.moudle;

/**
 * 
 * @author hyy
 * 商品详细信息
 *
 */
public class ProductItem {
	
	public int product_id;			//商品ID
	public int product_item_id;			//producr_item id
//	public String URL;				//访问商品地址
	public float price;       		//商品价格
	public int size;				//商品型号
	public int color;				//商品颜色
//	public int product_num;			//商品库存数量
	
	
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
