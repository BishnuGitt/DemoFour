package com.employee.Rest.DAO.DAOImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.employee.Rest.CollectionDTO.DepartmentDTO;
import com.employee.Rest.CollectionDTO.EmployeeBaseExtraDTO;
import com.employee.Rest.DAO.EmployeeDao;
import com.employee.Rest.Entity.Department;

@Repository // Notice this
public class EmployeDAOImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EmployeeBaseExtraDTO> getEmployeeLists(boolean isOldEmployee) {
		List<EmployeeBaseExtraDTO> employeeList = new ArrayList<EmployeeBaseExtraDTO>();
		String query1 = null;

		// query1 = "select * from employee_tbl where is_old_employee = ? "; // this is from table
	    query1 = "SELECT *  FROM employee_tbl AS e "
		+ "INNER JOIN department_table AS d ON e.employee_depart_id=d.id_depart"
		+ " JOIN baseEmpExtraInfo_tbl AS b ON e.baseempextrainfo_id=b.id where is_old_employee = ?";
		employeeList = jdbcTemplate.query(query1, new Object[] { isOldEmployee }, new EmployeeMapper1());
		return employeeList;
	}

	@Override
	public List<EmployeeBaseExtraDTO> getEmployeeLists(Date enrolledDate) {
		List<EmployeeBaseExtraDTO> listEmployeeDTO = new ArrayList<EmployeeBaseExtraDTO>();

		String query1 = null;
		query1 = "SELECT * FROM javaLength.employee_tbl emp, "
				+ "javaLength.baseEmpExtraInfo_tbl base where emp.baseempextrainfo_id = base.id "
				+ "and emp.enrolled_date = ?"; // where this emp.enrolled_date coming from -- answer
												// javaLength.employee_tbl emp, // is // table
		listEmployeeDTO = jdbcTemplate.query(query1, new Object[] { enrolledDate }, new EmployeeMapper());
		return listEmployeeDTO;
	}

	class EmployeeMapper1 implements RowMapper<EmployeeBaseExtraDTO> { /// this is a inner class
		@Override
		public EmployeeBaseExtraDTO mapRow(ResultSet rs, int rowNum) throws SQLException { // is it same as other ?
			EmployeeBaseExtraDTO employeeDTO = new EmployeeBaseExtraDTO();
			employeeDTO.setEmployeeId(rs.getInt("id_employee")); // EMPLOYEE CLASS
			employeeDTO.setFirstName(rs.getString("first_name"));
			employeeDTO.setLastName(rs.getString("last_name"));
			employeeDTO.setCellNumber(rs.getString("cell_number"));
			employeeDTO.setEnrolledDate(rs.getDate("enrolled_date"));
			employeeDTO.setOldEmployee(rs.getBoolean("is_old_employee"));
			employeeDTO.setBaseEmpExtraInfo_id(rs.getInt("baseempextrainfo_id"));
			employeeDTO.setEmployeeDepartId(rs.getInt("employee_depart_id"));
			employeeDTO.setId(rs.getInt("id"));				// BASEXTRAINFO CLASS 
			employeeDTO.setNickName(rs.getString("nick_name"));

			DepartmentDTO dto = new DepartmentDTO();   // this is because .getDepartment() will return so ... it better to create object of that class and pass the properties in it 
			dto.setDepartId(rs.getInt("id_depart"));
			dto.setDepartmentName(rs.getString("department_name"));
			dto.setYearsExperience(rs.getDouble("years_experience"));  
			employeeDTO.setDepartmentDTO(dto); // DEPARTMENT CLASS --- look closely...

			return employeeDTO;
		}

	}

	class EmployeeMapper implements RowMapper<EmployeeBaseExtraDTO> { /// this is a inner class
		@Override
		public EmployeeBaseExtraDTO mapRow(ResultSet rs, int rowNum) throws SQLException { // is it same as other ?
			EmployeeBaseExtraDTO employeeDTO = new EmployeeBaseExtraDTO();

			employeeDTO.setId(rs.getInt("id")); // BaseEmpExtraInfoDTO CLASSS COZ it EXTENDED
			employeeDTO.setNickName(rs.getString("nick_name"));

			employeeDTO.setEmployeeId(rs.getInt("id_employee")); // EMPLOYEE CLASS
			employeeDTO.setFirstName(rs.getString("first_name"));
			employeeDTO.setLastName(rs.getString("last_name"));
			employeeDTO.setCellNumber(rs.getString("cell_number"));
			employeeDTO.setEnrolledDate(rs.getDate("enrolled_date"));
			employeeDTO.setOldEmployee(rs.getBoolean("is_old_employee"));
			employeeDTO.setBaseEmpExtraInfo_id(rs.getInt("baseempextrainfo_id"));

			return employeeDTO;
		}

	}

	@Override
	public EmployeeBaseExtraDTO getEmployee(String cellNumber, String lastName) {
		EmployeeBaseExtraDTO employeeDTO = new EmployeeBaseExtraDTO();

		String sql = null;
		if (!StringUtils.isEmpty(cellNumber) && !StringUtils.isEmpty(lastName)) {

			sql = "SELECT * FROM employee_tbl "
					+ "INNER JOIN baseEmpExtraInfo_tbl ON employee_tbl.baseempextrainfo_id=baseEmpExtraInfo_tbl.id "
					+ "where cell_number = ? and last_name = ?"; // added later
			
			// TODO : For response, make sure you are getting all data from three tables ? Also don't forget to change in RowMapper for the response.

			employeeDTO = (EmployeeBaseExtraDTO) jdbcTemplate.queryForObject(sql, new Object[] { cellNumber, lastName },
					new EmployeeMapper());

		}
		return employeeDTO;
	}

	@Override
	public List<EmployeeBaseExtraDTO> getEmployees() {
		List<EmployeeBaseExtraDTO> listEmployeeDTO = new ArrayList<EmployeeBaseExtraDTO>();

		String sql = null;
		// Try sort Logic from java - using java 8 ?
		sql = "SELECT * " + "FROM employee_tbl "
				+ "INNER JOIN baseEmpExtraInfo_tbl ON employee_tbl.baseempextrainfo_id=baseEmpExtraInfo_tbl.id ";
		// this is from table
		listEmployeeDTO = jdbcTemplate.query(sql, new EmployeeMapper()); // new Object[]{cellNumber, lastName } not
																			// needed... query(

		Collections.sort(listEmployeeDTO, new EmployeeBaseExtraDTO()); // sorting by Comparator "ORDER BY last_name
																		// ASC";
		return listEmployeeDTO;
	}

	@Override
	public void getEmployeeCSV() {
		ICsvBeanWriter beanWriter = null;
		List<EmployeeBaseExtraDTO> listEmployees = getEmployees(); // this methods query will be executed for

		try {
			beanWriter = new CsvBeanWriter(new FileWriter("emp.csv"), CsvPreference.STANDARD_PREFERENCE); // try with
																											// customized
																											// ENUM ?
			// why employeeId not displaying in csv file ?
			String[] columnHeader = new String[] { "Id_Base_Extra", "NickName", "EmployeeId", "FirstName", "LastName",
					"CellNumber", "EnrolledDate", "BaseEmpExtraInfo_id", "OldEmployee" };
			String[] columnMapperTracker = { "id", "nickName", "employeeId", "firstName", "lastName", "cellNumber",
					"enrolledDate", "baseEmpExtraInfo_id", "oldEmployee" };
			// write the header
			beanWriter.writeHeader(columnHeader);

			// write the beans data
			for (EmployeeBaseExtraDTO c : listEmployees) {
				beanWriter.write(c, columnMapperTracker); /// columnMapperTracker we are mapping here
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				beanWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void saveListEmployees(List<EmployeeBaseExtraDTO> listEmpo) {
		String sql = null;
		// anything that is auto generated (employee_id) should not be in query
		//sql = "Call employeAndBaseExtra_store_procedure(?,?,?,?,?,?,?,?,?,?,?)";
		sql = "Call InsertEmployeeDepartmentBaseextra(?,?,?,?,?,?,?)";

		for (EmployeeBaseExtraDTO eD : listEmpo) {
			jdbcTemplate.update(sql,
					new Object[] { eD.getNickName(), eD.getDepartmentDTO().getDepartmentName(),
							eD.getDepartmentDTO().getYearsExperience(), eD.getFirstName(), eD.getLastName(),
							eD.getCellNumber(), eD.isOldEmployee() }); 
//			jdbcTemplate.update(sql,
//					new Object[] { e.getFirstName(), e.getLastName(), e.getCellNumber(), e.getEnrolledDate(), // we not passing listEmpo param  here since it is  list
//							e.getBaseEmpExtraInfo_id(), e.isOldEmployee(), e.getId(), e.getNickName(), e.getEmployeeDepartId(), 
//								 e.getDepartmentDTO().getDepartmentName(), e.getDepartmentDTO().getYearsExperience() }); // notice  e.isOldEmployee()  not have .get()
		}
	}

	@Override
	public void deleteEmpoById(int employeeId) {
		// Try deleting 3 tables with either Id or with single word ? 
// how is this working  DELETE employee_tbl, baseEmpExtraInfo_tbl  ? 
// Need to adjust the cascading in parent table		
		
		String query1  = "DELETE employee_tbl, baseEmpExtraInfo_tbl, department_table FROM employee_tbl"
					   + " JOIN baseEmpExtraInfo_tbl ON employee_tbl.baseempextrainfo_id = baseEmpExtraInfo_tbl.id"
				+ " JOIN department_table ON employee_tbl.employee_depart_id=department_table.id_depart"
				+ " WHERE employee_tbl.id_employee = ? ";

		jdbcTemplate.update(query1, new Object[] { employeeId }); // no need of new EmployeeMapper() ... only on GET

	}

	@Override
	public void updateEmpoById(int employeeId, EmployeeBaseExtraDTO empo) {
		String query1 = null;
		query1 = " UPDATE employee_tbl " + " SET first_name = ? " + " WHERE id_employee = ? ";
		jdbcTemplate.update(query1, new Object[] { empo.getFirstName(), employeeId }); // no need of new EmployeeMapper() ... only on GET
																						
	}

	@Override
	public void saveDepartment(Department department) {
		String query1 = null;
		query1 = "INSERT INTO department_table( department_name, years_experience) VALUES ( ?, ?)";
		jdbcTemplate.update(query1, new Object[] { department.getDepartmentName(), department.getYearsExperience() });

	}

	// Try inserting into both Employee and Department Table ??
	@Override
	public List<EmployeeBaseExtraDTO> getListEmployeeDepartment() {
		List<EmployeeBaseExtraDTO> listEmployeeDTO = new ArrayList<EmployeeBaseExtraDTO>();

		String sql = null;
		// always make sure forengn key is relation established
		sql = "SELECT *  FROM employee_tbl AS e "
				+ "INNER JOIN department_table AS d ON e.employee_depart_id=d.id_depart"
				+ " JOIN baseEmpExtraInfo_tbl AS b ON e.baseempextrainfo_id=b.id";
		// this is from table
		listEmployeeDTO = jdbcTemplate.query(sql, new EmployeeMapper1()); // new Object[]{cellNumber, lastName } not  needed... query(																		
		return listEmployeeDTO;
	}

	@Override
	public void saveEmployeeDepartmentBaseextraInfo(EmployeeBaseExtraDTO eD) {
		String sql = null;
		sql = "Call InsertEmployeeDepartmentBaseextra(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,
				new Object[] { eD.getNickName(), eD.getDepartmentDTO().getDepartmentName(),
						eD.getDepartmentDTO().getYearsExperience(), eD.getFirstName(), eD.getLastName(),
						eD.getCellNumber(), eD.isOldEmployee() }); // take a look closely ... it could even expand further																
	}

	@Override
	public void updateByEmployeeIdEmployeeBaseExtraDTO(int employeeId, EmployeeBaseExtraDTO empo) {
		String query1 = null;
		// try updating multiple tables using SP ? Try updating List<EmployeeBaseExtraDTO> in multiple tables ? 
		query1 = " UPDATE employee_tbl " + " SET first_name = ? " + " WHERE id_employee = ? ";
		jdbcTemplate.update(query1, new Object[] { empo.getFirstName(), employeeId });
	}

}
