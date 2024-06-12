package com.josi.repository;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.josi.model.Item;



public class MenuFileReader  {


    public List<Item> readMenuFromFile() {
        List<Item> items = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/josi/menu.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
              //skipping the header OR empty lines
                if (line.isEmpty() || line.endsWith(":")){
                    continue;
                }

                // splits at a space first string then int in menu file
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
