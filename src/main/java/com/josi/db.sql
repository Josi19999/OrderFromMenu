CREATE DATABASE IF NOT EXISTS restaurant;

USE restaurant;
START TRANSACTION;

-- Table for items
CREATE TABLE IF NOT EXISTS item (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(62) UNIQUE,
    price INT,
    PRIMARY KEY (id)
);

-- Table for categories
CREATE TABLE IF NOT EXISTS category (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(62) UNIQUE,
    PRIMARY KEY (id)
);

-- Table for item/category relationship
CREATE TABLE IF NOT EXISTS item_category (
    item_id INT,
    category_id INT,
    PRIMARY KEY (item_id, category_id),
    FOREIGN KEY (item_id) REFERENCES item(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Table for menus
CREATE TABLE IF NOT EXISTS menu (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(62) UNIQUE,
    PRIMARY KEY (id)
);

-- Junction menu/item relationship
CREATE TABLE IF NOT EXISTS menu_item (
    menu_id INT,
    item_id INT,
    PRIMARY KEY (menu_id, item_id),
    FOREIGN KEY (menu_id) REFERENCES menu(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

-- Table for orders
CREATE TABLE IF NOT EXISTS `order` (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Table for order/item relationship
CREATE TABLE IF NOT EXISTS order_item (
    order_id INT,
    item_id INT,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES `order`(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

-- Table for customers
CREATE TABLE IF NOT EXISTS customer (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    first_name VARCHAR(62),
    last_name VARCHAR(62),
    address VARCHAR(128),
    wallet_amount INT,
    PRIMARY KEY (id)
);

COMMIT;