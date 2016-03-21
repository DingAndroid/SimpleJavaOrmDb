package com.ding.db;


import com.ding.db.impl.BaseDbCreateTable;
import com.ding.db.util.DBConn;

/** 
 * @author ���¸�
 * @version 2016-3-7 ����2:29:55
 * 
 * ���ݿ������
 */
public class DBUtils extends BaseDbCreateTable{
	
	private static DBUtils db;
	
	//private DBUtils(){}
	private DBUtils(){
		super(DBConn.newInStanceConnection());
	}
	
	/***
	 * ������ݿ�ʵ��
	 * @return
	 */
	public static DBUtils newInstance(){
		if(db == null){
			db=new DBUtils();
		}
		return db;
	}
	
}
