package com.prestashop.step_definitions.api;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class API_PostAnEmployee {
	
	RequestSpecification request;
	int employeeId;
	Response response;
	String url = "http://18.220.228.122:1000/ords/hr/employees/";
	Map reqEmployee;
	
	@Given("Content type and Accept type is Json")
	public void content_type_and_Accept_type_is_Json() {
	request = given().accept(ContentType.JSON)
		             .and().contentType(ContentType.JSON);
	}

	@When("I post a new Employee with {string} id")
	public void i_post_a_new_Employee_with_id(String id) {
	   
		if(id.equals("random")){
			employeeId = new Random().nextInt(999999);
		}else {
			employeeId= Integer.parseInt(id);
		}
		
		reqEmployee = new HashMap();
		reqEmployee.put("employee_id", employeeId);
		reqEmployee.put("first_name","POSTNAME");
		reqEmployee.put("last_name","POSTLNAME");
		reqEmployee.put("email","EM"+employeeId);
		reqEmployee.put("phone_number", "202.123.4567");
		reqEmployee.put("hire_date","2018-04-24T07:25:00Z");
		reqEmployee.put("job_id","IT_PROG");
		reqEmployee.put("salary",7000.0);
		reqEmployee.put("commission_pct",null);
		reqEmployee.put("manager_id",null);
		reqEmployee.put("department_id",90.0);
		
		response = request.and().body(reqEmployee)
				          .when().post(url);
	}

	@Then("Status code is {int}")
	public void status_code_is(int statusCode) {
	    assertEquals(response.statusCode(), statusCode);
	}

	@Then("Response Json should contain Employee info")
	public void response_Json_should_contain_Employee_info() {
		Map postResEmployee = response.body().as(Map.class);
		//	And Response Json should contain Employee info
		for(Object key : reqEmployee.keySet()) {
			System.out.println(postResEmployee.get(key)+" <> "+reqEmployee.get(key));
			//assertEquals(postResEmployee.get(key),reqEmployee.get(key));
		}
	}

	@When("I send a GET request with {string} id")
	public void i_send_a_GET_request_with_id(String id) {
		if(!id.equals("random")) {
			employeeId = Integer.parseInt(id);
		}
		response = given().accept(ContentType.JSON)
				   .when().get(url+employeeId);
		}

	@Then("Employee JSON Response Data should match the posted JSON data")
	public void employee_JSON_Response_Data_should_match_the_posted_JSON_data() {
Map getResMap = response.body().as(Map.class);
		
		for(Object key:reqEmployee.keySet()) {
			System.out.println(key + ": " +reqEmployee.get(key) + "<>" +getResMap.get(key));
			assertEquals(getResMap.get(key),reqEmployee.get(key));
		}
	}

}
