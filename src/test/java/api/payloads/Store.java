package api.payloads;

public class Store
{
    int id;
    int petId;
    int quantity;
    String shipDate;
    String status;
    boolean complete;

    public Store(){}

    public Store(int id, int petId, int quantity, String shipDate, String status, boolean complete)
    {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public String getShipDate()
    {
        return shipDate;
    }

    public void setShipDate(String shipDate)
    {
        this.shipDate = shipDate;
    }

    public int getId()
    {
        return id;
    }
    public String getIdAsString()
    {
        return String.valueOf(id);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPetId()
    {
        return petId;
    }

    public void setPetId(int petId)
    {
        this.petId = petId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

}
