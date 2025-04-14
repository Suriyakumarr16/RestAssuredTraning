package OAuth;
import static io.restassured.RestAssured.*;

import POJO.GetCourse;
import io.restassured.path.json.JsonPath;

public class OAuthTest {
	
	
	public static void main(String[] args)
	{
		String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		given().formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		given().formParams("grant_type", "client_credentials").
		given().formParams("scope", "trust").when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
	
		JsonPath js = new JsonPath(response);
		String accesstoken = js.getString("access_token");
		
		//De-serialization
		GetCourse gc = given().baseUri("https://rahulshettyacademy.com/oauthapi/getCourseDetails").queryParam("access_token", accesstoken).when().log().all().get().as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		for(int i =0;i<gc.getCourses().getApi().size();i++) {
			if(gc.getCourses().getApi().get(i).getCourseTitle().contains("Rest"))
		System.out.println(gc.getCourses().getApi().get(i).getPrice());
		}
		
	}

}
