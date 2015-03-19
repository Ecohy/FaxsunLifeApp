package com.faxsun.moudle;

/**
 * 
 * @author hyy
 * 商品
 *
 */
public class Product {
	
	public int product_id;			//商品ID
	public int category_id;			//category id
	public String product_info;		//商品信息，产地，原料等
	public String name;				//商品名称
	public String price;				//商品价格(可能是一个区间)
//	public String URL;				//访问商品地址
//	public float price;       		//商品价格,确定颜色型号之后才有价格
//	public int product_num;			//商品库存数量
	
	
	/*
	 * 商品型号
	 */
	public enum product_item_size{
		S,       
		M,		
		L,				
		XL,				
		XXL				
	}
	
	/*
	 * 商品颜色分类
	 */
	public enum product_item_color{
		WHITE,       
		BLACK,		
		RED,				
		BLUE,				
		GREEN,				
		YELLOW
	}		
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}	
	
	public String getProduct_info() {
		return product_info;
	}

	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}
	
	public Product(){
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
