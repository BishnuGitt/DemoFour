package fourLengthyPro.example.demoFour;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.Rest.DAO.EmployeeDao;
import com.employee.Rest.Entity.Department;

@SpringBootTest
public class DepartmentTest {

	@Autowired
	private EmployeeDao employeDao; 
	
	@Test
	public void saveDepartment(Department department) {
// 		Department dpt = department.b
		
	}
	
	
}
