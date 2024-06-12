package com.josi.model;

public class Wallet {
    private int amount;

    public Wallet(int initialAmount) {
        this.amount = initialAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void subtractFromWallet(int totalCost) {
        if (amount >= totalCost) {
            amount -= totalCost;
        } else {
            throw new IllegalArgumentException("Insufficient funds in wallet.");
        }
    }
}

 /*
        order.getItems().stream(): Converts the list of items into a stream.
        mapToInt(Item::getPrice): Maps each item to its price.
        sum(): Sums up the prices.
         */

