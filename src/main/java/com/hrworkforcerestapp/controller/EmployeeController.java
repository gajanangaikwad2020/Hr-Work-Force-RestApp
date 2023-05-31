package com.hrworkforcerestapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrworkforcerestapp.entity.Employee;
import com.hrworkforcerestapp.exception.ResourceNotFoundException;
import com.hrworkforcerestapp.exception.SomethingWentWrongException;
import com.hrworkforcerestapp.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController 
{
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/save-empolyee")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employee)
	{
		Employee saveEmployee = employeeService.saveEmployee(employee);
		if (saveEmployee!=null) 
		{
			return new ResponseEntity<String>("Employee is Saved",HttpStatus.OK);
		} 
		else 
		{
			throw new SomethingWentWrongException("Somethig went wrong. Please try again");
		}
		
	}
	@PutMapping(value = "/update-empolyee")
	public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee)
	{
		
		Employee saveEmployee = employeeService.updateEmployee(employee);
		if (saveEmployee!=null) 
		{
			return new ResponseEntity<String>("Employee is Updated",HttpStatus.OK);
		} 
		else 
		{
			throw new ResourceNotFoundException("Employee is not found to Update with employee Id : "+employee.getEmpId());
		}
		
	}
	
	@GetMapping(value = "/get-emp-byid/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId)
	{
		 Employee employeeDb = employeeService.getEmployeeById(empId);
		if(employeeDb!=null)
		{
			return new ResponseEntity<Employee>(employeeDb,HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Employee is not found with employee Id :"+empId);
		}
	}
	
	@DeleteMapping(value = "/delete-emp-byid/{empId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int empId)
	{
		 Boolean idDeleted = employeeService.deleteEmployeeById(empId);
		 System.out.println(idDeleted);
		if(idDeleted)
		{
			return new ResponseEntity<String>("Employee is Deleted with employee Id :"+empId,HttpStatus.FOUND);
		}
		else
		{
			throw new ResourceNotFoundException("Employee is not found to delete with employee Id :"+empId);
		}
	}
	
	@GetMapping(value = "/get-all-employee")
	public ResponseEntity<List<Employee>> geAlltEmployee()
	{
		 List<Employee> allEmployee = employeeService.getAllEmployee();
		if(!allEmployee.isEmpty())
		{
			return new ResponseEntity<List<Employee>>(allEmployee,HttpStatus.FOUND);
		}
		else
		{
			throw new ResourceNotFoundException("Employees are  not found.");
		}
	}
	
	//PatchMapping- For Partial Update
	
	@PatchMapping(value = "/modify-empolyee/{empid}/{status}")
	public ResponseEntity<String> modifyEmployeestatuswithid(@PathVariable int empid, @PathVariable String status)
	{
		Integer modifyid = employeeService.modifyEmployeestatuswithid(empid,status);
//		return new ResponseEntity<String>("Employee's Status is Updated with id :"+empid,HttpStatus.OK);
		
		if (modifyid!=0) 
		{
			return new ResponseEntity<String>("Employee's Status is Updated with id :"+empid,HttpStatus.CREATED);
		} 
		else 
		{
			throw new ResourceNotFoundException("Employee is not found to Partially Update with employee Id : "+empid);
		}
	}
	
	@GetMapping(value = "/get-emp-byname/{empname}") 				
	public ResponseEntity<List<Employee>> getEmployeeByEmpNamePathVar(@PathVariable String empname)
	{
		 List<Employee> employeeByName = employeeService.getEmployeeByEmpName(empname);
		if(!employeeByName.isEmpty())
		{
			return new ResponseEntity<List<Employee>>(employeeByName,HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Employee is not found with employee Name :"+empname);
		}
	}
			//Or we can use request Param
	@GetMapping(value = "/get-emp-byname/empname") 
	public ResponseEntity<List<Employee>> getEmployeeByEmpNameRequestParam(@RequestParam String empname)
	{
		 List<Employee> employeeByName = employeeService.getEmployeeByEmpName(empname);
		if(!employeeByName.isEmpty())
		{
			return new ResponseEntity<List<Employee>>(employeeByName,HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Employee is not found with employee Name :"+empname);
		}
	}
	
	
	@GetMapping(value = "/get-emp-by-countryname/{cname}") 				
	public ResponseEntity<Employee> getEmployeeByCountryName(@PathVariable String cname)
	{
		  Employee employeeByCountryName = employeeService.getEmployeeByCountryName(cname);
		if(employeeByCountryName!=null)
		{
			return new ResponseEntity<Employee>(employeeByCountryName,HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Employee is not found with country  Name :"+cname);
		}
	}
	
	
	@GetMapping(value = "/get-total-emp-count") 				
	public ResponseEntity<String> getTotalEmployeeCount()
	{
		Long totalEmployeeCount = employeeService.getTotalEmployeeCount();
		if(totalEmployeeCount!=0)
		{
			return new ResponseEntity<String>("Total Count of Employee is :"+totalEmployeeCount,HttpStatus.OK);	
		}
		else
		{
			throw new ResourceNotFoundException("Employee is not found:");
		}
	}
	
	
	
}
