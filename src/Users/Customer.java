package Users;

public class Customer extends User {
   private ShoppingCart cart;


    public Customer(String id, String username, String password, String email) {
        super(id, username, password, email, "Customer");
        this.cart = new ShoppingCart();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
