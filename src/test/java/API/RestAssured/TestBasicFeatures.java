package API.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestBasicFeatures {

	// valid http request method.
	@Test
	public void getResponseCode()
	{
		given().
				get("http://jsonplaceholder.typicode.com/posts").
				then().statusCode(200).log().body();
	}

	// invalid request method.
	@Test
	public void invalidPostRequest()
	{
		given().
				header("contentType","application/image").
				//headers("Accept","none")
				log().all().
				post("http://jsonplaceholder.typicode.com/posts").
				then().statusCode(200).log().all();
	}


    @Test
	public void verifyHasItems()
	{
		given().
				log().all().
				get("http://services.groupkt.com/country/get/all").
				then().body("RestResponse.result.name", hasItems("Afghanistan"));

	}

	@Test
	public void equalToName()
	{
		given().
				log().all().
				get("http://services.groupkt.com/country/get/all").
				then().body("RestResponse.result.name[0]", equalTo("Afghanistan"));
	}

	@Test
	public void useOfParams()
	{
		given().
				// main variable https://jsonplaceholder.typicode.com/users/:id
				//param("id","1").

				// is only present in body. cant search like https://jsonplaceholder.typicode.com/users/:name
				//param("name","Leanne Graham").

				// across levels of hierarchy.
				param("address.geo.lat","-37.3159").
				log().all().
				get("https://jsonplaceholder.typicode.com/users").
				then().statusCode(200).log().all();
	}








}
