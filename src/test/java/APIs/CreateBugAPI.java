package APIs;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.path.json.JsonPath;
public class CreateBugAPI {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://tskworld.atlassian.net/";
		String response = given().log().all().header("Content-Type","application/json").header("Authorization","Basic dHN1cml5YWt1bWFyckBnbWFpbC5jb206QVRBVFQzeEZmR0YwTGhJRFRtdUJZc29vLVQ0VjZNTlJrSWQxSmVhMDYxNVNEYVBES3hya19JaVZic0w1SF9uVXNWTGFrZmY3REZyMlhzRFdZOXhvMDRVQWxoYnUza2RwZVZSMm41QWJELU5KQmxVcng5c1lIaHJrVXZoXzkyel9RdF9aRWJLQldfMlpzT0VvVzJMOGdSbjlrN2JFYVJ1SGhfb0dFTzVKb0hyaHVDaXhZSUp0Mzl3PTJCNERFNjlD")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"summa rest la oru bug-u\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").when().post("rest/api/3/issue").then().statusCode(201).extract().response().asString();
		
	JsonPath js = new JsonPath(response);
	String issueID = js.getString("id");
	
	given().log().all().pathParam("key",issueID).header("X-Atlassian-Token","no-check").header("Authorization","Basic dHN1cml5YWt1bWFyckBnbWFpbC5jb206QVRBVFQzeEZmR0YwTGhJRFRtdUJZc29vLVQ0VjZNTlJrSWQxSmVhMDYxNVNEYVBES3hya19JaVZic0w1SF9uVXNWTGFrZmY3REZyMlhzRFdZOXhvMDRVQWxoYnUza2RwZVZSMm41QWJELU5KQmxVcng5c1lIaHJrVXZoXzkyel9RdF9aRWJLQldfMlpzT0VvVzJMOGdSbjlrN2JFYVJ1SGhfb0dFTzVKb0hyaHVDaXhZSUp0Mzl3PTJCNERFNjlD")
	.multiPart("file",new File("/Users/Modern/Pictures/JIRA_test.png")).when().post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	
	}

}
