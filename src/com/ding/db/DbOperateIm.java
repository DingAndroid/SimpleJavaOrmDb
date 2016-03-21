package com.ding.db;

import java.util.List;

/** 
 * @author ���¸�
 * @version 2016-3-7 ����2:59:44
 * 
 * ���ݿ�����ӿ�
 */
public interface DbOperateIm{
	/**
	 * ��ѯ����
	 * @param t 
	 * @param args ��ѯ��������
	 * @param whereArgs ��ѯ�����Ķ���
	 * @return
	 */
	public <T> List<T> query(Class<T> t,String args[],String whereArgs[]);
	
	/**
	 * ��ѯ����
	 * @param t
	 * @param args 
	 * @param whereArgs
	 * @param orderBy ��������
	 * @param aes �Ƿ�����
	 * @return
	 */
	public <T> List<T> query(Class<T> t,String args[],String whereArgs[],String orderBy,String aes);
	
	/**
	 * ������߸�������
	 * @param t Ҫ������bean
	 * @return �Ƿ�ɹ�
	 */
	public <T> boolean insertOrUpdate(T t);
	
	/**
	 * ������߸������� List����ʽ��
	 * @param t Ҫ������bean
	 * @return �Ƿ�ɹ�
	 */
	public <T> boolean insertOrUpdateList(List<T> t);
	
	/**
	 * ���²���
	 * @param t Ҫ������bean
	 * @return
	 */
	public <T> boolean update(T t);
	
	
	/**
	 * ���ݿ�ɾ��
	 * @param t Ҫ������bean
	 * @return
	 */
	public <T> boolean delete(T t);
	
	/**
	 * ֱ��ִ��sql ��� ��Ҫ���ؽ���Ĵ�����
	 * @param sql
	 * @return
	 */
	public boolean execSql(String sql);

}
