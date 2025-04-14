package API.Resources;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payload.coursePrice());
		int count = js.getInt("courses.size()");
		int sum=0;
		for(int i=0;i<count;i++)
			{
			sum=sum+(js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies"));
			}
		if(js.getInt("dashboard.purchaseAmount")==sum)
		{
			System.out.println("valid");
		}
	}

}
