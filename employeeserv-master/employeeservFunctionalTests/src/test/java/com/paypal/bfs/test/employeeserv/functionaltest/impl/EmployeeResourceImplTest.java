package com.paypal.bfs.test.employeeserv.functionaltest.impl;

import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import com.paypal.bfs.test.employeeserv.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeResourceImplTest {
	
	@Autowired
	EmployeeResourceImpl employeeResourceImpl;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Before
	public void setup() {
		EmployeeEntity e = new EmployeeEntity();
		e.setId(1);
		e.setFirst_name("shivangi");
		e.setLast_name("goel");
		e.setDate_of_birth("2021-02-09");
		e.setLine1("5044 sobha carnation");
		e.setLine2("bellandur");
		e.setCity("bangalore");
		e.setCountry("india");
		e.setZip_code("560103");
		System.out.print(employeeRepository);
		employeeRepository.save(e);
		
		
	}
	
	@Test
	public void getEmployee_By_id_returns_employee() {
		ResponseEntity<Employee> employee = employeeResourceImpl.employeeGetById(1);
		Assert.assertEquals(200, employee.getStatusCodeValue());
	}
	
	@Test
	public void getEmployee_By_id_returns_no_content() {
		ResponseEntity<Employee> employee = employeeResourceImpl.employeeGetById(2);
		Assert.assertEquals(204, employee.getStatusCodeValue());
	}
	
	@Test
	public void createEmployee_returns_employee() {
		Employee e = new Employee();
		Address address = new Address();
		address.setLine1("5044 sobha carnation");
		address.setLine2("bellandur");
		address.setCity("bangalore");
		address.setCountry("india");
		address.setZipCode("560103");
		e.setId(2);
		e.setFirstName("amit");
		e.setLastName("srivastava");
		e.setDateOfBirth("2021-02-10");
		e.setAddress(address);
		ResponseEntity<Employee> employee = employeeResourceImpl.createEmployee(e);
		
		Assert.assertEquals(201, employee.getStatusCodeValue());
	}
	
	@Test
	public void createEmployee_returns_conflict_with_existing_id() {
		Employee e = new Employee();
		Address address = new Address();
		address.setLine1("5044 sobha carnation");
		address.setLine2("bellandur");
		address.setCity("bangalore");
		address.setCountry("india");
		address.setZipCode("560103");
		e.setId(1);
		e.setFirstName("abc");
		e.setLastName("srivastava");
		e.setDateOfBirth("2021-02-10");
		e.setAddress(address);
		ResponseEntity<Employee> employee = employeeResourceImpl.createEmployee(e);
		
		Assert.assertEquals(409, employee.getStatusCodeValue());
	}

}
