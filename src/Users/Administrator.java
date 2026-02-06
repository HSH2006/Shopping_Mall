package Users;

import Shopping.Product;

import javax.swing.*;

public class Administrator extends User {

    public Administrator(String username, String password, String email) {
        super(username, password, email, "Administrator");
    }

    public void addProduct(String id, String name, String category, double price, int number, String title, ImageIcon image) {
        Product newProduct = new Product(id, name, category, price, number, title, image);

    }
}
