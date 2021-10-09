package steps;

import java.io.File;

import org.json.JSONObject;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.testng.asserts.Assertion;

import com.google.gson.JsonObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import payloads.*;

public class PostRestAPISteps {

	RequestSpecification request;
	Response response;

	@Given("A post Request with some parameters")
	public void a_post_request_with_some_parameters() {
		RestAssured.baseURI = "https://reqres.in/";
		request = RestAssured.given().log().all();
		request.queryParam("page", 2);
	}

	@When("I call Rest assured post method with request payload to create user {string} and job as {int}")
	public void i_call_rest_assured_post_method_with_reques_payload_to_create_user(String name, int job) {
		// Way 1 - use JSONObject for Request Payload
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("name", "morpheus");
//		requestParams.put("job", "leader");
		
//		response = request.post("");
		
		
		// Way 2 - Create a json file, add req payload to it and pass in request.body(file)
		
		PythonInterpreter pyInter = new PythonInterpreter();
		pyInter.execfile(System.getProperty("user.dir") + "\\src\\test\\java\\payloads\\test.py");
		pyInter.set("str1", name);
		pyInter.set("str2", job);
		PyObject requestParams = pyInter.eval("getData(str1, str2)");
		
//		File requestParams = new File(System.getProperty("user.dir") + "\\src\\test\\java\\payloads\\postUser.json");
		request.body(requestParams);
		response = request.post("api/users");
	}

	@Then("I should be able to create a user successfully {string}")
	public void i_should_be_able_to_create_a_user_successfully(String name) {
		Assert.assertEquals(201, response.getStatusCode());
		// Response object contains raw format, asString used to convert to string
		String responseBody = response.asString();
		System.out.println("responseBody --->" + responseBody);
	}

}
