package com.ding.example;

import com.ding.db.DBUtils;

import sun.font.CreatedFontTracker;

/**
 * �������ͳһ����
 * @author ���¸�
 *	2016��3��21��14:26:54
 */
public class TestCreateTableDemo {
	
	
	public static void main(String[] args) {
		createTable();
	}

	/**
	 * �������ͳһ����
	 */
	private static void createTable() {
		// TODO Auto-generated method stub
		boolean createTable = DBUtils.newInstance().CreateTable(TestBean.class);
		if(createTable){
			System.out.println("������TestBean�ɹ�.....");
		}
	}
	
}
