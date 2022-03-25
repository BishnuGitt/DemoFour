package com.employee.Rest.CollectionDTO;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;

import com.employee.Rest.Entity.BaseEmpExtraInfo;
import com.employee.Rest.Entity.Employee;

// why is ModelMapper is not used in myRealProject since, youtube video says, it is important to have that ? 
// extends how and what it is doing here ? 
public class EmployeeBaseExtraDTO  extends BaseExtraInfoDTO implements Comparator<EmployeeBaseExtraDTO>, Serializable{ // Comparator must have type 
	
	private static final long serialVersionUID = 1L; 
	
	private int employeeId; 
	private String firstName; 
	private String lastName; 
	private String cellNumber; 
	@JsonbDateFormat("yyyy-dd-mm")  // fix the date issue ? 
	private Date enrolledDate; 
	private boolean isOldEmployee;

	
	private int baseEmpExtraInfo_id; // 
	
	private int employeeDepartId;   // this filed name can be anything.. 
	
	private DepartmentDTO departmentDTO;  // do this when you cannot extend but need to work in as a single class. 

	
	
	
	@Override
	public int compare(EmployeeBaseExtraDTO e1, EmployeeBaseExtraDTO e2) { 
		
		return e1.getLastName().compareTo(e2.getLastName()); // sorting by  Last name instead of query sql
	}
	
	
	
	
	// this doesn't have Constructor  and toString ..... why ? 

	public DepartmentDTO getDepartmentDTO() {
		return departmentDTO;
	}




	public void setDepartmentDTO(DepartmentDTO departmentDTO) {
		this.departmentDTO = departmentDTO;
	}

	public int getBaseEmpExtraInfo_id() {
		return baseEmpExtraInfo_id;
	}
	public void setBaseEmpExtraInfo_id(int baseEmpExtraInfo_id) {
		this.baseEmpExtraInfo_id = baseEmpExtraInfo_id;
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

	public int getEmployeeDepartId() {
		return employeeDepartId;
	}

	public void setEmployeeDepartId(int employeeDepartId) {
		this.employeeDepartId = employeeDepartId;
	}
}
