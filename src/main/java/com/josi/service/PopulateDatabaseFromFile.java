package com.josi.service;

import com.josi.model.Item;
import com.josi.repository.MysqlDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PopulateDatabaseFromFile {

    static MysqlDatabase db = new MysqlDatabase();

    public static void main(String[] args) {
        PopulateDatabaseFromFile.populateItems();
    }

    public static void populateItems() {
        try {
            String file = "src/main/java/com/josi/items.txt";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // "Hamburger 450"
                String[] arr = line.split(" ");
                String itemName = arr[0];
                int itemPrice = Integer.parseInt(arr[1]);
                Item item = new Item(itemName, itemPrice);
                db.addItem(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
