package com.employee.Rest.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_tbl")
public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_employee", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId; 
	@Column(name = "first_name")
	private String firstName; 
	@Column(name = "last_name")
	private String lastName; 
	@Column(name = "cell_number")
	private String cellNumber; 
	@Column(name = "enrolled_date")
	private Date enrolledDate; 
	@Column(name = "is_old_employee")
	private boolean isOldEmployee;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "baseempextrainfo_id", referencedColumnName = "id")  // from baseT
	private BaseEmpExtraInfo baseEmpExtraInfo; 
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_depart_id", referencedColumnName = "id_depart")  // from baseT
	private Department department; 


	
	
	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public BaseEmpExtraInfo getBaseEmpExtraInfo() {
		return baseEmpExtraInfo;
	}


	public void setBaseEmpExtraInfo(BaseEmpExtraInfo baseEmpExtraInfo) {
		this.baseEmpExtraInfo = baseEmpExtraInfo;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getCellNumber() {
		return cellNumber;
	}


	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}


	public Date getEnrolledDate() {
		return enrolledDate;
	}


	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}


	public boolean isOldEmployee() {
		return isOldEmployee;
	}


	public void setOldEmployee(boolean isOldEmployee) {
		this.isOldEmployee = isOldEmployee;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", cellNumber=" + cellNumber + ", enrolledDate=" + enrolledDate + ", isOldEmployee=" + isOldEmployee
				+ ", baseEmpExtraInfo=" + baseEmpExtraInfo + ", department=" + department + "]";
	}


	

	

}
