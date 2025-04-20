package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.ProductValidator;
import ra.edu.validate.Validator;

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
        this.name = inputProductName(scanner);
        this.brand = inputProductBrand(scanner);
        this.price = Validator.validateDouble(scanner, "Nhập giá của sản phẩm: ");
        this.stock = Validator.validateInt(scanner, "Nhập số lượng sản phẩm tồn kho: ");
    }

    public String inputProductName(Scanner scanner) {
        String productName = Validator.validateString(scanner, "Nhập vào tên sản phẩm: ", 1, 100);
        return ProductValidator.isProductExist(scanner, productName);
    }

    public String inputProductBrand(Scanner scanner) {
        return Validator.validateString(scanner, "Nhập thương hiệu sản phẩm: ", 1, 50);
    }

    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    // Max column widths (you can dynamically calculate this in real case)
    private static final int WIDTH_ID = 10;
    private static final int WIDTH_NAME = 25;
    private static final int WIDTH_BRAND = 20;
    private static final int WIDTH_PRICE = 18;
    private static final int WIDTH_STOCK = 10;

    private String formatProductData() {
        return String.format("| " + GREEN + "%-" + WIDTH_ID + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_NAME + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_BRAND + "s" + RESET + " | "
                        + RED + "%," + WIDTH_PRICE + ".2f" + RESET + " | "
                        + GREEN + "%" + WIDTH_STOCK + "d" + RESET + " |",
                id, name, brand, price, stock);
    }

    private static String repeat(char ch, int length) {
        return new String(new char[length]).replace('\0', ch);
    }

    private static String getSeparatorLine() {
        return "+" + repeat('-', WIDTH_ID + 2)
                + "+" + repeat('-', WIDTH_NAME + 2)
                + "+" + repeat('-', WIDTH_BRAND + 2)
                + "+" + repeat('-', WIDTH_PRICE + 2)
                + "+" + repeat('-', WIDTH_STOCK + 2) + "+";
    }

    private static String getTableHeader() {
        return getSeparatorLine() + "\n" +
                String.format("| " + YELLOW + "%-" + WIDTH_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_NAME + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_BRAND + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_PRICE + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_STOCK + "s" + RESET + " |",
                        "MÃ SP", "TÊN SẢN PHẨM", "THƯƠNG HIỆU", "GIÁ (VNĐ)", "TỒN KHO") + "\n" +
                getSeparatorLine();
    }

    private static String getTableFooter() {
        return getSeparatorLine();
    }

    @Override
    public String toString() {
        return getTableHeader() + "\n" +
                formatProductData() + "\n" +
                getTableFooter();
    }

}
