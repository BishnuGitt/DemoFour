package com.employee.Rest.CollectionDTO;

import java.math.BigInteger;

public class BaseExtraInfoDTO {

	private int id;
	private String nickName; // if commented, this field will not appear in Postman 


//	public BaseEmpExtraInfoDTO() {
//		this.id = new BigInteger("34"); 
//		this.nickName = "Rithik"; 
//	}
//	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
