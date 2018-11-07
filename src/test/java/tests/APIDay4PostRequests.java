package tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.prestashop.beans.Country;
import com.prestashop.beans.CountryResponse;
import com.prestashop.beans.Region;
import com.prestashop.beans.RegionResponse;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay4PostRequests {
	
	@Test
	public void postNewRegion() {
		
		//String requestJson = "{\"region_id\" : 7,\"region_name\" : \"Agdash\"}";
		
		Map requestMap = new HashMap<>();
		requestMap.put("region_id", 5555.0);
		requestMap.put("region_name", "Rashad's Region");
		
		Response response = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.and().body(requestMap)
		.when().post("http://18.220.228.122:1000/ords/hr/regions/");
		
		System.out.println(response.statusLine());
		response.prettyPrint();
		assertEquals(response.statusCode(), 201);
		
		Map responseMap = response.body().as(Map.class);
		
		//assertEquals(responseMap, requestMap); did not work
		assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));
		assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
	}
	
	
	@Test
	public void postUsingPOJO() {
		String url = "http://18.220.228.122:1000/ords/hr/regions/";
		
		Region region = new Region();
		region.setRegion_id(new Random().nextInt(999999));
		region.setRegion_name("murodil's region");
		
		Response response = given().log().all()
							.accept(ContentType.JSON)
						   .and().contentType(ContentType.JSON)
						   .and().body(region)
						   .when().post(url);
	
		assertEquals(response.statusCode(),201);
		
		RegionResponse responseRegion = response.body().as(RegionResponse.class);
		
		//And response body should match request body
		//region id and region name must match
		assertEquals(responseRegion.getRegion_id(),region.getRegion_id());
		assertEquals(responseRegion.getRegion_name(),region.getRegion_name());
		
	}
	
	@Test
	public void postCountryUsingPojo() {
		String url = "http://18.220.228.122:1000/ords/hr/countries/";
		
		Country reqCountry = new Country();
		reqCountry.setCountry_id("AZ");
		reqCountry.setCountry_name("Azerbaijan");
		reqCountry.setRegion_id(4);
		
		Response response = given().log().all()
				.accept(ContentType.JSON)
			   .and().contentType(ContentType.JSON)
			   .and().body(reqCountry)
			   .when().post(url);
		
		assertEquals(response.getStatusCode(),201);
		
		CountryResponse resCountry = response.body().as(CountryResponse.class);
		
		assertEquals(resCountry.getCountry_id(),reqCountry.getCountry_id()); 
		assertEquals(resCountry.getCountry_name(),reqCountry.getCountry_name()); 
		assertEquals(resCountry.getRegion_id(),reqCountry.getRegion_id());

	}
	

}
