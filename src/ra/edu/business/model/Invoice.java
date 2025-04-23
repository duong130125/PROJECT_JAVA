package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.InvoiceValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Invoice implements IApp {
    private int id;
    private int customer_Id;
    private LocalDate created_At;
    private double total_Amount;

    public Invoice() {
    }

    public Invoice(int id, int customer_Id, LocalDate created_At, double total_Amount) {
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

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
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
        this.customer_Id = inputInvoiceId(scanner);
        this.created_At = LocalDate.now();
    }

    public Integer inputInvoiceId(Scanner scanner) {
        int invoiceId = Validator.validateInt(scanner, "Nhập vào mã khách hàng: ");
        return InvoiceValidator.validateCustomerExists(scanner, invoiceId);
    }


    @Override
    public String toString() {
        return "Mã hóa đơn: " + this.id + " - Mã khách hàng: "
                + this.customer_Id + " - Ngày tạo hóa đơn: " + this.created_At
                + " - Tổng tiền hóa đơn: " + this.total_Amount;
    }
}
