package com.faxsun.moudle;

/**
 * 
 * @author hyy
 * ��Ʒ
 *
 */
public class Product {
	
	public int product_id;			//��ƷID
	public int category_id;			//category id
	public String product_info;		//��Ʒ��Ϣ�����أ�ԭ�ϵ�
	public String name;				//��Ʒ����
	public String price;				//��Ʒ�۸�(������һ������)
//	public String URL;				//������Ʒ��ַ
//	public float price;       		//��Ʒ�۸�,ȷ����ɫ�ͺ�֮����м۸�
//	public int product_num;			//��Ʒ�������
	
	
	/*
	 * ��Ʒ�ͺ�
	 */
	public enum product_item_size{
		S,       
		M,		
		L,				
		XL,				
		XXL				
	}
	
	/*
	 * ��Ʒ��ɫ����
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
