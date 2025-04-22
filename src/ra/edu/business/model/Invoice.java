package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Invoice implements IApp {
    private int id;
    private String customer_Id;
    private LocalDate created_At;
    private double total_Amount;

    public Invoice() {
    }

    public Invoice(int id, String customer_Id, LocalDate created_At, double total_Amount) {
        this.id = id;
        this.customer_Id = customer_Id;
        this.created_At = created_At;
        this.total_Amount = total_Amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public LocalDate getCreated_At() {
        return created_At;
    }

    public void setCreated_At(LocalDate created_At) {
        this.created_At = created_At;
    }

    public double getTotal_Amount() {
        return total_Amount;
    }

    public void setTotal_Amount(double total_Amount) {
        this.total_Amount = total_Amount;
    }

    @Override

    public void inputData(Scanner scanner) {
        this.customer_Id = Validator.validateString(scanner, "Nhập vào mã khách hàng: ", 1, 100);
        this.created_At = Validator.validateDate(scanner, "Nhập ngày/tháng/năm của hóa đơn: ");
        this.total_Amount = Validator.validateDouble(scanner, "Nhập vào tổng tiền của hóa đơn: ");
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Khách hàng: %s | Ngày: %s | Tổng tiền: %.2f",
                id, customer_Id, created_At, total_Amount);
    }
}
