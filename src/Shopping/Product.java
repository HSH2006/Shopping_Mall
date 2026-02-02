package Shopping;

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private int number;

    public Product(String id, String name, String category, double price, int number) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean decreaseProduct(int decrease) {
        if (decrease <= number) {
            number -= decrease;
            return true;
        }
        return false;
    }

    public void increaseProduct(int increaseM) {
        number += increaseM;
    }
}
