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
        this.quantity = Validator.validateInt(scanner, "Nhập số lượng sản phẩm muốn thêm: ");
    }

    public Integer inputInvDtProduceId(Scanner scanner) {
        int produceId = Validator.validateInt(scanner, "Nhập vào mã sản phẩm muốn thêm: ");
        return InvoiceDtValidator.validateProductExists(scanner, produceId);
    }
}
