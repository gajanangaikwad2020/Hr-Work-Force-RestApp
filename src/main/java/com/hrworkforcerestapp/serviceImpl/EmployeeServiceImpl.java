package com.hrworkforcerestapp.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.hrworkforcerestapp.entity.Employee;
import com.hrworkforcerestapp.exception.ResourceNotFoundException;
import com.hrworkforcerestapp.repo.EmployeeRepository;
import com.hrworkforcerestapp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) 
	{		
//		String joiningDate = employee.getJoiningDate();
//		String updatedDate = employee.getUpdatedDate();
//		Date date1,date2;
//		try 
//		{
//			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(joiningDate);
//			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updatedDate);
//			String newJoiningDate = new SimpleDateFormat("yyyy-MM-dd").format(date1);
//			String newUpdatedDate = new SimpleDateFormat("yyyy-MM-dd").format(date2);
//			employee.setJoiningDate(newJoiningDate);
//			employee.setUpdatedDate(newUpdatedDate);
//		} 
//		catch (ParseException e) 
//		{
//			e.printStackTrace();
//		}		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) 
	{
		Employee employeeDB = employeeRepository.findById(employee.getEmpId()).get();
		if(employeeDB!=null)
		{
			employeeRepository.save(employee);
			return employee;
		}
		return employee;
	}

	@Override
	public Employee getEmployeeById(int empId) 
	{
//		Employee saveEmployee=null;
		Optional<Employee> empDb = employeeRepository.findById(empId);
		System.out.println(empDb);
		if(empDb.isPresent())
		{
			System.out.println(empDb);
			return empDb.get();
		}
		return empDb.get();
		
	}

	@Override
	public Boolean deleteEmployeeById(int empId) 
	{
		Employee employeeDb = employeeRepository.findById(empId).get();
		Boolean isDeleted=false;
		if(employeeDb!=null)
		{
			employeeRepository.deleteById(empId);
			isDeleted=true;
		}
		return isDeleted;
		
	}

	@Override
	public List<Employee> getAllEmployee() 
	{
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Integer modifyEmployeestatuswithid(int empid, String status) {
		 Integer modifyEmployeestatuswithid = employeeRepository.modifyEmployeestatuswithid(empid,status);
		 return modifyEmployeestatuswithid;
	}

	@Override
	public List<Employee> getEmployeeByEmpName(String empName) 
	{
		return employeeRepository.findAllByEmpName(empName);
	}

	@Override
	public Employee getEmployeeByCountryName(String cname) 
	{
		List<Employee> allEmployees = employeeRepository.findAll();
		Employee employee2=null;
		for (Employee employee : allEmployees) 
		{
			if(employee.getCountry().getCname().equalsIgnoreCase(cname))
			{
				employee2=employee;
			}	
		}
		return employee2;
	}

	@Override
	public Long getTotalEmployeeCount() 
	{
		long count = employeeRepository.count();
		return count;
	}
	
	

}
