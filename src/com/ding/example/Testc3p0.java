package com.ding.example;

//�������ݿ����ӳ�
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ding.db.c3p0.ConnectionManager;

public class Testc3p0 {

	public Testc3p0() {
		// TODO �Զ����ɹ��캯�����
	}

	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		ConnectionManager cm = ConnectionManager.getInstance();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from logs";
		for(int i=0;i<10000;i++){
			try {
				conn = cm.getConnection();
				System.out.println("���ݿ����ӳɹ�....");
				System.out.println(conn);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				System.out.println(rs.getString(1));

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
					}
				}

				if (stmt != null) {
					try {
						stmt.close();
					} catch (Exception e) {
					}
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}

			try {
				System.out.println(cm.ds.toString());
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
