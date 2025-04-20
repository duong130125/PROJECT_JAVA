package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Customer implements IApp {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(int id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    @Override
    public void inputData(Scanner scanner) {
        this.name = Validator.validateString(scanner,"Nhập vào tên của khách hàng: ",1, 100);
        this.phone = Validator.validatePhone(scanner, "Nhập vào số điện thoại của khách hàng: ");
        this.email = Validator.validateEmail(scanner, "Nhập vào email của khách hàng: ");
        this.address = Validator.validateString(scanner, "Nhập vào địa chỉ của khách hàng: ", 1, 225);
    }

    @Override
    public String toString() {
        return "Mã khách hàng: " + this.id + " - Tên khách hàng: "
                + this.name + " - Số điện thoai: " + this.phone
                + " - Email khách hàng: " + this.email + " - Địa chỉ khách hàng: " + this.address;
    }
}
