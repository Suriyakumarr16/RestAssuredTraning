package OAuth;
import static io.restassured.RestAssured.*;


import io.restassured.path.json.JsonPath;

public class OAuth2 {

	public static void main(String[] args) throws InterruptedException {
	
			
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("stester1698@gmail.com");
//		driver.findElement(By.xpath("//div[@id='identifierNext']//button")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Password1698@");
//		driver.findElement(By.xpath("//div[@id='passwordNext']//button")).click();
//		Thread.sleep(4000);
//		String OAuthURL = driver.getCurrentUrl();
//		String spliturl = OAuthURL.split("=")[1];
		String code = "4%2F0Ab_5qlmG95eEpibKfZFC4DkfBnWwufIXYKEsi_uyddTB14I_9azq4jtbxBZlDF6ttDkwmg";
		
		
	
		String accessTokenresponse = given().urlEncodingEnabled(false).queryParam("code", code)
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenresponse);
		String accesstoken = js.get("access_token");
		System.out.println(accesstoken);
		
		
		String response = given().queryParam("access_token", accesstoken).when().log().all().post("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
			
	}

}
