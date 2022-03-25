package com.employee.Rest.EnumCollection;

public enum EmployeeEnum {
	
	
	MIN_WORK_AGE("MWA", Constants.MIN_WORK_AGE), 
	
	STANDARD_HOUR("SH", Constants.STANDARD_HOUR), 
	
	ROLE ("R", Constants.ROLE),
	
	PAY_PERIOD("PP", Constants.PAY_PERIOD); 



	private String abbreviation;
	private String fullFormValue; // this field are only for converting to the data type in STRING  or int
	
	private double doubleValueAbbreviation; 
	
	
	
	
	private EmployeeEnum(String abbreviation, String fullFormValue) {
		this.abbreviation = abbreviation;
		this.fullFormValue = fullFormValue;
	}

	
	private EmployeeEnum(String abbreviation, double doubleValueAbbreviation) {
		this.abbreviation = abbreviation;
		this.doubleValueAbbreviation = doubleValueAbbreviation;
	}


	public double getDoubleValueAbbreviation() {
		return doubleValueAbbreviation;
	}
	public void setDoubleValueAbbreviation(double doubleValueAbbreviation) {
		this.doubleValueAbbreviation = doubleValueAbbreviation;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getFullFormValue() {
		return fullFormValue;
	}
	public void setFullFormValue(String fullFormValue) {
		this.fullFormValue = fullFormValue;
	}
	
	
	
	
	


	

}
