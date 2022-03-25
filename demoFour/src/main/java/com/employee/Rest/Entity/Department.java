package com.employee.Rest.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name = "department_table")
@Builder
public class Department {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_depart", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int departId;
	@Column(name = "department_name")
	private String departmentName;
	@Column(name = "years_experience")
	private double yearsExperience;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employee;   // its a list of Employee 


	public int getDepartId() {
		return departId;
	}


	public void setDepartId(int departId) {
		this.departId = departId;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public double getYearsExperience() {
		return yearsExperience;
	}


	public void setYearsExperience(double yearsExperience) {
		this.yearsExperience = yearsExperience;
	}


	public List<Employee> getEmployee() {
		return employee;
	}


	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "Department [departId=" + departId + ", departmentName=" + departmentName + ", yearsExperience="
				+ yearsExperience + ", employee=" + employee + "]";
	}

	
	
	

}
