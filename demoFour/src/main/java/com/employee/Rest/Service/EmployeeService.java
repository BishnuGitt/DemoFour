package com.employee.Rest.Service;

import java.util.Date;
import java.util.List;

import com.employee.Rest.CollectionDTO.EmployeeBaseExtraDTO;
import com.employee.Rest.Entity.Department;


public interface EmployeeService {

	public List<EmployeeBaseExtraDTO> getEmployeeLists(boolean isOldEmployee);

	public List<EmployeeBaseExtraDTO> getEmployeeByDate(Date enrolledDate);

	public EmployeeBaseExtraDTO getEmployee(String cellNumber, String lastName);

	public List<EmployeeBaseExtraDTO> getEmployees();

	public void getEmployeeTableCSV();

	public void saveEmployeeLists(List<EmployeeBaseExtraDTO> listEmpo);

	public void deleteEmployeById(int employeeId); // why is it giving return type as VOID by default ? 

	public void updateEmployeById(int employeeId, EmployeeBaseExtraDTO empo);

	public void saveDepartment(Department department);

	public List<EmployeeBaseExtraDTO> getListEmployeeDepartment();

	public void saveEmployeeDepartmentBaseextraInfo(EmployeeBaseExtraDTO eD );

	public void updateByEmployeeIdEmployeeBaseExtraDTO(int employeeId, EmployeeBaseExtraDTO empo);

	

	
	
	
	
}
