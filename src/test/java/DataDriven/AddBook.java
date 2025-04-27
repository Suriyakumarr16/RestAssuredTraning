package DataDriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddBook {
	
	
	public static void main(String[] args) throws IOException {
		DataDriven d = new DataDriven();
		ArrayList<String> list = d.getData("RestAPI","RestAssured");
		HashMap<String,String> JsonAsMap = new HashMap<>();
		JsonAsMap.put("name", list.get(1));
		JsonAsMap.put("isbn", list.get(2));
		JsonAsMap.put("aisle", list.get(3));
		JsonAsMap.put("author", list.get(4));
		
			RestAssured.baseURI="http://216.10.245.166";
			String response = given().log().all().header("Content-Type","application/json").body(JsonAsMap).when().post("Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();
			JsonPath js = new JsonPath(response);
			System.out.println(js.get("ID").toString());
			
		}

}
