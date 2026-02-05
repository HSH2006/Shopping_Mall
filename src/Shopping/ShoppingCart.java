package Shopping;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<ProductInShoppingCart> products;
    private double price;

    public ShoppingCart() {
        this.products = new ArrayList<>();
        this.price = 0.0;
    }

    public void add(Product product) {
        if (product.getNumber() == 0) {
            throw new IllegalArgumentException("Mojodi nadrim.");
        }

        for (ProductInShoppingCart product2 : products)
            if (product2.getProduct().getId().equals(product.getId())) {
                product2.add();
                calculatePrice();
                return;
            }

        ProductInShoppingCart newProduct = new ProductInShoppingCart(product);
        products.add(newProduct);
        calculatePrice();
    }

    public void remove(String id) {
        for (ProductInShoppingCart product : products)
            if (product.getProduct().getId().equals(id)) {
                product.remove();
                if (product.getNumberInShoppingCart() == 0)
                        products.remove(product);
                calculatePrice();
                return;
            }
    }

    public void clear() {
        products.clear();
        price = 0.0;
    }

    private void calculatePrice() {
        price = 0.0;
        for (ProductInShoppingCart product : products)
                price += ((double) product.getNumberInShoppingCart()) * product.getProduct().getPrice();
    }

    public List<ProductInShoppingCart> view() {
        return products;
    }
    public double getPrice() {
        return price;
    }
}
