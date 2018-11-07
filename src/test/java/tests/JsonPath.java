package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;


import io.restassured.response.Response;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.prestashop.utilities.ConfigurationReader;

import io.restassured.http.ContentType;

public class JsonPath {
	
	/*
	 * Given Accept type is JSON
	 * When I send a get request to REST URL
	 * http://34.223.219.142:1212/ords/hr/regions
	 * the status code is 200
	 * And Response content should be json
	 *And 4 regions should be returned
	 * 
	 * And Americas is one of the region name
	 */
	
	@Test
	public void testItemsCountFromResponseBody() {
		given().accept(ContentType.JSON)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions")
		.then().assertThat().statusCode(200)
		.and().assertThat().contentType(ContentType.JSON)
		.and().assertThat().body("items.region_id",hasSize(25))
		.and().assertThat().body("items.region_name", hasItems("Americas","Asia"))
	;
	}
	
	@Test
	public void testWithQueryParameterAndList() {
		given().accept(ContentType.JSON)
		.and().params("limit", 100)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees")
		.then().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("items.employee_id", hasSize(100))
		;

	}
	@Test
	public void testWithPathParameter() {
//		given().accept(ContentType.JSON)
//		.and().params("limit", 100)
//		.and().pathParams("employee_id",110)
//		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/{employee_id}")
//		.then().statusCode(200)
//		.and().contentType(ContentType.JSON)
//		.and().assertThat().body("employee_id", equalTo(110),
//				"first_name", equalTo("John"),"last_name", equalTo("Chen"),"email",equalTo("JCEN"));
		 given().accept(ContentType.JSON)
				.and().pathParams("employee_id", 7222)
		.when().get("http://34.223.219.142:1212/ords/hr/employees/{employee_id}")
		.then().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("employee_id",	equalTo(7222),
				                 "first_name",equalTo("Peter"),
				                 "last_name",equalTo("Parker"),
				                 "email",equalTo("PP"));
		
		//response.body().prettyPrint();
	}
	
	@Test
	public void testWithJsonPath() {
		Map<String,Integer> rParamMap = new HashMap<>();
		rParamMap.put("limit", 100);
		
		Response response = given().accept(ContentType.JSON)
				            .and().params(rParamMap)
				            .and().pathParams("employee_id",7222)
				            .when().get("http://34.223.219.142:1212/ords/hr/employees/{employee_id}");
		
		io.restassured.path.json.JsonPath json = response.jsonPath();
		System.out.println(json.getInt("employee_id"));
		System.out.println(json.getString("first_name"));
		System.out.println(json.getString("last_name"));
		System.out.println(json.getInt("salary"));
		System.out.println(json.getString("links[2].href"));
		List<String> hrefs = json.getList("links.href");
		System.out.println(hrefs);
		
	}
	
	@Test
	public void TestJsonPathWithLists() {
		Map<String,Integer> rParamMap = new HashMap<>();
		rParamMap.put("limit", 100);
		
		Response response = given().accept(ContentType.JSON)
				            .and().params(rParamMap)
				            .when().get("http://34.223.219.142:1212/ords/hr/employees");
		
		assertEquals(response.statusCode(),200);
		
		io.restassured.path.json.JsonPath json = response.jsonPath();
		//JsonPath json = new JsonPath(new File(FilePath.json));
		//JsonPath json = new JsonPath(response.asString());
		//get all employees ids into an arraylist
		List<Integer> empIds = json.getList("items.employee_id");
		System.out.println(empIds);
		//assert that there are 100 emp ids
		assertEquals(empIds.size(),100);
		//get all emails and assign into arraylist
		List<String> empEmails = json.getList("items.email");
		System.out.println(empEmails);
		assertEquals(empEmails.size(),100);
		
		//get all employee ids that are greater than 150
		List<String> empIdList = json.getList("items.findAll{it.employee_id>150}.employee_id");
		System.out.println(empIdList);
		//get all employee lastnames, whose salary is more than 7000
		List<Integer> empSalaryList = json.getList("items.findAll{it.salary>7000}.salary");
		List<String> empLNList = json.getList("items.findAll{it.salary>7000}.last_name");
		System.out.println(empSalaryList);
		System.out.println(empLNList);
		
		
	}

}
