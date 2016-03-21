package com.ding.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author ���¸�
 * @version 2016-3-7 ����11:21:47
 * ������
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DingColumn {
	/**
	 * �ֶ���
	 * @return
	 */
	String name();
	
	/**
	 * �Ƿ�Ϊ����
	 * @return
	 */
	boolean isPRIMARYKEYId() default false; 
	
	/**
	 * �ֶ�����
	 * @return
	 */
	String type() default "char";
	
	/**
	 * �ֶγ���
	 * @return
	 */
	String length() default "300";
	/**
	 * �Ƿ�Ϊ��
	 * @return
	 */
	boolean isNotNull() default false; 
}
