import Catalog.Catalog;
import Shopping.Product;
import Shopping.ProductInShoppingCart;
import Shopping.ShoppingCart;
import Users.Administrator;
import Users.Customer;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;




public class Main {
    private static Map<String, Administrator> administrators = new HashMap<>();
    private static Map<String, Customer> customers = new HashMap<>();
    private static Catalog catalog = new Catalog();
    public static void main(String[] args) {
        Product[] products = {
                new Product("Coffee",  100,2, new ImageIcon("C:/Users/home/Downloads/12.jpg")),
                new Product( "hot chocolate",  200, 3, new ImageIcon("C:/Users/home/Downloads/13.jpg")),
                new Product("nescafe", 300, 1, new ImageIcon("C:/Users/home/Downloads/14.jpg")),
        };

        for (Product product : products)
            catalog.addProduct(product);
        Login();
    }

    static class AuthenticationService {

        public AuthenticationService() {
            administrators.put("admin1", new Administrator("admin1", "adminpass", "admin1@example.com", "ADM001"));
            administrators.put("admin2", new Administrator("admin2", "adminpass2", "admin2@example.com", "ADM002"));
            customers.put("user3", new Customer("user3", "pass3", "user3@example.com"));
        }

        public User authenticate(String username, String password, String userType) {
            if (userType.equals("admin")) {
                if (administrators.containsKey(username)) {
                    Administrator admin = administrators.get(username);
                    if (admin.getPassword().equals(password)) {
                        return admin;
                    }
                }
                else
                    return null;
            }
            if (customers.containsKey(username)) {
                Customer customer = customers.get(username);
                if (customer.getPassword().equals(password))
                    return customer;
            }
            return null;
        }
    }



    private static void Adminfront(Object admin) {
        JFrame adminFrame = new JFrame("Admin Panel");

        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(400, 400);
        adminFrame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome Admin: " + admin.toString());
        adminFrame.add(label, BorderLayout.NORTH);

        JButton addProductButton = new JButton("Add Product");
        JButton removeProductButton = new JButton("Remove Product");
        JButton editTitleProductButton = new JButton("Edit Title Product");
        JButton editPriseProductButton = new JButton("Edit Prise Product");
        JButton editQuantityProductButton = new JButton("Edit Quantity Product");
        JButton editImageProductButton = new JButton("Edit Image Product");
        JButton showCatalogButton = new JButton("Show Catalog");
        JButton backButton = new JButton("Back");
        addProductButton.addActionListener(e -> addProduct());
        removeProductButton.addActionListener(e -> removeProdct());
        showCatalogButton.addActionListener(e -> showCatalog());
        editTitleProductButton.addActionListener(e -> editTitleProduct());
        editPriseProductButton.addActionListener(e ->  editPriseProduct());
        editQuantityProductButton.addActionListener(e ->  editQuantityProduct());
        editImageProductButton.addActionListener(e ->  editImageProduct());
        backButton.addActionListener(e -> {
            adminFrame.dispose();
            //Catalog.saveToFile();  // Ensure products are saved before leaving
            Login();
        });

        //adminFrame.setLayout(new BorderLayout());
        adminFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
        adminFrame.add(addProductButton);
        adminFrame.add(removeProductButton);
        adminFrame.add(showCatalogButton);
        adminFrame.add(editTitleProductButton);
        adminFrame.add(editPriseProductButton);
        adminFrame.add(editQuantityProductButton);
        adminFrame.add(editImageProductButton);
        adminFrame.add(backButton);

        adminFrame.setVisible(true);
    }

    private static void showCatalog() {
        String catalogContents = catalog.showCatalog();
        JOptionPane.showMessageDialog(null, catalogContents.isEmpty() ? "No products available." : catalogContents);
    }

