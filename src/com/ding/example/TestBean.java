package com.ding.example;

import java.io.Serializable;

import com.ding.db.annotation.DingColumn;
import com.ding.db.annotation.DingTable;

/**
 * ����ʵ����
 * @author ���¸� 2016��3��21��14:24:48
 *
 */
@DingTable(name="test")
public class TestBean implements Serializable{
	@DingColumn(name="id")
	private String id;
	//�û���
	@DingColumn(name="id")
	private String name;
	//�û�����
	@DingColumn(name="id")
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", name=" + name + ", password="
				+ password + "]";
	}
	
}
