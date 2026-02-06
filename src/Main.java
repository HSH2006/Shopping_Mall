import Shopping.Product;
import Shopping.ProductInShoppingCart;
import Shopping.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Main {
    public static void main(String[] args) {
        Productfront();
    }
    private static void Login() {
        //authService = new AuthenticationService();
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

        // bottomcollor
        adminButton.setBackground(Color.BLUE);
        adminButton.setForeground(Color.WHITE);
        customerButton.setBackground(Color.GREEN);
        customerButton.setForeground(Color.BLACK);

        JTextArea messageArea = new JTextArea(2, 20);
        messageArea.setEditable(false);
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(messageArea, gbc);

        /*adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin(usernameField, passwordField, messageArea, "admin");
            }
        });*/

        /*customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin(usernameField, passwordField, messageArea, "customer");
            }
        });*/

        frame.setVisible(true);
    }

    private static void Productfront() {
        ShoppingCart shoppingCart = new ShoppingCart();
        JFrame frame = new JFrame("Shopping Mall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());

        // wallpaper theme
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Sample products
        Product[] products = {
                new Product("01","Coffee", " ", 100,2, " ",new ImageIcon(" ")),
                new Product("02", "hot chocolate", " ", 200, 3, " ",new ImageIcon(" ")),
                new Product("03","nescafe", " ",300, 1, " ",new ImageIcon(" ")),
        };

        for (Product product : products) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BorderLayout());
            productPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel(product.getTitle());
            JLabel priceLabel = new JLabel("Price: " + product.getPrice());
            JLabel imageLabel = new JLabel(product.getImage());
            JButton addButton = new JButton("Add to Basket");
            JButton removeButton = new JButton("Remove from Basket");

            // button color
            addButton.setBackground(Color.GREEN);
            addButton.setForeground(Color.BLACK);
            removeButton.setBackground(Color.RED);
            removeButton.setForeground(Color.WHITE);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.add(product);
                    JOptionPane.showMessageDialog(frame, product.getTitle() + " added to basket.");
                }
            });

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.remove(product.getId());
                    JOptionPane.showMessageDialog(frame, product.getTitle() + " removed from basket.");
                }
            });

            productPanel.add(imageLabel, BorderLayout.CENTER);
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
                cartContents.append(p.getProduct().getTitle()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, cartContents.toString());
        });

        frame.add(viewCartButton);
        frame.setVisible(true);
    }
}




