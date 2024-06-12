package com.josi.repository;

import com.josi.model.Item;
import java.util.ArrayList;
import java.util.List;
/*
public class MockDatabase implements Database {
    private final List<Item> items = new ArrayList<>(){{
        Item burger = new Item("Hamburger", 42);
        Item sushi = new Item("Sushi", 37);
        Item milkshake = new Item("Milkshake", 22);
        add(burger);
        add(sushi);
        add(milkshake);
    }};

    public MockDatabase() {

    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public List<Item> getItems() {
        // Dette er hardkoding, med en mysql database ville man hentet denne
        // infoen fra mysql
        /*Item burger = new Item("Hamburger", 42);
        Item sushi = new Item("Sushi", 37);
        Item milkshake = new Item("Milkshake", 22);
        return List.of(burger, sushi, milkshake);


        return items;
    }

}
            */
