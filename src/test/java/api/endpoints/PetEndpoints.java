package api.endpoints;

import api.payloads.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;

public class PetEndpoints
{
    public static Response uploadPetImg(int petId)
    {
        File petImg = new File("/Users/taras/Documents/Personal/Learn/RestAssured/restAssTestNG/FrameworkProjectV2/imgToTest.jpg");

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.MULTIPART)
                        .multiPart("file",petImg)
                        .formParam("additionalMetadata","additionalMetadataText")
                        .pathParam("petId",petId)
                        .body(petImg)
                        .post(Routes.postPetImgUrl);

        return response;
    }

    public static Response addPet(Pet payload)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(Routes.postPetUrl);
        return response;
    }

    public static Response updatePet(Pet payload)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .put(Routes.updatePetUrl);
        return response;
    }

    public static Response getPet(int petId)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("petId",petId)
                        .when()
                        .get(Routes.getPetUrl);
        return response;
    }

    public static Response getPetsByStatus(String status)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .queryParam("status",status)
                        .when()
                        .get(Routes.getPetByStatusPetUrl);
        return response;
    }

    public static Response deletePet(int petId)
    {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("petId",petId)
                        .when()
                        .delete(Routes.deletePetUrl);
        return response;
    }

}
