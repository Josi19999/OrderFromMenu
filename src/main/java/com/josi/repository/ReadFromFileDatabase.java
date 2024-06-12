package com.josi.repository;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.josi.model.Item;

/*

// This file was intended for testing and debugging, but derviated from the interface
// Has no actual use case
public class ReadFromFileDatabase  {


    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/josi/items.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // "Hamburger 450"
                String[] arr = line.split(" ");
                String itemName = arr[0];
                int itemPrice = Integer.parseInt(arr[1]);
                Item item = new Item(itemName, itemPrice);
                items.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}

 */
