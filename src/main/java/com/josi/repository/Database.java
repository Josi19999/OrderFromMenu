package com.josi.repository;

import com.josi.model.Customer;
import com.josi.model.Item;

import java.util.List;

public interface Database {
    void addItem(Item item);
    List<Item> getItems();
    Customer getCustomerById(int id);
    Item getItemByName(String name);
    void updateCustomerWallet(int customerId, int newAmount);  // New method to update wallet
    void addCustomer(Customer customer);  // New method to add a customer
}


//ITEM FINDITEMBYNAME W A QUERY TERMINAL IS ALSO THE PROGRAM / TERMINAL IS FOR USER
    // DATABASE HØGSTE HIERARCHY MY TERMINAL NEED THE DATABASE


    //EINMAL ALLE ITEMS THROWALL TO LIST
    //ORDER SEE CART
    //

