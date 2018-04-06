package com.boy.utils;

import java.util.HashMap;
import java.util.Map;

public class Msg {
//	״̬�� 200��ʾ�ɹ���500��ʾʧ��
	private Integer code;
//	��ʾ��Ϣ
	private String msg;
	
	private Map<String, Object> extend=new HashMap<String, Object>();
	
	public static Msg success() {
		Msg ms=new Msg();
		ms.setCode(200);
		ms.setMsg("����ɹ�");
		return ms;
	}
	
	public static Msg fail() {
		Msg ms=new Msg();
		ms.setCode(500);
		ms.setMsg("����ʧ��");
		return ms;
	}
	
	public Msg add(String key,Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	

}
