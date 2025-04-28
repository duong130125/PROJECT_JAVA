package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.InvoiceValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.customer_Id = inputInvoiceCustomerId(scanner);
        this.created_At = LocalDate.now();
    }

    public Integer inputInvoiceCustomerId(Scanner scanner) {
        int customerId;

        do {

            customerId = Validator.validateInt(scanner, "Nhập vào mã khách hàng: ");

            customerId = InvoiceValidator.validateCustomerExists(scanner, customerId);

            boolean isActive = InvoiceValidator.validateCustomerStatus(customerId);
            if (!isActive) {
                continue; // Không active thì nhập lại
            }

            break; // Nếu active thì thoát vòng lặp
        } while (true);

        return customerId;
    }

    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    // Max column widths
    private static final int WIDTH_ID = 12;
    private static final int WIDTH_CUSTOMER_ID = 15;
    private static final int WIDTH_DATE = 20;
    private static final int WIDTH_AMOUNT = 20;

    // Date formatter for display
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String repeat(char ch, int length) {
        return new String(new char[length]).replace('\0', ch);
    }

    public static String getSeparatorLine() {
        return "+" + repeat('-', WIDTH_ID + 2)
                + "+" + repeat('-', WIDTH_CUSTOMER_ID + 2)
                + "+" + repeat('-', WIDTH_DATE + 2)
                + "+" + repeat('-', WIDTH_AMOUNT + 2) + "+";
    }

    public static String getTableHeader() {
        return getSeparatorLine() + "\n" +
                String.format("| " + YELLOW + "%-" + WIDTH_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_CUSTOMER_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_DATE + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_AMOUNT + "s" + RESET + " |",
                        "MÃ HÓA ĐƠN", "MÃ KHÁCH HÀNG", "NGÀY TẠO", "TỔNG TIỀN (VNĐ)") + "\n" +
                getSeparatorLine();
    }

    public String formatInvoiceData() {
        String formattedDate = created_At != null ? created_At.format(dateFormatter) : "";

        return String.format("| " + GREEN + "%-" + WIDTH_ID + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_CUSTOMER_ID + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_DATE + "s" + RESET + " | "
                        + RED + "%," + WIDTH_AMOUNT + ".2f" + RESET + " |",
                id, customer_Id, formattedDate, total_Amount);
    }

    @Override
    public String toString() {
        return formatInvoiceData();
    }
}