package com.hrworkforcerestapp.service;

import java.util.List;
import java.util.Optional;

import com.hrworkforcerestapp.entity.Employee;

public interface EmployeeService 
{
	Employee saveEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	Employee getEmployeeById(int empId);
	Boolean deleteEmployeeById(int empId);
	List<Employee> getAllEmployee();
	
	Integer modifyEmployeestatuswithid(int empid, String status);
	List<Employee> getEmployeeByEmpName(String empName);
	Employee getEmployeeByCountryName(String cname);
	Long getTotalEmployeeCount();
		
	

}
