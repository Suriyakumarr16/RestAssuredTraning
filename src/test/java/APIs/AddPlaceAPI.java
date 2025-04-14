package APIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;




public class AddPlaceAPI {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI="https://rahulshettyacademy.com";
String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
.body("{\r\n"
		+ "\"location\": {\r\n"
		+ "\"lat\": -38.383494,\r\n"
		+ "\"lng\": 33.427362\r\n"
		+ "},\r\n"
		+ "\"accuracy\": 50,\r\n"
		+ "\r\n"
		+ "\"name\": \"Frontline house\",\r\n"
		+ "\"phone_number\": \"(+91) 983 893 3937\",\r\n"
		+ "\"address\": \"29, side layout, cohen 09\",\r\n"
		+ "\"types\": [\r\n"
		+ "\"shoe park\",\r\n"
		+ "\"shop\"\r\n"
		+ "],\r\n"
		+ "\"website\": \"http://google.com\",\r\n"
		+ "\"language\": \"French-IN\"\r\n"
		+ "}").when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
	System.out.println(response);
	JsonPath js = new JsonPath(response);
	String placeID = js.getString("place_id");
	String newaddress = "70 winter walk, USA";
	System.out.println(placeID);
	
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
			+ "\"place_id\":\""+placeID+"\",\r\n"
			+ "\"address\":\""+newaddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

	
	String getPlace = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).when().get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();
	JsonPath js1 = new JsonPath(getPlace);
	Assert.assertEquals(js1.getString("address"), newaddress);
	
	
	}
}