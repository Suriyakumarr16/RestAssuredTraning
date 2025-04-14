package API.Resources;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
@Test(dataProvider="bookData")
public void addBook(String ibsn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json").body(Payload.addBook(ibsn,aisle)).when().post("Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		System.out.println(js.get("ID").toString());
		
	}

@DataProvider(name="bookData")
public Object[][] bookData()
{
	return new Object[][] {{"haws","637628"},{"uushj","6722"},{"ywuh","728"}};
}


}
