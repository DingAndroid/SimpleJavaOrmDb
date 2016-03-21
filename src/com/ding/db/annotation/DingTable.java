package com.ding.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author ���¸�
 * @version 2016-3-7 ����11:14:15
 *
 * ���� �������༶��
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DingTable {
	/**
	 * ����
	 * @return
	 */
	String name();
}
