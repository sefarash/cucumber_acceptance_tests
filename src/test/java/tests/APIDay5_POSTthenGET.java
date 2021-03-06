package tests;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class APIDay5_POSTthenGET {
	
		/*
		Given Content type and Accept type is Json
		When I post a new Employee with "555" id
		Then Status code is 201
		And Response Json should contain Employee info
		When i send a GET request with "555" id 
		Then status code is 200
		And employee JSON Response Data should match the posted JSON data
		*/
		
		/*
		 * AD_PRES
			AD_VP
			AD_ASST
			FI_MGR
			FI_ACCOUNT
			AC_MGR
			AC_ACCOUNT
			SA_MAN
			SA_REP
			PU_MAN
			PU_CLERK
			ST_MAN
			ST_CLERK
			SH_CLERK
			IT_PROG
			MK_MAN
			MK_REP
			HR_REP
			PR_REP
		 */
		
	@Test
	public void PostEmployeeThenGetEmployee() {
		
//		String url = "http://18.220.228.122:1000/ords/hr/employees/";
		Faker faker = new Faker();
//		int randomID = new Random().nextInt(999999);
//		Map reqEmployee = new HashMap<>();
//		reqEmployee.put("employee_id", randomID);
//		reqEmployee.put("first_name", "Sefa");
//		reqEmployee.put("last_name", "Omarova");
//		reqEmployee.put("email", faker.internet().emailAddress());
//		reqEmployee.put("phone_number", faker.phoneNumber().cellPhone());
//		reqEmployee.put("hire_date", faker.date());
//		reqEmployee.put("salary", faker.number().numberBetween(10000, 100000));
//		reqEmployee.put("commission_pct", null);
//		reqEmployee.put("manager_id", null);
//		reqEmployee.put("department_id", 90);
		
		int randomID = new Random().nextInt(999999);
		String url = "http://18.220.228.122:1000/ords/hr/employees/";

		Map reqEmployee = new HashMap();
		reqEmployee.put("employee_id", randomID+"."+0);
		reqEmployee.put("first_name","POSTNAME");
		reqEmployee.put("last_name","POSTLNAME");
		reqEmployee.put("email",faker.internet().emailAddress());
		reqEmployee.put("phone_number", "202.123.4567");
		reqEmployee.put("hire_date","2018-04-24T07:25:00Z");
		reqEmployee.put("job_id","IT_PROG");
		reqEmployee.put("salary",7000.0);
		reqEmployee.put("commission_pct",null);
		reqEmployee.put("manager_id",null);
		reqEmployee.put("department_id",90.0);
		
		//Given Content type and Accept type is Json
		Response response = given().accept(ContentType.JSON)
							.and().contentType(ContentType.JSON)
							.and().body(reqEmployee)
							.when().post(url);
		
		assertEquals(response.statusCode(),201);
		
		Map postResEmployee = response.body().as(Map.class);
		//	And Response Json should contain Employee info
		for(Object key : reqEmployee.keySet()) {
			System.out.println(postResEmployee.get(key)+" <> "+reqEmployee.get(key));
			//assertEquals(postResEmployee.get(key),reqEmployee.get(key));
		}
		
		/*When i send a GET request with "1100" id 
		Then status code is 200
		And employee JSON Response Data should match the posted JSON data
		*/
		response = given().accept(ContentType.JSON)
				   .when().get(url+randomID);
		
		assertEquals(response.statusCode(),200);
		
		Map getResMap = response.body().as(Map.class);
		
		for(Object key:reqEmployee.keySet()) {
			System.out.println(key + ": " +reqEmployee.get(key) + "<>" +getResMap.get(key));
			assertEquals(getResMap.get(key),reqEmployee.get(key));
		}
		
	}

}
