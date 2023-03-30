package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayLoad;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayLoad = new User();
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5,10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testpostUser()
	{
		Response response = userEndpoints.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Test(priority=2)
	public void testreadUser()
	{
		Response response = userEndpoints.readUser(this.userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Test(priority=3)
	public void testupdateUser()
	{
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response = userEndpoints.updateUser(this.userPayLoad.getUsername(),userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseafterUpdate = userEndpoints.readUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Test(priority=4)
	public void testdeleteUser()
	{
		Response response = userEndpoints.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	
}
