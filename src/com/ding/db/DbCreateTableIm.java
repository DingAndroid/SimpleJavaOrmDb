package com.ding.db;
/** 
 * @author ���¸�
 * @version 2016-3-8 ����1:29:09
 * 
 * ���ݿ���� ������Ľӿ�
 */
public interface DbCreateTableIm {
	
	/**
	 * ������ע�ⴴ����
	 * @param cls
	 * @return
	 */
	public <T>boolean CreateTable(Class<T> cls);
	
	public <T>String getCreateTableSql(Class<T> cls);
	
	/**
	 * ɾ����
	 * @param cls
	 * @return
	 */
	public <T>boolean dropTable(Class<T> cls);
	
}