    public static void editImageProduct() {
        JTextField titleField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Title :"));
        myPanel.add(titleField);
        JTextField imageField = new JTextField(15);
        myPanel.add(new JLabel("New Image Addres :"));
        myPanel.add(imageField);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String imageAdress = imageField.getText();
            ImageIcon productImage = new ImageIcon(imageAdress);
            for (Product product : catalog.getProducts())
                if (product.getName().equals(title) == true) {
                    product.setImage(productImage);
                    JOptionPane.showMessageDialog(null,  title + " edit in catalog.");
                    return;
                }
            JOptionPane.showMessageDialog(null,  title + " not find in catalog.");
        }
    }

    public static void editQuantityProduct() {
        JTextField titleField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Title :"));
        myPanel.add(titleField);
        JTextField quantityField = new JTextField(15);
        myPanel.add(new JLabel("New Quantity :"));
        myPanel.add(quantityField);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            int quantityNew = Integer.parseInt(quantityField.getText());
            for (Product product : catalog.getProducts())
                if (product.getName().equals(title) == true) {
                    product.setNumber(quantityNew);
                    JOptionPane.showMessageDialog(null,  title + " edit in catalog.");
                    return;
                }
            JOptionPane.showMessageDialog(null,  title + " not find in catalog.");
        }
    }

    public static void editPriseProduct() {
        JTextField titleField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Title :"));
        myPanel.add(titleField);
        JTextField priseField = new JTextField(15);
        myPanel.add(new JLabel("New Prise :"));
        myPanel.add(priseField);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            double priseNew = Double.parseDouble(priseField.getText());
            for (Product product : catalog.getProducts())
                if (product.getName().equals(title) == true) {
                    product.setPrice(priseNew);
                    JOptionPane.showMessageDialog(null,  title + " edit in catalog.");
                    return;
                }
            JOptionPane.showMessageDialog(null,  title + " not find in catalog.");
        }
    }
    public static void editTitleProduct() {
        JTextField titleField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Old Title :"));
        myPanel.add(titleField);
        JTextField titleField2 = new JTextField(15);
        myPanel.add(new JLabel("New Title :"));
        myPanel.add(titleField2);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Edit Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String titleNew = titleField2.getText();
            for (Product product : catalog.getProducts())
                if (product.getName().equals(title) == true) {
                    product.setName(titleNew);
                    JOptionPane.showMessageDialog(null,  title + " edit in catalog.");
                    return;
                }
            JOptionPane.showMessageDialog(null,  title + " not find in catalog.");
        }
    }
    public static void removeProdct() {
        JTextField titleField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Title :"));
        myPanel.add(titleField);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Remove Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            for (Product product : catalog.getProducts())
                    if (product.getName().equals(title) == true) {
                        catalog.removeProduct(product);
                        JOptionPane.showMessageDialog(null,  title + " remove of catalog.");
                        return;
                    }
            JOptionPane.showMessageDialog(null,  title + " not find in catalog.");
        }
    }

    public static void addProduct() {
        JTextField titleField = new JTextField(15);
        JTextField priceField = new JTextField(15);
        JTextField quantityField = new JTextField(15);
        JTextField imageUrlField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Title:"));
        myPanel.add(titleField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Price:"));
        myPanel.add(priceField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Quantity:"));
        myPanel.add(quantityField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Image URL:"));
        myPanel.add(imageUrlField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String imageUrl = imageUrlField.getText();

            ImageIcon productImage = new ImageIcon(imageUrl);
            for (Product product : catalog.getProducts())
                if (product.getName().equals(title) == true){
                    JOptionPane.showMessageDialog(null,  title + " also in catalog, cant add.");
                    return;
                }
            catalog.addProduct(new Product(title, price, quantity, productImage));

            JOptionPane.showMessageDialog(null, quantity + " of " + title + " added to catalog.");
        }
    }


    private static void performLogin(JTextField usernameField, JPasswordField passwordField, JTextArea messageArea, String userType) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        AuthenticationService authService = new AuthenticationService();
        User user = authService.authenticate(username, password, userType);
        if (user != null) {
            messageArea.setText("succes");
            clearLoginFields(usernameField, passwordField);

            if (userType.equals("admin")) {
                Adminfront(user);
            }
            else {
                Productfront(user);
            }
        }
        else {
            messageArea.setText("incorrect");
        }
    }

    private static void clearLoginFields(JTextField usernameField, JPasswordField passwordField) {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    private static void Login() {
        AuthenticationService authService = new AuthenticationService();
        JFrame frame = new JFrame("Shopping Mall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridBagLayout());

        // wallpaper color
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("username");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(userLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        frame.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("password");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        frame.add(passwordField, gbc);

        JButton adminButton = new JButton("admin");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(adminButton, gbc);

        JButton customerButton = new JButton("customer");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(customerButton, gbc);

        JButton singUpButton = new JButton("Sing up");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(singUpButton, gbc);

        // bottomcollor
        adminButton.setBackground(Color.BLUE);
        adminButton.setForeground(Color.WHITE);
        customerButton.setBackground(Color.GREEN);
        customerButton.setForeground(Color.BLACK);
        singUpButton.setBackground(Color.YELLOW);
        singUpButton.setForeground(Color.BLACK);

        JTextArea messageArea = new JTextArea(2, 20);
        messageArea.setEditable(false);
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(messageArea, gbc);


        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin(usernameField, passwordField, messageArea, "admin");
            }
        });

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin(usernameField, passwordField, messageArea, "customer");
            }
        });

        singUpButton.addActionListener(e -> singUp());

        frame.setVisible(true);
    }

    private static void singUp() {
        JPanel myPanel = new JPanel();
        JTextField userNameField = new JTextField(15);
        myPanel.add(new JLabel("Username :"));
        myPanel.add(userNameField);
        JTextField passwordField = new JTextField(15);
        myPanel.add(new JLabel("Password :"));
        myPanel.add(passwordField);
        JTextField emailField = new JTextField(15);
        myPanel.add(new JLabel("Email :"));
        myPanel.add(emailField);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter informatiom of new customer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = userNameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            if (customers.containsKey(username)) {
                JOptionPane.showMessageDialog(null,  username + " sing up later.(failed)");
            }
            customers.put(username, new Customer(username, password, email));
            JOptionPane.showMessageDialog(null,  username + " sing up succesful.");
        }
    }

    private static void Productfront(Object user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        JFrame frame = new JFrame("Shopping Mall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());

        // wallpaper theme
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);


        for (Product product : catalog.getProducts()) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            productPanel.setBackground(Color.WHITE);

            ImageIcon originalIcon = product.getImage();
            Image scaledImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon productImage = new ImageIcon(scaledImage);

            JLabel titleLabel = new JLabel(product.getName());
            JLabel priceLabel = new JLabel("Price: " + product.getPrice());
            //JLabel imageLabel = new JLabel(product.getImage());
            JButton addButton = new JButton("Add to Basket");
            JButton removeButton = new JButton("Remove from Basket");

            // button color
            addButton.setBackground(Color.GREEN);
            addButton.setForeground(Color.BLACK);
            removeButton.setBackground(Color.RED);
            removeButton.setForeground(Color.WHITE);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.add(product);
                    JOptionPane.showMessageDialog(frame, product.getName() + " added to basket.");
                }
            });

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.remove(product.getName());
                    JOptionPane.showMessageDialog(frame, product.getName() + " removed from basket.");
                }
            });

            JLabel image = new JLabel(productImage);
            productPanel.add(image, BorderLayout.CENTER);
            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(titleLabel);
            textPanel.add(priceLabel);
            productPanel.add(textPanel, BorderLayout.NORTH);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            productPanel.add(buttonPanel, BorderLayout.SOUTH);
            frame.add(productPanel);
        }

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBackground(Color.YELLOW);
        viewCartButton.setForeground(Color.BLACK);

        viewCartButton.addActionListener(e -> {
            StringBuilder cartContents = new StringBuilder("Items in Cart:\n");
            for (ProductInShoppingCart p : shoppingCart.getProducts()) {
                cartContents.append(p.getProduct().getName()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, cartContents.toString());
        });

        frame.add(viewCartButton);
        frame.setVisible(true);
    }

}

