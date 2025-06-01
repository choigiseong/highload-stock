CREATE DATABASE IF NOT EXISTS warehouse;
CREATE TABLE `product` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(256) NOT NULL,
    price BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `stock` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    stock INT NOT NULL,
    expired_at DATETIME NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE `selling_stock` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE orders (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE order_item (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(256) NOT NULL,
    product_price BIGINT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);


INSERT INTO product(name, price) VALUES ('대파', 3000), ('계란', 10000);
INSERT INTO stock (product_id, stock, expired_at)
VALUES
  (1, 100, '2025-06-30'),
  (1, 30, '2025-03-17'),
  (1, 12, '2024-09-15'),
  (2, 50, '2025-06-25'),
  (2, 50, '2025-06-08'),
  (2, 50, '2025-12-25');

INSERT INTO available_stock (product_id, stock)
VALUES
  (1, 142),
  (2, 150);