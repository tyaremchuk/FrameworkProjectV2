package api.payloads;

/*"id": 9223372036854776000,
        "category": {
        "id": 0,
        "name": "string"
        },
        "name": "doggie",
        "photoUrls": [
        "string"
        ],
        "tags": [
        {
        "id": 0,
        "name": "string"
        }
        ],
        "status": "available"*/


public class Pet
{
    int id;
    Category category;
    String name;
    String[] photoUrls;
    String[] tag;
    String status;

    public Pet(){}

    public Pet(int id, Category category, String name, String[] photoUrls, String[] tag, String status)
    {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tag = tag;
        this.status = status;
    }

    public String[] getPhotoUrls()
    {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls)
    {
        this.photoUrls = photoUrls;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getTag()
    {
        return tag;
    }

    public void setTag(String[] tag)
    {
        this.tag = tag;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }




}
