package api.test;

import api.endpoints.StoreEndpoints;
import api.payloads.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StoreTests
{
    Faker faker;
    Store storePayload;

    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        storePayload = new Store();

        storePayload.setId(faker.number().numberBetween(1,999));
        storePayload.setPetId(faker.number().numberBetween(1,999));
        storePayload.setQuantity(faker.number().numberBetween(1,5));
        storePayload.setShipDate("2025-12-12T17:55:25.890Z");
        storePayload.setStatus("available");
        storePayload.setComplete(false);
    }


    @Test(priority = 1)
    public void testGetInventory()
    {
        Response response = StoreEndpoints.getInventory();
        response.then().log().all();
        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 2)
    public void testPostStoreOrder()
    {
        Response response = StoreEndpoints.createOrder(storePayload);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
        assertThat(response.jsonPath().get("id"),equalTo(storePayload.getId()));
        assertThat(response.jsonPath().get("petId"),equalTo(storePayload.getPetId()));
        assertThat(response.jsonPath().get("quantity"),equalTo(storePayload.getQuantity()));
    }

    @Test(priority = 3)
    public void testGetStoreOrder()
    {
        Response response = StoreEndpoints.readOrder(this.storePayload.getIdAsString());
        response.then().log().all();
        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 4)
    public void testDeleteStoreOrder()
    {
        Response response = StoreEndpoints.deleteOrder(this.storePayload.getIdAsString());
        response.then().log().all();
        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 5)
    public void testDeleteStoreOrderNotFound()
    {
        Response response = StoreEndpoints.deleteOrder(this.storePayload.getIdAsString());
        response.then().log().all();
        assertThat(response.getStatusCode(),equalTo(404));
    }
}
