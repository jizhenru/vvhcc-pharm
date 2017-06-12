package com.vvcs.pharm.pojo;

public class GenericNum {
	private String generic;
	private int num;
	public String getGeneric() {
		return generic;
	}
	public void setGeneric(String generic) {
		this.generic = generic;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "GenericNum [generic=" + generic + ", num=" + num + "]";
	}
}
