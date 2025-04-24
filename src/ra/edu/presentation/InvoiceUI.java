package ra.edu.presentation;

import ra.edu.business.model.Invoice;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    public static final InvoiceService invoiceService = new InvoiceServiceImp();

    public static void displayInvoiceMenu(Scanner scanner) {
        do {
            System.out.println("========== QUẢN LÝ HÓA ĐƠN ==========");
            System.out.println("1. Hiển thị danh sách hóa đơn");
            System.out.println("2. Thêm mới hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Xem hóa đơn chi tiết");
            System.out.println("5. Quay lại menu chính");
            System.out.println("====================================");
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    displayAllInvoices();
                    break;
                case 2:
                    addNewInvoice(scanner);
                    break;
                case 3:
                    searchInvoiceMenu(scanner);
                    break;
                case 4:
                    InvoiceDetailUI.displayInvoiceDetailMenu(scanner);
                    break;
                case 5:
                    System.out.println("Quay lại menu chính...");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 4.");
            }
        } while (true);
    }

    private static void displayAllInvoices() {
        List<Invoice> invoices = invoiceService.findAll();
        if (invoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào trong hệ thống.");
        } else {
            System.out.println("====== DANH SÁCH HÓA ĐƠN ======");
            invoices.forEach(System.out::println);
        }
    }

    public static void addNewInvoice(Scanner scanner) {
        System.out.println("=== Nhập thông tin hóa đơn mới ===");

        Invoice invoice = new Invoice();
        invoice.inputData(scanner);

        boolean result = invoiceService.save(invoice);

        if (result) {
            System.out.println("Thêm mới hóa đơn thành công. Mã hóa đơn: " + invoice.getId());
            System.out.println("==== Nhập chi tiết hóa đơn ====");
            InvoiceDetailUI.addInvoiceDetail(scanner, invoice.getId());
        } else {
            System.err.println("Có lỗi trong quá trình thêm hóa đơn.");
        }
    }

    private static void searchInvoiceMenu(Scanner scanner) {
        do {
            System.out.println("\n----- TÌM KIẾM HÓA ĐƠN -----");
            System.out.println("1. Theo tên khách hàng");
            System.out.println("2. Theo ngày xuất hóa đơn (YYYY-MM-DD)");
            System.out.println("3. Quay lại menu quản lý hóa đơn");
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    searchInvoiceByCustomerName(scanner);
                    break;
                case 2:
                    searchInvoiceByDate(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1 đến 3.");
            }

        } while (true);
    }

    private static void searchInvoiceByCustomerName(Scanner scanner) {
        String cusName = Validator.validateString(scanner, "Nhập tên khách hàng cần tìm: ", 1, 100);
        List<Invoice> result = invoiceService.searchByCustomerName(cusName);
        if (result.isEmpty()) {
            System.out.printf("Không tìm thấy hóa đơn nào với khách tên là:  %s\n.", cusName);
        } else {
            System.out.println("====== KẾT QUẢ TÌM KIẾM ======");
            for (Invoice i : result) {
                System.out.printf("ID: %d | Mã KH: %s | Ngày tạo: %s | Tổng tiền: %.2f%n",
                        i.getId(), i.getCustomer_Id(), i.getCreated_At(), i.getTotal_Amount());
            }
        }
    }

    private static void searchInvoiceByDate(Scanner scanner) {
        LocalDate date = Validator.validateDate(scanner, "Nhập ngày cần tìm: ");
        List<Invoice> result = invoiceService.searchByDate(date);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy hóa đơn nào trong ngày " + date);
        } else {
            System.out.println("====== KẾT QUẢ TÌM KIẾM ======");
            for (Invoice i : result) {
                System.out.printf("ID: %d | Mã KH: %s | Ngày tạo: %s | Tổng tiền: %.2f%n",
                        i.getId(), i.getCustomer_Id(), i.getCreated_At(), i.getTotal_Amount());
            }
        }
    }
}

