package com.faxsun.moudle;

/*
 * �ᱦҳ��Item��ʾ
 */
public class RaidersCategoryItem extends CategoryItem{
	
	

	public RaidersCategoryItem(int id,int img_id, String n, String p) {
//		super(id,img_id, n, p);
		// TODO Auto-generated constructor stub
	}

	public int total_buyers;	//Ԥ���ܹ�������
	public int already_buyer;		//�ѹ�������
	
	
	
	public int getTotal_buyers() {
		return total_buyers;
	}

	public void setTotal_buyers(int total_buyers) {
		this.total_buyers = total_buyers;
	}

	public int getAlready_buyer() {
		return already_buyer;
	}

	public void setAlready_buyer(int already_buyer) {
		this.already_buyer = already_buyer;
	}

}
