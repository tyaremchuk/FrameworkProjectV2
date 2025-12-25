package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints
{
    public static Response getInventory()
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get(Routes.storeGetInventoriesUrl);
        return response;
    }

    public static Response createOrder(Store payload)
    {
        Response response = given()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(Routes.storePostUrl);

        return response;
    }

    public static Response readOrder(String orderId)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("orderId",orderId)
                        .when()
                        .get(Routes.storeGetOrderUrl);
        return response;
    }

    public static Response deleteOrder(String orderId)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("orderId",orderId)
                .when()
                        .delete(Routes.storeDeleteUrl);
        return response;
    }
}
