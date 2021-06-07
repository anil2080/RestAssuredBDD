package steps;

import org.testng.asserts.Assertion;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetRestAPISteps {

	RequestSpecification request;
	Response response;

	@Given("A Get Request with some parameters")
	public void a_get_request_with_some_parameters() {
		RestAssured.baseURI = "https://reqres.in/";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.param("page", 2);
	}
	
	@Given("A Get Request with user parameters")
	public void a_get_request_with_user_parameters() {
		RestAssured.baseURI = "https://reqres.in/";
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.pathParam("userId", "3");
	}

	@When("I call Rest assured Get method with Get URL")
	public void i_call_rest_assured_get_method_with_get_url() {
		response = request.get("api/users");
	}
	
	@When("I call Rest assured Get method with Get URL & path parameter")
	public void i_call_rest_assured_get_method_with_get_url_path_parameter() {
		response = request.get("api/users/{userId}");
	}

	@Then("I should be able to see the related data")
	public void i_should_be_able_to_see_the_related_data() {
		Assert.assertEquals(200, response.getStatusCode());
		// Response object contains raw format, asString used to convert to string
		String responseBody = response.asString();
		System.out.println("responseBody --->" + responseBody);

		// responseBody is a string now, we need to convert this in json to iterate this
		JsonPath json = new JsonPath(responseBody);
		int perPage = json.get("per_page");
		Assert.assertEquals(perPage, 6);
	}
	
	@Then("I should be able to see the user related data")
	public void i_should_be_able_to_see_the_user_related_data() {
		Assert.assertEquals(200, response.getStatusCode());
		// Response object contains raw format, asString used to convert to string
		String responseBody = response.asString();
		System.out.println("responseBody --->" + responseBody);

		// responseBody is a string now, we need to convert this in json to iterate this
		JsonPath json = new JsonPath(responseBody);
		String first_name = json.get("data.first_name");
		Assert.assertEquals(first_name, "Janet");
	}

}
