package Shopping;

import javax.swing.*;

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private int number;
    private String title;
    private ImageIcon image;

    public Product(String id, String name, String category, double price, int number, String title, ImageIcon image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.number = number;
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public ImageIcon getImage() {
        return image;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
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
