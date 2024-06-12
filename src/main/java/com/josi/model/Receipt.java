package com.josi.model;

import java.util.List;

public class Receipt {
    private List<Item> items;
    private int total;

    public Receipt(List<Item> items, int total) {
        this.items = items;
        this.total = total;
    }

    // Getters
    public List<Item> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total: ").append(total).append(" kr\n");
        return sb.toString();
    }
}

