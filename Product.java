package Nikon;

public class Product {
    private int id;
    private String name;
    private String price;
    private String details;

    public Product(int id, String name, String price, String details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }
}