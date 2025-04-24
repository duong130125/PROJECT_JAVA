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
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(100)                      NOT NULL,
    brand  VARCHAR(50)                       NOT NULL,
    price  DECIMAL(12, 2) check ( 0 < price) NOT NULL,
    stock  INT check ( 0 <= stock)           NOT NULL,
    status boolean default (true)
);

-- 3. Tạo bảng Customer (Khách hàng)
CREATE TABLE customer
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100)        NOT NULL,
    phone   VARCHAR(20)         NULL,
    email   VARCHAR(100) UNIQUE NULL,
    address VARCHAR(255)        NULL,
    status  boolean default (true)
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

-- Tài khoản đăng nhập
insert into admin(username, password)
values ('duongdinh', '13012005');

-- Đăng nhập Admin
DELIMITER //
CREATE PROCEDURE PROC_ADMIN_LOGIN(IN user_name VARCHAR(50), IN pass VARCHAR(225))
BEGIN
    SELECT * FROM admin WHERE username = user_name AND password = pass;
END //
DELIMITER ;

-- ****Phần sản phẩm****
DELIMITER //
-- Thêm sản phẩm
CREATE PROCEDURE PROC_INSERT_PRODUCT(IN p_name VARCHAR(100), IN p_brand VARCHAR(50), IN p_price DECIMAL(12, 2),
                                     IN p_stock INT)
BEGIN
    INSERT INTO product(name, brand, price, stock) VALUES (p_name, p_brand, p_price, p_stock);
END;

-- Hiển thị danh sách
CREATE PROCEDURE PROC_GET_ALL_PRODUCT()
BEGIN
    SELECT *
    FROM product
    where status = true
      and stock > 0;
END;

-- Cập nhật sản phẩm
CREATE PROCEDURE PROC_UPDATE_PRODUCT(IN p_id INT, IN p_name VARCHAR(100), IN p_brand VARCHAR(50),
                                     IN p_price DECIMAL(12, 2), IN p_stock INT)
BEGIN
    UPDATE product SET name = p_name, brand = p_brand, price = p_price, stock = p_stock WHERE id = p_id;
END;

-- Xóa sản phẩm
CREATE PROCEDURE PROC_DELETE_PRODUCT(IN p_id INT)
BEGIN
    update product
    set status = false
    where id = p_id;
END;

-- Lấy id sản phẩm
create procedure get_product_by_id(id_in int)
begin
    select * from product where id = id_in;
end;

-- Tìm theo nhãn hàng
CREATE PROCEDURE PROC_FIND_BY_BRAND(IN p_brand VARCHAR(50))
BEGIN
    SELECT * FROM product WHERE brand = p_brand;
END;

-- Tìm theo khoảng giá
CREATE PROCEDURE PROC_FIND_BY_PRICE_RANGE(IN min_price DECIMAL(12, 2), IN max_price DECIMAL(12, 2))
BEGIN
    SELECT * FROM product WHERE price BETWEEN min_price AND max_price;
END;

-- Tìm theo tồn kho
CREATE PROCEDURE PROC_FIND_PRODUCT_BY_STOCK(IN min_stock INT, IN max_stock INT)
BEGIN
    SELECT * FROM product WHERE stock BETWEEN min_stock AND max_stock;
END;
//
DELIMITER ;

-- ****Phần khách hàng****
DELIMITER //
-- Lấy tất cả khách hàng
CREATE PROCEDURE PROC_GET_ALL_CUSTOMERS()
BEGIN
    SELECT *
    FROM customer
    where status = true;
END;

-- Thêm mới khách hàng
CREATE PROCEDURE PROC_INSERT_CUSTOMER(IN cname VARCHAR(100), IN cemail VARCHAR(100), IN cphone VARCHAR(20),
                                      IN caddress VARCHAR(255))
BEGIN
    INSERT INTO customer(name, email, phone, address) VALUES (cname, cemail, cphone, caddress);
END;

-- Cập nhật khách hàng
CREATE PROCEDURE PROC_UPDATE_CUSTOMER(IN cid INT, IN cname VARCHAR(100), IN cemail VARCHAR(100), IN cphone VARCHAR(20),
                                      IN caddress VARCHAR(255))
BEGIN
    UPDATE customer SET name = cname, email = cemail, phone = cphone, address = caddress WHERE id = cid;
END;

-- Xoá khách hàng
CREATE PROCEDURE PROC_DELETE_CUSTOMER(IN cid INT)
BEGIN
    update customer
    set status = false
    WHERE id = cid;
END;

