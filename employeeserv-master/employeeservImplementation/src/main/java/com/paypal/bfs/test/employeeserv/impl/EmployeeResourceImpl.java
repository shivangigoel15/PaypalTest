package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.utils.Utils;

import java.util.Date;
import java.util.Optional;
//import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	
	Logger logger = LogManager.getLogger(EmployeeResourceImpl.class);
	@Autowired
	EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Employee> employeeGetById(int id) {
    	try {
    		//Get employee using id
	        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
	        
	        if(employee.get() != null) {
	        	//Map Employee entity to response object
	        	Employee result = Utils.entityToEmployee(employee.get());
	        	
	        	return new ResponseEntity<>(result, HttpStatus.OK);	
	        }
    	} catch (Exception e) {
    		logger.error("employeeGetById -> failed for id: {id}", id, e);
    	}

    	//If no employee found, return NoContent
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

	@Override
	public ResponseEntity<Employee> createEmployee(Employee newEmployee) {
		try {
			// check if employee already exists
			if(testIfEmployeeExists(newEmployee.getId())) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			//Map request to Employee entity
			EmployeeEntity employeeEntity = Utils.employeeToEntity(newEmployee);
			
			//Update the in-memory db
			employeeRepository.save(employeeEntity);
			
			return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("newEmployee -> failed for employee: {employee}", newEmployee, e);
			
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	private Boolean testIfEmployeeExists(int id) {
		Optional<EmployeeEntity> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return true;
		}
		return false;
		
	}
}
