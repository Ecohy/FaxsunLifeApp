package com.faxsun.moudle;

/*
 * 购物车列表显示item
 */
public class OrderItem {
	
	private int id;		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public double getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(double retail_price) {
		this.retail_price = retail_price;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public int getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}

	private String name;
	private String img_url;
	private double retail_price;
	private double sale_price;
	private int item_quantity;	//购买数量

	public OrderItem(){
		
	}
	
	public OrderItem(int id,String name,String img_url,double retail,double sale,int q){
		this.id = id;
		this.name = name;
		this.img_url = img_url;
		this.retail_price = retail;
		this.sale_price = sale;
		this.item_quantity = q;
	}
		
}
