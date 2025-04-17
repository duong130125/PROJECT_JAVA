package ra.edu.business.model;

import ra.edu.utils.IApp;

import java.util.Scanner;

public class Product implements IApp {
    private int id;
    private String name;
    private String brand;
    private Double price;
    private int stock;

    public Product() {
    }

    public Product(int id, String name, String brand, Double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm: ");
        setName(scanner.nextLine());
        System.out.print("Nhập thương hiệu sản phẩm: ");
        setBrand(scanner.nextLine());
        System.out.print("Nhập giá sản phẩm: ");
        setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.print("Nhập số lượng tồn kho sản phẩm: ");
        setStock(Integer.parseInt(scanner.nextLine()));
    }

    @Override
    public String toString() {
        return "Mã sản phẩm: " + this.id + " - Tên sản phẩm: "
                + this.name + " - Thương hiệu: " + this.brand + " - Số lương tồn kho: " + this.stock;
    }
}
