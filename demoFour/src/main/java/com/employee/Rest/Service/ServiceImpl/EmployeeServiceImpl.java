package com.employee.Rest.Service.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Rest.CollectionDTO.EmployeeBaseExtraDTO;
import com.employee.Rest.DAO.EmployeeDao;
import com.employee.Rest.Entity.Department;
import com.employee.Rest.Service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	
	
	@Override
	public List<EmployeeBaseExtraDTO> getEmployeeLists(boolean isOldEmployee) {
		
		return employeeDao.getEmployeeLists(isOldEmployee);
	}



	@Override
	public List<EmployeeBaseExtraDTO> getEmployeeByDate(Date enrolledDate) {
		
		return employeeDao.getEmployeeLists(enrolledDate) ;
	}



	@Override
	public EmployeeBaseExtraDTO getEmployee(String cellNumber, String lastName) {
		
		return employeeDao.getEmployee(cellNumber, lastName);
	}



	@Override
	public List<EmployeeBaseExtraDTO> getEmployees() {
		//
		return employeeDao.getEmployees();
	}



	@Override
	public void getEmployeeTableCSV() {
		 employeeDao.getEmployeeCSV();   //this is void so no RETURN
		
	}



	@Override
	public void saveEmployeeLists(List<EmployeeBaseExtraDTO> listEmpo) {
		
		employeeDao.saveListEmployees(listEmpo);
	}



	@Override
	public void deleteEmployeById(int employeeId) {
		employeeDao.deleteEmpoById(employeeId);
		
	}



	@Override
	public void updateEmployeById(int employeeId, EmployeeBaseExtraDTO empo) {
	
		employeeDao.updateEmpoById(employeeId, empo);
	}



	@Override
	public void saveDepartment(Department department) { // we don't need to create a DepartmentDAO since they have relation
		employeeDao.saveDepartment(department);
		
	}



	@Override
	public List<EmployeeBaseExtraDTO> getListEmployeeDepartment() {
		
		return employeeDao.getListEmployeeDepartment();
	}



	@Override
	public void saveEmployeeDepartmentBaseextraInfo(EmployeeBaseExtraDTO eD) {
		employeeDao.saveEmployeeDepartmentBaseextraInfo(eD); 
		
	}



	@Override
	public void updateByEmployeeIdEmployeeBaseExtraDTO(int employeeId, EmployeeBaseExtraDTO empo) {
		employeeDao.updateByEmployeeIdEmployeeBaseExtraDTO(employeeId, empo); 
		
	}



	



}
