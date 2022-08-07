public class Cars {

    private int id;
    private String manufacturer;
    private String model;
    private int yearOfProducing;
    private String color;
    private double price;
    private int ownerId;

    public Cars(int id, String manufacturer, String model, int yearOfProducing, String color, double price, int ownerId) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfProducing = yearOfProducing;
        this.color = color;
        this.price = price;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfProducing() {
        return yearOfProducing;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public int getOwnerId() {
        return ownerId;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYearOfProducing(int yearOfProducing) {
        this.yearOfProducing = yearOfProducing;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Car [id= " + id + ", manufacturer + " + manufacturer + ", model = " + model + ", year of producing = " + yearOfProducing + ", color = " + color
                + ", price = " + price + "owner ID" + ownerId
                + "]";
    }
}
