package com.josi.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.josi.model.Item;

public class WriteToFileForDatabase {
    public List<Item> setItems() {
        List<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
            // for the user input
            System.out.println("Name of food (or type 'exit' to finish):");
            String itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter food price:");
            int itemPrice = scanner.nextInt();
            scanner.nextLine(); // for txt file


            Item item = new Item(itemName, itemPrice);
            item.setName(itemName);
            item.setPrice(itemPrice);

            // Adding order items to list
            items.add(item);
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid price.");
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }

        }

        // writings items to items.txt which is the ordering file will go into a sql orders table
        String filePath = "src/main/java/org/example/items.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            boolean firstItem = true;
            for (Item item : items) {
                if (firstItem) {
                    writer.write(item.getName() + " " + item.getPrice());
                    firstItem = false;
                } else {
                    writer.write("\n" + item.getName() + " " + item.getPrice());
                }
            System.out.println("Items have been written to the file.");
        }} catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        return items;
    }
}
