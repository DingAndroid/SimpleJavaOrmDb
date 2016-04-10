package com.ding.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ding.db.c3p0.ConnectionManager;
import com.ding.db.constants.Constant;


/**
 * @author ���¸�
 * @version 2016-3-7 ����9:33:43
 * 
 * ���ݿ�������
 */
public class DBConn {
	
	public static Connection newInStanceConnection(){
		return getConn();
	}

	/**
	 * ���ݿ�����
	 * @return
	 */
	private static Connection getConn() {
//		Connection conn=null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(Constant.DB_URL, 
//					Constant.DB_USER_NAME, Constant.DB_USER_PASSWORD);
//			if (conn != null) {
//				System.out.println("���ݿ����ӳɹ�");
//			}
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return ConnectionManager.getInstance().getConnection();
	}
	
	/***
	 * �ر����ݿ�
	 */
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	}

}
