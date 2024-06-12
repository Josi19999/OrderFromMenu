package com.josi.util;

import com.josi.model.Customer;
import com.josi.model.Item;
import com.josi.model.Order;
import com.josi.model.Receipt;
import com.josi.repository.Database;

import java.util.List;
import java.util.Scanner;

public class Terminal {
    private Scanner scanner = new Scanner(System.in); // Scanner for user input
    private Database db; // Database interface for CRUD operations

    // Constructor to initialize the Terminal with a Database instance
    public Terminal(Database db) {
        this.db = db;
    }

    // Print the available options to the user
    public void printOptions() {
        System.out.println(
                """
                1: Show menu
                2: Add item to cart
                3: Show cart
                4: Remove item from cart
                5: Checkout and pay
                6: Say something nice
                0: Leave
                """
        );
    }

    // Get the user's choice
    public int pickChoice() {
        System.out.println("Please enter your choice:");
        return scanner.nextInt();
    }

    // Display the menu items retrieved from the database
    public void showMenu() {
        System.out.println("Menu:");
        List<Item> items = db.getItems();
        for (Item item : items) {
            System.out.println(item.toString());
        }
        System.out.println();
    }

    // Method to select a customer
    public Customer selectCustomer() {
        System.out.println("Enter customer ID:");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Customer customer = db.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found. Creating a new customer.");
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter address:");
            String address = scanner.nextLine();
            System.out.println("Enter initial wallet amount:");
            int walletAmount = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            customer = new Customer(customerId, firstName, lastName, address, walletAmount);
            db.addCustomer(customer);
        }
        return customer;
    }

    // Method to select an item from the menu
    public Item selectItemFromMenu() {
        System.out.println("Enter item name:");
        scanner.nextLine(); // Consume newline
        String itemName = scanner.nextLine();
        System.out.println("Searching for item: " + itemName);
        Item item = db.getItemByName(itemName);
        if (item == null) {
            System.out.println("Item not found.");
        }
        return item;
    }

    // Method to list the content of the current order
    public void listCart(Order order) {
        System.out.println("Current order:");
        for (Item item : order.getItems()) {
            System.out.println(item.toString());
        }
        System.out.println();
    }

    // Method to select an item from the cart
    public Item selectItemFromCart(Order order) {
        System.out.println("Enter item name to remove:");
        String itemName = scanner.nextLine();
        for (Item item : order.getItems()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Return null if the item is not found
    }

    // Method to print the receipt of the order
    public void printOrderReceipt(Receipt receipt) {
        System.out.println("Receipt:");
        System.out.println(receipt.toString());
    }
}
