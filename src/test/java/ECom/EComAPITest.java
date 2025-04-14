package ECom;

import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetails;
import POJO.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class EComAPITest {

	public static void main(String[] args) {
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LoginRequest login = new LoginRequest();
		login.setUserEmail("testerapi@gmail.com");
		login.setUserPassword("Password1");

		RequestSpecification reqspec = given().log().all().spec(req).body(login);

		LoginResponse loginresponse  = reqspec.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		System.out.println(loginresponse.getToken());
		System.out.println(loginresponse.getUserId());
		
		String token = loginresponse.getToken();
		String userID = loginresponse.getUserId();
		
		//add Product
		
		RequestSpecification reqAddProductBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).build();
		RequestSpecification reqAddProduct = given().spec(reqAddProductBase).param("productName", "Book").param("productAddedBy", userID)
		.param("productCategory", "fashion").param("productSubCategory", "shirts")
		.param("productPrice", "11500").param("productDescription", "Addias Originals").param("productFor", "men")
		.multiPart("productImage", new File("C://Users//61096001//Documents//test.png"));
		
		String addProductResponse = reqAddProduct.when().log().all().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");
		System.out.println(productId);
		
		//create Product
		OrderDetails orderdetails = new OrderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productId);
		List<OrderDetails> orderdetailList = new ArrayList<OrderDetails>();
		orderdetailList.add(orderdetails);
		Orders order = new Orders();
		order.setOrders(orderdetailList);
		
		RequestSpecification reqCreateOrderBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",token).setContentType(ContentType.JSON).build();
		RequestSpecification reqCreateOrder = given().log().all().spec(reqCreateOrderBase).body(order);
		String resp = reqCreateOrder.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(resp);
		
		
		//delete Product
		RequestSpecification reqDeleteOrderBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",token).addPathParam("productId",productId).setContentType(ContentType.JSON).build();
		String response = given().log().all().spec(reqDeleteOrderBase).when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
		System.out.println(response);
		JsonPath js1 = new JsonPath(response);
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
	}
}
