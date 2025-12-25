package api.test;

import api.endpoints.PetEndpoints;
import api.payloads.Category;
import api.payloads.Pet;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetTests
{
    Faker faker;
    Pet petPayload;
    Category category;

    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        petPayload = new Pet();

        category = new Category();
        category.setCategoryId(faker.number().numberBetween(0,1));
        category.setCategoryName(faker.animal().name());

        String[] photosUrls = {"ur1","url2"};
        String[] tags = {"tag1","tag2"};

        petPayload.setId(faker.number().numberBetween(1,100));
        petPayload.setCategory(category);
        petPayload.setName(faker.name().firstName());
        petPayload.setPhotoUrls(photosUrls);
        petPayload.setTag(tags);
        petPayload.setStatus("available");

    }

    @Test(priority = 1)
    public void testPostPet()
    {
        Response response = PetEndpoints.addPet(petPayload);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("name"),equalTo(this.petPayload.getName()));
        assertThat(response.jsonPath().getString("status"),equalTo("available"));
    }

    @Test(priority = 2)
    public void testPetImgUpload()
    {
        Response response = PetEndpoints.uploadPetImg(this.petPayload.getId());
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 3)
    public void testGetPetById()
    {
        Response response = PetEndpoints.getPet(this.petPayload.getId());
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("name"),equalTo(this.petPayload.getName()));
    }

    @Test(priority = 4)
    public void testUpdatePet()
    {
        petPayload.setStatus("sold");
        petPayload.setName("nameUpdated");

        Response response = PetEndpoints.updatePet(this.petPayload);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("name"),equalTo("nameUpdated"));
    }

    /*@Test(priority = 5)
    public void testUpdatePetWithFormData()
    {}*/

    @Test(priority = 6)
    public void testFindByStatus()
    {
        Response response = PetEndpoints.getPetsByStatus("sold");
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 8)
    public void testDeletePet()
    {
        Response response = PetEndpoints.deletePet(this.petPayload.getId());
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 9)
    public void testDeleteDeletedPet()
    {
        Response response = PetEndpoints.deletePet(this.petPayload.getId());
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(404));
    }
}
