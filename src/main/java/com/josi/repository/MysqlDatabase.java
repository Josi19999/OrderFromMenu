package com.josi.repository;

import com.josi.model.Customer;
import com.josi.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDatabase implements Database {

    private Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String username = "root";
        String password = "Root";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItem(Item item) {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO item (name, price) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            int added = statement.executeUpdate();
            if (added > 0) {
                System.out.println("Added " + item.getName() + " to database.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try (Connection con = getConnection()) {
            String query = "SELECT * FROM item";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String itemName = rs.getString("name");
                int itemPrice = rs.getInt("price");
                Item item = new Item(itemName, itemPrice);
                items.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    @Override
    public Customer getCustomerById(int id) {
        try (Connection con = getConnection()) {
            String query = "SELECT * FROM customer WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                int walletAmount = rs.getInt("wallet_amount");
                return new Customer(id, firstName, lastName, address, walletAmount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null; // Return null if the customer is not found
    }

    @Override
    public Item getItemByName(String name) {
        System.out.println("Executing query to find item by name: " + name);
        try (Connection con = getConnection()) {
            String query = "SELECT * FROM item WHERE name = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int itemPrice = rs.getInt("price");
                System.out.println("Found item: " + name + " with price " + itemPrice);
                return new Item(name, itemPrice);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        System.out.println("Item not found: " + name);
        return null;
    }

    @Override
    public void updateCustomerWallet(int customerId, int newAmount) {
        try (Connection con = getConnection()) {
            String query = "UPDATE customer SET wallet_amount = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, newAmount);
            statement.setInt(2, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO customer (first_name, last_name, address, wallet_amount) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setInt(4, customer.getWallet().getAmount());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getInt(1)); // Set the generated ID to the customer object
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
