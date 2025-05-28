CREATE DATABASE IF NOT EXISTS warehouse;
CREATE TABLE `product` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(256) NOT NULL,
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

INSERT INTO product(name) VALUES ('대파'), ('계란');
INSERT INTO stock (product_id, stock, expired_at)
VALUES
  (1, 100, '2025-06-30'),
  (1, 30, '2025-03-17'),
  (1, 12, '2024-09-15'),
  (2, 50, '2025-06-25'),
  (2, 50, '2025-06-08'),
  (2, 50, '2025-12-25');

