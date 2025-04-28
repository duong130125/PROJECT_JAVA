package ra.edu.business.model;

import ra.edu.utils.IApp;
import ra.edu.validate.InvoiceDtValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class InvoiceDetail implements IApp {
    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int id, int invoiceId, int productId, int quantity, double unitPrice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.productId = inputInvDtProduceId(scanner);
        this.quantity = inputValidQuantity(scanner, productId);
    }

    public Integer inputInvDtProduceId(Scanner scanner) {
        while (true) {
            int produceId = Validator.validateInt(scanner, "Nhập vào mã sản phẩm muốn thêm: ");
            int validId = InvoiceDtValidator.validateProductExists(scanner, produceId);

            if (InvoiceDtValidator.validateProductStatus(validId)) {
                return validId;
            } else {
                System.err.println("Sản phẩm này đang ngừng kinh doanh, vui lòng chọn sản phẩm khác!");
            }
        }
    }

    public Integer inputValidQuantity(Scanner scanner, int productId) {
        while (true) {
            int quantity = Validator.validateInt(scanner, "Nhập số lượng sản phẩm muốn thêm: ");
            if (InvoiceDtValidator.validateProductStock(productId, quantity)) {
                return quantity;
            } else {
                System.err.println("Vui lòng nhập lại số lượng phù hợp.");
            }
        }
    }


    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

    // Max column widths
    private static final int WIDTH_ID = 10;
    private static final int WIDTH_INVOICE_ID = 12;
    private static final int WIDTH_PRODUCT_ID = 12;
    private static final int WIDTH_QUANTITY = 12;
    private static final int WIDTH_UNIT_PRICE = 18;
    private static final int WIDTH_TOTAL = 18;

    public static String repeat(char ch, int length) {
        return new String(new char[length]).replace('\0', ch);
    }

    public static String getSeparatorLine() {
        return "+" + repeat('-', WIDTH_ID + 2)
                + "+" + repeat('-', WIDTH_INVOICE_ID + 2)
                + "+" + repeat('-', WIDTH_PRODUCT_ID + 2)
                + "+" + repeat('-', WIDTH_QUANTITY + 2)
                + "+" + repeat('-', WIDTH_UNIT_PRICE + 2)
                + "+" + repeat('-', WIDTH_TOTAL + 2) + "+";
    }

    public static String getTableHeader() {
        return getSeparatorLine() + "\n" +
                String.format("| " + YELLOW + "%-" + WIDTH_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_INVOICE_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_PRODUCT_ID + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_QUANTITY + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_UNIT_PRICE + "s" + RESET + " | "
                                + YELLOW + "%-" + WIDTH_TOTAL + "s" + RESET + " |",
                        "MÃ CTĐƠN", "MÃ HÓA ĐƠN", "MÃ SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ (VNĐ)", "THÀNH TIỀN (VNĐ)") + "\n" +
                getSeparatorLine();
    }

    public String formatInvoiceDetailData() {
        double totalPrice = quantity * unitPrice;

        return String.format("| " + GREEN + "%-" + WIDTH_ID + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_INVOICE_ID + "s" + RESET + " | "
                        + GREEN + "%-" + WIDTH_PRODUCT_ID + "s" + RESET + " | "
                        + GREEN + "%" + WIDTH_QUANTITY + "d" + RESET + " | "
                        + RED + "%," + WIDTH_UNIT_PRICE + ".2f" + RESET + " | "
                        + RED + "%," + WIDTH_TOTAL + ".2f" + RESET + " |",
                id, invoiceId, productId, quantity, unitPrice, totalPrice);
    }

    @Override
    public String toString() {
        return formatInvoiceDetailData();
    }
}