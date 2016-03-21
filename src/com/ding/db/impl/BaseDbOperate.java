package com.ding.db.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.ding.db.DbOperateIm;
import com.ding.db.util.ClassAnnotation;
import com.ding.db.util.DBConn;

/** 
 * @author ���¸�
 * @version 2016-3-7 ����3:24:33
 * 
 * ���ݿ������ʵ����
 */
public class BaseDbOperate implements DbOperateIm,Serializable{	
	public static Connection conn;
	
	public BaseDbOperate(Connection conn1){
		conn=conn1;
	}
	
	public BaseDbOperate(){}

	/**
	 * ��ѯ����
	 * @param field ��ѯ�ֶ�,���Ϊ�գ���ѯȫ������
	 * @return
	 */
	public <T> List<T> query(Class<T> t,String args[],String whereArgs[]){
		List<T> list=new ArrayList<T>();
		String tableName=ClassAnnotation.getInstance().getTableName(t);
	
		String sql="select * from "+tableName;
		if(args!=null){
			sql="select * from "+tableName+" where "+args[0]+" = "+whereArgs[0];
		}
		
		Map<String,Object> map=ClassAnnotation.getInstance().getFiledsRelation(t);
		
		/***������¼�������***/
		Map<String,Object> resultMap=null;
	
		Statement createStatement=null;
		ResultSet executeQuery=null;
		try {
			isDbConnection();
			createStatement = conn.createStatement();
			executeQuery= createStatement.executeQuery(sql);
			Set<Entry<String,Object>> entrySet = map.entrySet();
			while(executeQuery.next()){
				resultMap=new HashMap<String, Object>();
				for(Entry<String,Object> entry:entrySet){
					resultMap.put(entry.getKey(), executeQuery.getObject(entry.getKey()));
				}
				list.add((T) ClassAnnotation.getInstance().invoke(t, resultMap));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResult(executeQuery);
			closeStatement(createStatement);
		}
		return list;
	}
	
	/***
	 * ������߸�������
	 * @param t
	 * @return
	 */
	public <T> boolean insertOrUpdate(T t){
//		if(t==null || t.size() ==0){
//			return true;
//		}
		Class<?> cls=t.getClass();
		String tableName=ClassAnnotation.getInstance().getTableName(cls);
		
		//��ȡ��Ҫ�����sql���
		Map<String,Object> map=ClassAnnotation.getInstance().getFiledsRelationByBean(t);
		StringBuilder tableColumn=new StringBuilder("(");
		StringBuilder tableWildcard=new StringBuilder("values(");
		Set<Entry<String,Object>> entrySet = map.entrySet();
		int i=0;
		int size=map.size();
//		for(Entry<String,Object> entry:entrySet){
//			i++;
//			if(i != size){
//				tableColumn.append(entry.getKey()+",");
//				tableWildcard.append("?,");
//			}else{
//				tableColumn.append(entry.getKey()+")");
//				tableWildcard.append("?)");
//			}
//		}
		
		for(Entry<String,Object> entry:entrySet){
			i++;
			if(i != size){
				tableColumn.append(entry.getKey()+",");
				tableWildcard.append("'"+entry.getValue()+"'"+",");
			}else{
				tableColumn.append(entry.getKey()+")");
				tableWildcard.append("'"+entry.getValue()+"'"+")");
			}
		}
		String sql="insert into "+tableName+tableColumn.toString()+" "+tableWildcard.toString();
		PreparedStatement prepareStatement =null;
		try {
			isDbConnection();
			prepareStatement= conn.prepareStatement(sql);
			int executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closePreStatement(prepareStatement);
		}
		return true;
	}
	
	/**
	 * ����List��������
	 */
	public <T> boolean insertOrUpdateList(List<T> list) {
		// TODO Auto-generated method stub
		for(T t:list){
			if(!insertOrUpdate(t)){
				//ֻҪ��һ�����벻�ɹ���ֱ�ӷ���  Ƿȱ���ǣ����ݿ�ع�
				return false;
			}
		}
		return false;
	}
	
	public <T> boolean update(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> boolean delete(T t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * �ж������ǲ��Ǵ򿪣����û��ֱ�Ӵ�
	 * @return
	 */
	public boolean isDbConnection(){
		if(conn ==null){
			conn=DBConn.newInStanceConnection();
			return false;
		}
		return true;
	}
	
	
	/**
	 * �رս������
	 * @param set
	 */
	public void closeResult(ResultSet set){
		if(set!=null){
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				set=null;
			}
		}
	}
	
	
	/***
	 * �رղ�ѯ����
	 * @param statement
	 */
	public void closeStatement(Statement statement){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				statement=null;
			}
		}
	}
	
	
	/***
	 * �رղ�������
	 * @param statement
	 */
	public void closePreStatement(PreparedStatement statement){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				statement=null;
			}
		}
	}

	public <T> List<T> query(Class<T> t, String[] args, String[] whereArgs,
			String orderBy, String aes) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ִ��sql���
	 */
	public boolean execSql(String sql) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement =null;
		try {
			isDbConnection();
			prepareStatement= conn.prepareStatement(sql);
			return prepareStatement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closePreStatement(prepareStatement);
		}
	}
}
