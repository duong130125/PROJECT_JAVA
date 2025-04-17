-- Tạo cơ sở dữ liệu
CREATE DATABASE phone_shop_management;
USE phone_shop_management;

-- 1. Tạo bảng Admin (quản trị viên)
CREATE TABLE admin
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- 2. Tạo bảng Product (Sản phẩm)
CREATE TABLE product
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(100)   NOT NULL,
    brand VARCHAR(50)    NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    stock INT            NOT NULL
);

-- 3. Tạo bảng Customer (Khách hàng)
CREATE TABLE customer
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100)        NOT NULL,
    phone   VARCHAR(20)         NULL,
    email   VARCHAR(100) UNIQUE NULL,
    address VARCHAR(255)        NULL
);

-- 4. Tạo bảng Invoice (Hóa đơn)
CREATE TABLE invoice
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    customer_id  INT,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- 5. Tạo bảng Invoice_details (Chi tiết hóa đơn)
CREATE TABLE invoice_details
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    invoice_id INT,
    product_id INT,
    quantity   INT            NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoice (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);

insert into admin(username, password)
values
    ('duongdinh', '13012005');
-- Đăng nhập Admin
DELIMITER //
CREATE PROCEDURE PROC_ADMIN_LOGIN(IN user_name VARCHAR(50), IN pass VARCHAR(50))
BEGIN
    SELECT * FROM admin WHERE username = user_name AND password = pass;
END //
DELIMITER ;

-- Thêm sản phẩm
DELIMITER //
CREATE PROCEDURE PROC_INSERT_PRODUCT(IN p_name VARCHAR(100), IN p_brand VARCHAR(50), IN p_price DECIMAL(12,2), IN p_stock INT)
BEGIN
    INSERT INTO product(name, brand, price, stock) VALUES (p_name, p_brand, p_price, p_stock);
END;
//
DELIMITER ;

-- Hiển thị danh sách
DELIMITER //
CREATE PROCEDURE PROC_GET_ALL_PRODUCT()
BEGIN
    SELECT * FROM product;
END;
//
DELIMITER ;

-- Cập nhật sản phẩm
DELIMITER //
CREATE PROCEDURE PROC_UPDATE_PRODUCT(IN p_id INT, IN p_name VARCHAR(100), IN p_brand VARCHAR(50), IN p_price DECIMAL(12,2), IN p_stock INT)
BEGIN
    UPDATE product SET name = p_name, brand = p_brand, price = p_price, stock = p_stock WHERE id = p_id;
END;
//
DELIMITER ;

-- Xóa sản phẩm
DELIMITER //
CREATE PROCEDURE PROC_DELETE_PRODUCT(IN p_id INT)
BEGIN
    DELETE FROM product WHERE id = p_id;
END;
//
DELIMITER ;

