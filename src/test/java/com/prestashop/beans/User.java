package com.prestashop.beans;
// beans  --> used to represent a certain type of object

// pojo  --> plain old java object,  used to represent a certain type of object
// model   --> used to represent a certain type of object

public class User {

	private String firstName;
	private String lastName;
	private String address;
	private String company;
	private String city;
	private String password;
	private String zipcode;
	private String state;
	private String phone;
	private String email;

	public User(String firstName, String lastName, String email, String phone, String company, String address,
			String city, String password, String zipcode, String state) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.company = company;
		this.city = city;
		this.password = password;
		this.zipcode = zipcode;
		this.state = state;
		this.phone = phone;
	}

	public String getFistName() {
		return firstName;
	}

	public void setFistName(String fistName) {
		this.firstName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