-- Tìm theo ID khách hàng
CREATE PROCEDURE PROC_FIND_CUSTOMER_BY_ID(IN cid INT)
BEGIN
    SELECT * FROM customer WHERE id = cid;
END;
//
DELIMITER ;

-- ****Phần Hóa Đơn****
DELIMITER //
-- Hiện thị thông tin hóa đơn
CREATE PROCEDURE PROC_FIND_ALL_INVOICES()
BEGIN
    SELECT * FROM invoice;
END;

-- Thêm mới hóa đơn
CREATE PROCEDURE PROC_INSERT_INVOICE(
    IN p_customer_id INT,
    IN p_created_at DATE,
    IN p_total_amount DOUBLE,
    OUT p_invoice_id INT
)
BEGIN
    INSERT INTO invoice(customer_id, created_at, total_amount)
    VALUES (p_customer_id, p_created_at, p_total_amount);

    SET p_invoice_id = LAST_INSERT_ID(); -- Lấy id vừa thêm
END;

-- Tìm kiếm hóa đơn theo Tên khách hàng
CREATE PROCEDURE PROC_SEARCH_INVOICE_BY_NAME(
    IN p_keyword VARCHAR(100)
)
BEGIN
    SELECT *
    FROM invoice i
             JOIN customer c ON i.customer_id = c.id
    WHERE c.name LIKE p_keyword;
END;

-- Tìm kiếm hóa đơn theo DD/MM/YYYY
CREATE PROCEDURE PROC_SEARCH_INVOICE_BY_DATE(
    IN p_date DATE
)
BEGIN
    SELECT *
    FROM invoice
    WHERE DATE(created_at) = p_date;
END;
//
DELIMITER ;

-- *****Phần chi tiết hóa đơn và Thống kê*****
DELIMITER //
-- Hiện thị chi tiết hóa đơn
CREATE PROCEDURE PROC_GET_ALL_INVOICE_DETAIL()
BEGIN
    SELECT id, invoice_id, product_id, quantity, unit_price
    FROM invoice_details;
END;

-- Thêm mới chi tiết hóa đơn
CREATE PROCEDURE PROC_INSERT_INVOICE_DETAIL(
    IN p_invoice_id INT,
    IN p_product_id INT,
    IN p_quantity INT
)
BEGIN
    DECLARE v_unit_price DECIMAL(12,2);
    DECLARE v_stock INT;

    -- Lấy giá và tồn kho của sản phẩm
    SELECT price, stock INTO v_unit_price, v_stock
    FROM product
    WHERE id = p_product_id;

    -- Kiểm tra tồn kho
    IF v_stock >= p_quantity THEN
        -- Trừ số lượng khỏi kho
        UPDATE product
        SET stock = stock - p_quantity
        WHERE id = p_product_id;

        -- Thêm chi tiết hóa đơn
        INSERT INTO invoice_details(invoice_id, product_id, quantity, unit_price)
        VALUES (p_invoice_id, p_product_id, p_quantity, v_unit_price);

        -- Cập nhật tổng tiền hóa đơn
        UPDATE invoice
        SET total_amount = total_amount + (v_unit_price * p_quantity)
        WHERE id = p_invoice_id;

    ELSE
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Số lượng trong kho không đủ, vui lòng đợi trong giây lát!';
    END IF;
END;

-- Lấy chi tiết hóa đơn theo invoice_id
CREATE PROCEDURE get_invoice_detail_by_invoice_id(IN p_invoice_id INT)
BEGIN
    SELECT *
    FROM invoice_details
    WHERE invoice_id = p_invoice_id;
END;

-- Tổng doanh thu theo ngày
CREATE PROCEDURE PROC_REVENUE_BY_DAY()
BEGIN
    SELECT DATE(created_at) AS day, SUM(total_amount) AS total_revenue
    FROM invoice
    GROUP BY DATE(created_at)
    ORDER BY DATE(created_at);
END;

-- Tổng doanh thu theo tháng
CREATE PROCEDURE PROC_REVENUE_BY_MONTH()
BEGIN
    SELECT DATE_FORMAT(created_at, '%Y-%m') AS month, SUM(total_amount) AS total_revenue
    FROM invoice
    GROUP BY DATE_FORMAT(created_at, '%Y-%m')
    ORDER BY DATE_FORMAT(created_at, '%Y-%m');
END;

-- Tổng doanh thu theo năm
CREATE PROCEDURE PROC_REVENUE_BY_YEAR()
BEGIN
    SELECT YEAR(created_at) AS year, SUM(total_amount) AS total_revenue
    FROM invoice
    GROUP BY YEAR(created_at)
    ORDER BY YEAR(created_at);
END;
//
DELIMITER ;