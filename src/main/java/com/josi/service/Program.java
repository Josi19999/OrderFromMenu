package com.josi.service;

import com.josi.model.Customer;
import com.josi.model.Item;
import com.josi.model.Order;
import com.josi.model.Receipt;
import com.josi.repository.Database;
import com.josi.util.Terminal;

public class Program {

    private Database database;  // Database interface
    private Order order;  // Initialize the order
    private final Terminal terminal;

    // Constructor to initialize the Program with a Database instance
    public Program(Database database) {
        this.database = database;
        this.terminal = new Terminal(database); // Initialize terminal with the database
        this.order = new Order();  // Initialize the order
    }

    // Start the main program loop
    public void start() {
        boolean running = true;
        Customer customer = terminal.selectCustomer(); // Select customer once at the beginning

        while (running) {
            terminal.printOptions(); // Print available options
            int choice = terminal.pickChoice(); // Get user's choice

            // Handle user's choice
            switch (choice) {
                case 1 -> terminal.showMenu(); // Show the menu
                case 2 -> {
                    Item item = terminal.selectItemFromMenu(); // Select item from menu
                    if (item != null) {
                        order.addItem(item); // Add item to order
                        System.out.println("Added " + item.getName() + " to the cart.");
                    } else {
                        System.out.println("Item not found.");
                    }
                }
                case 3 -> terminal.listCart(order); // Show cart
                case 4 -> {
                    Item item = terminal.selectItemFromCart(order); // Select item from cart
                    if (item != null) {
                        order.removeItem(item); // Remove item from order
                        System.out.println("Removed " + item.getName() + " from the cart.");
                    } else {
                        System.out.println("Item not found in cart.");
                    }
                }
                case 5 -> {
                    try {
                        int totalCost = order.getItems().stream().mapToInt(Item::getPrice).sum();
                        customer.getWallet().subtractFromWallet(totalCost); // Subtract total cost from wallet
                        database.updateCustomerWallet(customer.getId(), customer.getWallet().getAmount()); // Update wallet in database
                        Receipt receipt = new Receipt(order.getItems(), totalCost);
                        terminal.printOrderReceipt(receipt); // Print receipt
                        order.clear(); // Clear current order
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> System.out.println("You're doing great! Keep it up!"); // Say something nice
                case 0 -> {
                    running = false; // Exit the program
                    System.out.println("Good bye. Thanks for shopping with us!");
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
