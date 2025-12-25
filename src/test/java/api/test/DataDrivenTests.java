package api.test;
import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataDrivenTests
{
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph)
    {
        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndpoints.createUser(userPayload);
        assertThat(response.statusCode(),equalTo(200));

        response.then().log().all();
    }

    @Test(priority = 2, dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testGetTestUser(String username)
    {
        Response response = UserEndpoints.readUser(username);
        response.then().log().all();
        assertThat(response.jsonPath().get("username"), equalTo(username));
        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 3, dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testDeleteTestUser(String username)
    {
        Response response = UserEndpoints.deleteUser(username);
        assertThat(response.getStatusCode(),equalTo(200));
    }
}
