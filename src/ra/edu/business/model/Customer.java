package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.CustomerValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Customer implements IApp {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private boolean status;

    public Customer() {
    }

    public Customer(int id, String name, String phone, String email, String address, boolean status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.name = Validator.validateString(scanner,"Nhập vào tên của khách hàng: ",1, 100);
        this.phone = inputCustomerPhone(scanner);
        this.email = inputCustomerEmail(scanner);
        this.address = Validator.validateString(scanner, "Nhập vào địa chỉ của khách hàng: ", 1, 225);
    }

    public String inputCustomerEmail(Scanner scanner) {
        String customerEmail = Validator.validateEmail(scanner, "Nhập vào email của khách hàng: ");
        return CustomerValidator.isEmailUnique(scanner, customerEmail);
    }

    public String inputCustomerPhone(Scanner scanner) {
        String customerPhone =  Validator.validatePhone(scanner, "Nhập vào số điện thoại của khách hàng: ");
        return CustomerValidator.isPhoneUnique(scanner, customerPhone);
    }

    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    // Max column widths
    private static final int WIDTH_ID = 10;
    private static final int WIDTH_NAME = 25;
    private static final int WIDTH_PHONE = 15;
    private static final int WIDTH_EMAIL = 30;
    private static final int WIDTH_ADDRESS = 30;

    public static String repeat(char ch, int length) {
        return new String(new char[length]).replace('\0', ch);
    }

    public static String getSeparatorLine() {
        return "+" + repeat('-', WIDTH_ID + 2)
                + "+" + repeat('-', WIDTH_NAME + 2)
                + "+" + repeat('-', WIDTH_PHONE + 2)
                + "+" + repeat('-', WIDTH_EMAIL + 2)
                + "+" + repeat('-', WIDTH_ADDRESS + 2) + "+";
    }

    public static String getTableHeader() {
        return getSeparatorLine() + "\n" +
                String.format("| " + YELLOW + "%-" + WIDTH_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_NAME + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_PHONE + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_EMAIL + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_ADDRESS + "s" + RESET + " |",
                        "MÃ KH", "TÊN KHÁCH HÀNG", "SỐ ĐIỆN THOẠI", "EMAIL", "ĐỊA CHỈ") + "\n" +
                getSeparatorLine();
    }

    public String formatCustomerData() {
        return String.format("| " + GREEN + "%-" + WIDTH_ID + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_NAME + "s" + RESET + " | "
                        + RED + "%-" + WIDTH_PHONE + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_EMAIL + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_ADDRESS + "s" + RESET + " |",
                id, name, phone, email, address);
    }

    @Override
    public String toString() {
        return formatCustomerData();
    }
}