package tests;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HRRestAPIGetRequests {
	
	@Test
	public void simpleGet() {
		when().get("http://34.223.219.142:1212/ords/hr/employees")
		.then()
		.statusCode(200);
		
	}
	
	@Test
	public void printResponse() {
		when().get("http://34.223.219.142:1212/ords/hr/employees")
		.andReturn()
		.body()
		.prettyPrint();
	}
	
	@Test
	public void getWithHeaders() {
		with().accept(ContentType.JSON).when().get("http://34.223.219.142:1212/ords/hr/countries/US")
		.then().statusCode(200);
	}
	
	@Test
	public void negativeGet() {
		when().get("http://34.223.219.142:1212/ords/hr/employees/1234")
		.then()
		.statusCode(404);
		Response response = when().get("http://34.223.219.142:1212/ords/hr/employees/1234");
		assertEquals(response.statusCode(), 404);
		assertTrue(response.asString().contains("Not Found"));
		response.prettyPrint();
	}
	
	@Test
	public void VerifyContentTypeWithAssertThat() {
		String url = "http://34.223.219.142:1212/ords/hr/employees/100";
		
		with().accept(ContentType.JSON).when().get(url)
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.XML);
	}
	
	@Test
	public void verifyFristName() throws URISyntaxException {
		
		URI uri = new URI("http://34.223.219.142:1212/ords/hr/employees/100");
		
		
		with().accept(ContentType.JSON).when().get(uri)
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("first_name", Matchers.equalTo("Ahmet"))
		.and().assertThat().body("employee_id", Matchers.equalTo(100));
		
	}

}
