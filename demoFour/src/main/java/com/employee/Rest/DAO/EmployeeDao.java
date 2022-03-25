package com.employee.Rest.DAO;

import java.util.Date;
import java.util.List;

import com.employee.Rest.CollectionDTO.EmployeeBaseExtraDTO;
import com.employee.Rest.Entity.Department;



public interface EmployeeDao {

	List<EmployeeBaseExtraDTO> getEmployeeLists(boolean isOldEmployee);

	List<EmployeeBaseExtraDTO> getEmployeeLists(Date enrolledDate);

	// how to get list with the help  parameters from 3 different class's filed ? 
	EmployeeBaseExtraDTO getEmployee(String cellNumber, String lastName);

	List<EmployeeBaseExtraDTO> getEmployees();

	void getEmployeeCSV();  //

	void saveListEmployees(List<EmployeeBaseExtraDTO> listEmpo);

	void deleteEmpoById(int employeeId);

	void updateEmpoById(int employeeId, EmployeeBaseExtraDTO empo);
	
	void saveDepartment(Department department);

	List<EmployeeBaseExtraDTO> getListEmployeeDepartment();

	void saveEmployeeDepartmentBaseextraInfo(EmployeeBaseExtraDTO eD );  // 

	void updateByEmployeeIdEmployeeBaseExtraDTO(int employeeId, EmployeeBaseExtraDTO empo);
}
