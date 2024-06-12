package com.josi.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items = new ArrayList<>(); // List of items in the order

    // Add an item to the order
    public void addItem(Item item) {
        items.add(item);
    }

    // Remove an item from the order
    public void removeItem(Item item) {
        items.remove(item);
    }

    // Clear the order
    public void clear() {
        items.clear();
    }

    // Getter for items in the order
    public List<Item> getItems() {
        return items;
    }
}

