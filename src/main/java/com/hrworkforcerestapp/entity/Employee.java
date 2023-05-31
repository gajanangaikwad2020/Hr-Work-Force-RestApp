package com.hrworkforcerestapp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	@NotNull(message = "Emp Name is Required")
	private String empName;
	@NotNull(message = "Emp Dept is Required")
	private String dept;
	@NotNull(message = "Emp status is Required")
	private String status; 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String joiningDate;
	private String createdBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String updatedDate;
	private String updateBy;
	@NotBlank
	@Pattern(regexp = "^\\d{10}$",message="Please enter valid phone number")
	private String phoneNo;
	@Email
	private String email;
	@NotBlank
	private String gender;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Country country;
	
	
	
}
