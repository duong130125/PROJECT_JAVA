package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Admin implements IApp {
    private int id;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.username = Validator.validateString(scanner, "Tài khoản: ", 1, 50);

        this.password = Validator.validateString(scanner, "Mật Khẩu: ", 1, 225);
    }
}
