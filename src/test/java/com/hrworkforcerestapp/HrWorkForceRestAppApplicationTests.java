package com.hrworkforcerestapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hrworkforcerestapp.entity.Country;
import com.hrworkforcerestapp.entity.Employee;
import com.hrworkforcerestapp.repo.EmployeeRepository;
import com.hrworkforcerestapp.service.EmployeeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HrWorkForceRestAppApplicationTests 
{
	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;
	
//	@BeforeEach
//	void  setup()
//	{
//		Employee employee1=new Employee(21,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina"));
//		Employee employee2=new Employee(22,"Vishal Kale", "DeoOps","Inactive", "2023-01-11","Sumit Sir", "2023-01-17", "Santosh Sir", "7325147858", "viskalkale@gmail.com", "Male", new Country(122,"UK"));
//		List<Employee> empList=java.util.Arrays.asList(employee1,employee2);
//		when(employeeRepository.findAll()).thenReturn(empList);
//	}
	@Test
	@Order(1)
	void testSaveEmployee() 
	{
//		List<Employee> empList=java.util.Arrays.asList(new Employee(21,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina")),
//		new Employee(22,"Vishal Kale", "DeoOps","Inactive", "2023-01-11","Sumit Sir", "2023-01-17", "Santosh Sir", "7325147858", "viskalkale@gmail.com", "Male", new Country(122,"UK")));
//		Optional<List<Employee>> empLis1t=Optional.of(empList);
		
		Employee employeeDbTest=new Employee(21,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina"));
		when(employeeRepository.save(employeeDbTest)).thenReturn(employeeDbTest);
		assertEquals(employeeDbTest, employeeService.saveEmployee(employeeDbTest));
	}
	
//	@Test
////	@Order(2)
//	void testUpdateEmployee() 
//	{	
//		Employee employeeDbTest=new Employee(21,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina"));
//		Employee employeeDbTest2=new Employee(21,"Manoj Shinde", "Admin","Inactive", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina"));
//		
////		employeeDbTest.setStatus("Inactive");
//		when(employeeRepository.save(employeeDbTest)).thenReturn(employeeDbTest2);
//		
//		Employee updatedEmployee = employeeService.getEmployeeById(21);
//		
//		updatedEmployee.setStatus("Inactive");
//		String status = updatedEmployee.getStatus();
//		System.out.println(status);
//		assertEquals("Inactive", status);	
//		
//	}



	@Test
	@Order(3)
	void testGetEmployeeById()
	{
		Optional<Employee> empDBTest= Optional.of(new Employee(21,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina")));
		when(employeeRepository.findById(21)).thenReturn(empDBTest);
		assertEquals("manojshinde@gmail.com", employeeService.getEmployeeById(21).getEmail());
		assertEquals("Manoj Shinde", employeeService.getEmployeeById(21).getEmpName());
		
	}


	@Test
	@Order(4)
	void testGetAllEmployeeList()
	{
		Employee employee1=new Employee(21,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina"));
		Employee employee2=new Employee(22,"Vishal Kale", "DeoOps","Inactive", "2023-01-11","Sumit Sir", "2023-01-17", "Santosh Sir", "7325147858", "viskalkale@gmail.com", "Male", new Country(122,"UK"));
		List<Employee> empList=java.util.Arrays.asList(employee1,employee2);
		when(employeeRepository.findAll()).thenReturn(empList);
		assertEquals(2, employeeService.getAllEmployee().size());
	}

	@Test
	@Order(5)
	void testDeleteEmployeeById()
	{
		Optional<Employee> empDBTest= Optional.of(new Employee(22,"Manoj Shinde", "Admin","Active", "2023-01-12","Sumit Sir", "2023-01-17", "Santosh Sir", "9325147858", "manojshinde@gmail.com", "Male", new Country(121,"Chaina")));
		employeeRepository.deleteById(empDBTest.get().getEmpId());
		Optional<Employee> empDeleted = employeeRepository.findById(22);
		assertFalse(empDeleted.isPresent());
	}
}
















