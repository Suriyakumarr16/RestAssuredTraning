package graphQL;
import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQL {

	public static void main(String[] args)
	{
		
		//Query
		String response = given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"query($characterId : Int!,$episodeId : Int!){\\n  character(characterId: $characterId)\\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: 20358)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name:\\\"Rahul\\\"})\\n  {\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      type\\n      id\\n      location {\\n        id\\n      }\\n    }\\n  }\\n  episodes(filters: {episode:\\\"hulu\\\"})\\n  {\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":14180,\"episodeId\":14196}}")
        .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		JsonPath js = new JsonPath(response);
		String characterName = js.getString("data.character.name");
		Assert.assertEquals(characterName, "Tony Stark");
		
		//Mutation
		String location = "Pondy";
		String mutationResponse = given().log().all().header("Content-Type","application/json").body("{\"query\":\"mutation($locationName: String!,$characterName: String!,$episodeName: String!){\\n  createLocation(location: {name:$locationName,type:\\\"UT\\\",dimension:\\\"365\\\"})\\n  {\\n   id \\n  }\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Steel\\\",status:\\\"reincarnated\\\",species:\\\"rare\\\",gender:\\\"sigmamale\\\",image:\\\"png\\\",originId:205,locationId:108})\\n  {\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:$episodeName,air_date:\\\"29-09\\\",episode:\\\"2\\\"})\\n  {\\n    id\\n  }\\n}\",\"variables\":{\"locationName\":\""+location+"\",\"characterName\":\"Tony Stark\",\"episodeName\":\"D&D\"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
		System.out.println(mutationResponse);
	}
	
}
