package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserTests
{
    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setupData()
    {
        faker = new Faker();

        userPayload = new User();

        userPayload.setId(faker.number().numberBetween(1,1000));
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().username());
        userPayload.setLastName(faker.name().firstName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("***** CREATING USER *****");

        Response response = UserEndpoints.createUser(userPayload);
        response.then().log().all();

        //hamcrest asserts:
        assertThat(response.statusCode(),equalTo(200));
        response.then().statusCode(200).body("code",equalTo(200));

        //testng assert:
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("***** USER IS CREATED *****");

    }

    @Test (priority = 2)
    public void testGetUserByName()
    {
        logger.info("***** READING USER INFO *****");

        Response response = UserEndpoints.readUser(this.userPayload.getUsername());

        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        assertThat(response.jsonPath().getString("username"),equalTo(userPayload.getUsername()));
        assertThat(response.getStatusCode(),equalTo(200));

        response.then().statusCode(200).body("userStatus",equalTo(0));
        //assertThat(response.statusCode(),equalTo(200));

        logger.info("***** USER INFO IS DISPLAYED *****");

    }

    @Test(priority = 3)
    public void testUpdateUserByName()
    {
        logger.info("***** UPDATING USER *****");

        //update data
        userPayload.setFirstName("updatedFirstName");
        userPayload.setLastName("updatedLastName");
        userPayload.setEmail("updatedEmail");

        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        //checking data after update
        Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
        assertThat(responseAfterUpdate.jsonPath().getString("firstName"),equalTo("updatedFirstName"));
        assertThat(responseAfterUpdate.getStatusCode(),equalTo(200));

        logger.info("***** USER IS UPDATED *****");


    }

    @Test(priority = 4)
    public void testDeleteUserByName()
    {
        logger.info("***** DELETING USER *****");

        Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        //hamcrest asserts:
        assertThat(response.statusCode(),equalTo(200));
        //response.then().statusCode(200).body("code",equalTo(200));

        logger.info("***** USER IS DELETED *****");

    }
}
