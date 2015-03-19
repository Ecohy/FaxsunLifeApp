package com.faxsun.moudle;

/*
 * 首页用于显示的每一个Item
 */
public class CategoryItem {
	
	public int id;				//商品id
	public String img_url;					//商品图片
	public int category_id_type;		//分类id
	public String name;					//商品名称
	public double retail_price;				//商品价格
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

	public double sale_price;
	
	public int getId() {
		return id;
	}

	public void setId(int product_id) {
		this.id = product_id;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public int getCategory_id_type() {
		return category_id_type;
	}

	public void setCategory_id_type(int category_id_type) {
		this.category_id_type = category_id_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		
	
	public CategoryItem(){
		super();

	}
	
	public CategoryItem(int product_id,String img_url,String n,double retail_p,double sale_p){
		super();
		this.id = product_id;
		this.img_url = img_url;
		this.name = n;
		this.retail_price = retail_p;
		this.sale_price = sale_p;
	}
}
