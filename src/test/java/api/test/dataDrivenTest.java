package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;



public class dataDrivenTest {
	
	
	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String username, String fname,String lname, String useremail, String pwd,String phone) {
	
		User userPayLoad = new User();
		
		userPayLoad.setId(Integer.parseInt(userId));
		userPayLoad.setUsername(username);
		userPayLoad.setFirstName(fname);
		userPayLoad.setLastName(lname);
		userPayLoad.setEmail(useremail);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(phone);
		
		Response response = userEndpoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testdeleteUser(String username) {
	
			
		Response response = userEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
