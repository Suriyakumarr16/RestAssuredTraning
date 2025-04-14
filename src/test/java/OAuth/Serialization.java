package OAuth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class Serialization {

	public static void main(String[] args) {
		AddPlace addplace = new AddPlace();
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		addplace.setLocation(loc);
		addplace.setAccuracy(50);
		addplace.setName("Frontline house");
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setAddress("29, side layout, cohen 09");
		List<String> list = new ArrayList<>();
		list.add("shoe park");
		list.add("shop");
		addplace.setTypes(list);
		addplace.setWebsite("http://google.com");
		addplace.setLanguage("French-IN");

		// SpecBuilders and Serialization
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").build();
		RequestSpecification request = given().spec(req).body(addplace);

		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		String response = request.post("/maps/api/place/add/json").then().log().all().spec(resp).extract().response()
				.asString();
		System.out.println(response);
	}

}
