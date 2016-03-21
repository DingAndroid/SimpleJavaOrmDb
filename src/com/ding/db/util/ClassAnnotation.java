package com.ding.db.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.ding.db.annotation.DingColumn;
import com.ding.db.annotation.DingTable;


/** 
 * @author ���¸�
 * @version 2016-3-7 ����3:34:54
 */
public class ClassAnnotation{
	
	private static ClassAnnotation ann;
	
	public static ClassAnnotation getInstance(){
		if(ann==null){
			ann=new ClassAnnotation();
		}
		return ann;
	}
	
	
	/**
	 * ͨ��������ȡ����
	 * @param cls
	 * @return
	 */
	public <T>String getTableName(Class<T> cls){
		DingTable annotation = cls.getAnnotation(DingTable.class);
		return annotation.name();
	}
	
	/**
	 * ��ȡ�ֶ����� ��ע��ı��ֶ�
	 * @return
	 */
	public <T> Map <String,Object> getFiledsRelation(Class<T> cls){
		Map<String,Object> map=new HashMap<String, Object>();
		//T t= ClassNewInstance.getInstance().getInstanceByClass(cls);
		Field[] declaredFields = cls.getDeclaredFields();
		for(Field f:declaredFields){
			DingColumn annotation = f.getAnnotation(DingColumn.class);
			f.setAccessible(true);
			if(annotation !=null)
				map.put(annotation.name(),f.getName());
		}
		return map;
	}
	

	/**
	 * ��ȡ�ֶ����� ��ע���ֵ
	 * @return
	 */
	public <T> Map <String,Object> getFiledsRelationByBean(T t){
		Map<String,Object> map=new HashMap<String, Object>();
		//T t= ClassNewInstance.getInstance().getInstanceByClass(cls);
		Field[] declaredFields = t.getClass().getDeclaredFields();
		for(Field f:declaredFields){
			DingColumn annotation = f.getAnnotation(DingColumn.class);
			f.setAccessible(true);
			if(annotation !=null){
				try {
					map.put(annotation.name(),f.get(t));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	
	/***
	 * ע������ֵ
	 * @param cls
	 * @return
	 */
	public <T> T invoke(Class<T> cls,Map<String,Object> map){
		T t= ClassNewInstance.getInstance().getInstanceByClass(cls);
		Field[] declaredFields = cls.getDeclaredFields();
		for(Field f:declaredFields){
			DingColumn annotation = f.getAnnotation(DingColumn.class);
			if(annotation !=null){
				f.setAccessible(true);
				try {
					f.set(t, map.get(annotation.name()));
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return t;
	}
}
