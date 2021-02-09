package com.paypal.bfs.test.employeeserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paypal.bfs.test.employeeserv.api.model.Address;

import java.util.Date;


@Entity
@Table
public class EmployeeEntity {
	
	@Id
	@Column
	private int id;
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private String date_of_birth;
	@Column
	private String line1;
	@Column
	private String line2;
	@Column
	private String city;
	@Column
	private String country;
	@Column
	private String zip_code;
	
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
		
	}	

}

//class Address {
//	
//	private String line1;
//
//	public String getLine1() {
//		return line1;
//	}
//
//	public void setLine1(String line1) {
//		this.line1 = line1;
//	}
//	
	
//}
