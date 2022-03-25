package com.employee.Rest.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Rest.CollectionDTO.EmployeeBaseExtraDTO;
import com.employee.Rest.Entity.Department;
import com.employee.Rest.Service.EmployeeService;


@RestController
@RequestMapping("/main")
public class EmployeeController {
	
//	@GetMapping("/getMessage")
//	@ResponseBody
//	public String getEmployeeMessage() { 
//			return "Good job !"; 
//	}
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(method = {RequestMethod.PUT }, value = "/updateByEmployeeIdEmployeeBaseExtraDTO") 
	public String updateByEmployeeIdEmployeeBaseExtraDTO(@RequestParam(required = true) int employeeId, @RequestBody EmployeeBaseExtraDTO empo ){    //  look @RequestBody .. value = "employeeId" if parameter name is different than clumn name than use this 
		  employeeService.updateByEmployeeIdEmployeeBaseExtraDTO(employeeId, empo); 
	
			return "Employee with id " + employeeId  + " UPDATED (department_table, employee_tbl, baseEmpExtraInfo_tbl) !"; 
	}
	
	
	@RequestMapping(method = {RequestMethod.POST }, value = "/saveEmployeeDepartmentBaseextraInfo") 
	public String saveEmployeeDepartmentBaseextraInfo( @RequestBody EmployeeBaseExtraDTO eD){    //  not need of @RequestParam since we are passing obj for save 
		  employeeService.saveEmployeeDepartmentBaseextraInfo(eD); 
	
			return " EmployeeDepartmentBaseextraInfo are saved ! " ; 
	}
	
	
	@RequestMapping(method = {RequestMethod.GET }, value = "/getListEmployeeDepartment") 
	public List<EmployeeBaseExtraDTO> getListEmployeeDepartment( ){    //  not need of @RequestParam since we are passing obj for save 
		List<EmployeeBaseExtraDTO> listEmployee =  employeeService.getListEmployeeDepartment(); 
	
			return listEmployee; 
	}
	
	@RequestMapping(method = {RequestMethod.POST }, value = "/saveDepartment") 
	public String saveDepartment(  @RequestBody Department department ){    //  not need of @RequestParam since we are passing obj for save 
		  employeeService.saveDepartment(department); 
	
			return "Department saved !"; 
	}
	
	
	
	@RequestMapping(method = {RequestMethod.PUT }, value = "/updateByEmployeeId") 
	public String UpdateEmployeeById(@RequestParam(required = true) int employeeId, @RequestBody EmployeeBaseExtraDTO empo ){    //  look @RequestBody .. value = "employeeId" if parameter name is different than clumn name than use this 
		  employeeService.updateEmployeById(employeeId, empo); 
	
			return "Employee with id " + employeeId  + " UPDATED !"; 
	}
	
	
	
	@RequestMapping(method = {RequestMethod.DELETE }, value = "/deleteByEmployeeId") 
	public String deleteEmployeeById(@RequestParam(required = true) int employeeId){   
		  employeeService.deleteEmployeById(employeeId); 
			return "Employee with id " + employeeId  + " is deleted successfully !"; 
	}


	//  Pass @RequestBody List<EmployeeDTO> listEmpo and return listOfEmployee ??  very important 
	@RequestMapping(method = {RequestMethod.POST }, value = "/saveListOfEmployee") // look at Return Type of Controller and implementation data type of other service, dao classes
	public String getListEmployee(@RequestBody List<EmployeeBaseExtraDTO> listEmpo){   // Passing list of Employee 
		  employeeService.saveEmployeeLists(listEmpo); 
			return "Employees list has been saved !"; 
	}
	

	@RequestMapping(method = {RequestMethod.GET }, value = "/getListEmployee")
	@ResponseBody
	public List<EmployeeBaseExtraDTO> getListEmployee(
											 @RequestParam(value = "isOldEmployee", required = false) boolean isOldEmployee ){
		List<EmployeeBaseExtraDTO> EmployeeDTOList = employeeService.getEmployeeLists(isOldEmployee); 
	
			return EmployeeDTOList; 
	}
	
	
	@RequestMapping(method = {RequestMethod.GET }, value = "/getEmployees")
	@ResponseBody
	public List<EmployeeBaseExtraDTO> getEmployees(){
		List<EmployeeBaseExtraDTO> EmployeeDTOList = employeeService.getEmployees(); 
			return EmployeeDTOList; 
	}
	
	@RequestMapping(method = {RequestMethod.GET }, value = "/getEmployeeByCellNumberLastName")
	@ResponseBody
	public EmployeeBaseExtraDTO getEgetEmployeeByCellNumbermployee(
											 @RequestParam(value = "cellNumber", required = true) String cellNumber, 
											 @RequestParam(value = "lastName", required = true) String lastName){
		// check on the post for required TRUE or FALSE ? and meanwhile check query too ??? 
		EmployeeBaseExtraDTO EmployeeDTO = employeeService.getEmployee(cellNumber, lastName); 
	
			return EmployeeDTO; 
	}
	
	
	// Employee enrolled between this date to this ? 
	@RequestMapping(method = {RequestMethod.GET }, value = "/getListEmployeeByDate")
	@ResponseBody
	public List<EmployeeBaseExtraDTO> getListEmployeeByDate(
											 @RequestParam(value = "enrolledDate", required = false) String enrolledMyDate ) throws ParseException{ // this value is one that we pass through postman 
		Date enrolledDate = null; // default format is "-" 
		System.out.println("Testing2 date output " + enrolledMyDate);
		if(enrolledMyDate.contains("-") ) {  // 2022-03-11
			 enrolledDate = new SimpleDateFormat("yyyy-MM-dd").parse(enrolledMyDate); 
		}else {
			 enrolledDate = new SimpleDateFormat("yyyy/MM/dd").parse(enrolledMyDate);  
		} 
		List<EmployeeBaseExtraDTO> EmployeeDTOList = employeeService.getEmployeeByDate(enrolledDate); 
			return EmployeeDTOList; 
	}
	

	@RequestMapping(method = {RequestMethod.GET }, value = "/getTableToCsv")
	public String getEmployeeCSV(){
		 employeeService.getEmployeeTableCSV();    // Look at this no need of something like this List<EmployeeDTO> EmployeeDTOList = 
	
			return "CSV file successfully created"; 
	}
	

}
