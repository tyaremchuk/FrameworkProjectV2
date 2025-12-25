package api.endpoints;

public class Routes
{
    public static String baseUrl = "https://petstore.swagger.io/v2";

    //User model
    public static String postUrl = baseUrl+"/user";
    public static String getUrl = baseUrl+"/user/{username}";
    public static String updateUrl = baseUrl+"/user/{username}";
    public static String deleteUrl = baseUrl+"/user/{username}";

    //Pet model
    public static String postPetImgUrl = baseUrl+"/pet/{petId}/uploadImage";
    public static String postPetUrl = baseUrl+"/pet";
    public static String updatePetUrl = baseUrl+"/pet";
    public static String getPetByStatusPetUrl = baseUrl+"/pet/findByStatus";
    public static String getPetUrl = baseUrl+"/pet/{petId}";
    public static String updatePetWithFormDataUrl = baseUrl+"/pet/{petId}";
    public static String deletePetUrl = baseUrl+"/pet/{petId}";


    //Store model
    public static String storePostUrl = baseUrl+"/store/order";
    public static String storeGetInventoriesUrl = baseUrl+"/store/inventory";
    public static String storeGetOrderUrl = baseUrl+"/store/order/{orderId}";
    public static String storeDeleteUrl = baseUrl+"/store/order/{orderId}";

}
