package com.hrworkforcerestapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrworkforcerestapp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

	@Modifying
	@Query("update Employee set status=:status where empId=:empid")
	Integer modifyEmployeestatuswithid(int empid, String status);  
	//Patch - Modifying queries can only use void or int/Integer as return type!

	List<Employee> findAllByEmpName(String empName);
	
}
