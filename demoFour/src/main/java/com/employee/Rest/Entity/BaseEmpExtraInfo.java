package com.employee.Rest.Entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="baseEmpExtraInfo_tbl")
public class BaseEmpExtraInfo {
	

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "nick_name")
	private String nickName;	
	
	
	@OneToOne(mappedBy = "baseEmpExtraInfo") // this is reference from employee table
	private Employee employee; 
	
	



	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


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


	@Override
	public String toString() {
		return "BaseEmpExtraInfo [id=" + id + ", nickName=" + nickName + "]";
	}
	
	

	
	
	
	
	
	
}
