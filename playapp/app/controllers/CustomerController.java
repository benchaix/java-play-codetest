package controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import models.Customer;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@Api(value = "/customers")
public class CustomerController extends Controller {

	ObjectMapper mapper = new ObjectMapper();

	@ApiOperation(value = "Sort a customer list by due time", response = Customer.class, httpMethod = "POST", consumes = "application/json", produces = "application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "body", required = true, paramType = "body", allowMultiple = true, dataType = "array") })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid array supplied") })
	public Promise<Result> sort() {
		JsonNode json = request().body().asJson();
		return Promise.promise(() -> sortCustomers(json)).map((customerList) -> ok(Json.toJson(customerList)));
	}

	/**
	 * Retrieve a sorted customer list
	 * 
	 * @param json
	 * @return List
	 */
	private List<Customer> sortCustomers(JsonNode json) {
		try {
			List<Customer> customerList = mapper.readValue(json.toString(), new TypeReference<List<Customer>>() {
			});
			customerList.sort((r1, r2) -> r1.getDeserializedDueTime().compareTo(r2.getDeserializedDueTime()));
			return customerList;
		} catch (IOException ex) {
			return null;
		}
	}
}