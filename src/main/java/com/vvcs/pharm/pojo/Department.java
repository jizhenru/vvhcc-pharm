package com.vvcs.pharm.pojo;

public class Department {
	private String Num;
	private String PatientName;
	private String Desc;
	private String Operation;
	public String getNum() {
		return Num;
	}
	public void setNum(String num) {
		Num = num;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
	}
	@Override
	public String toString() {
		return "Department [Num=" + Num + ", PatientName=" + PatientName + ", Desc=" + Desc + ", Operation=" + Operation
				+ "]";
	}
	
//	public String toString() {
//		return "{Num:" + Num + ", PatientName:" + PatientName + ", Desc:" + Desc + ", Operation:" + Operation
//				+ "}";
//	}
	
	
	
	
}
