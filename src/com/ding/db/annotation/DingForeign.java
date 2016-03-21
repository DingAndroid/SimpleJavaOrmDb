package com.ding.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author ���¸�
 * @version 2016-3-8 ����1:54:50
 */
@Target({ElementType.FIELD,ElementType.TYPE}) //������Խ��
@Retention(RetentionPolicy.RUNTIME)
public @interface DingForeign {
	/**
	 * �����
	 * @return
	 */
	String column() default "";
	String foreign();
}
