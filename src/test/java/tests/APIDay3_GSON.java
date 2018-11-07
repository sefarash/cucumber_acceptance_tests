package tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay3_GSON {

	@Test
	public void testWithJsonToHashMap() {
		Response response = given().accept(ContentType.JSON).when()
				.get("http://34.223.219.142:1212/ords/hr/employees/120");
		HashMap<String, String> map = response.as(HashMap.class);
		System.out.println(map.keySet());
		System.out.println(map.values());
		assertEquals(map.get("employee_id"), 120.0);
		assertEquals(map.get("job_id"), "AC_MGR");
	}

	@Test
	public void convertJsonListOfMaps() {
		Response response = given().accept(ContentType.JSON).when()
				.get("http://34.223.219.142:1212/ords/hr/departments");

		// convert the response that contains department information info list of maps

		List<Map> listOfMaps = response.jsonPath().getList("items", Map.class);

		System.out.println(listOfMaps.get(0));

		// assert that first department name as "IT department"
		assertEquals(listOfMaps.get(0).get("department_name"), "IT department");
		assertEquals(listOfMaps.get(0).get("department_id"), 539);
	}

	@Test
	public void warmUpTask() {
		Map<String,Integer> mapParam = new HashMap<>();
		mapParam.put("limit", 10);
		
		Response response = given().accept(ContentType.JSON)
				            .and().params(mapParam)
		                    .when().get("http://34.223.219.142:1212/ords/hr/regions");
		
		assertEquals(response.statusCode(),200);
		
		io.restassured.path.json.JsonPath json = response.jsonPath();
		
		List<Map> mapRegions = response.jsonPath().getList("items",Map.class);
		System.out.println(mapRegions);
		Map<Integer,String> expectedRegions = new HashMap<>();
		
				
		        expectedRegions.put(61, "Mening yurtim Uzb");
				expectedRegions.put(98765, "kan savas aci gozyasi");
				expectedRegions.put(987654, "TESTINSERT");
				expectedRegions.put(98764, "KAN SAVAS ACI GOZYASI");
		//assertEquals(mapRegions.get(0).get("region_id"),61);
		//assertEquals(mapRegions.get(0).get("region_name"), "Mening yurtim Uzb");
				
//				for (int i = 0; i < mapRegions.size(); i++) {
//					assertEquals(mapRegions.get(i).get("region_id"), );
//				}
		
		
		
		
		
	}

}
