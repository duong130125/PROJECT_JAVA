package ra.edu.presentation;

import ra.edu.business.model.InvoiceDetail;
import ra.edu.business.service.InvoiceDetail.InvoiceDetailService;
import ra.edu.business.service.InvoiceDetail.InvoiceDetailServiceImp;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class InvoiceDetailUI {
    private static final InvoiceDetailService invoiceDetailService = new InvoiceDetailServiceImp();
    public static void displayInvoiceDetailMenu(Scanner scanner) {
        do {
            System.out.println("===== QUẢN LÝ CHI TIẾT HÓA ĐƠN =====");
            System.out.println("1. Hiển thị danh sách chi tiết hóa đơn");
            System.out.println("2. Quay lại menu hóa đơn");
            System.out.println("====================================");
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    showInvoiceDetailsByInvoiceId(scanner);
                    break;
                case 2:
                    System.out.println("Quay lại hóa đơn....");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại 1->3!!!");
            }
        } while (true);
    }

    public static void showInvoiceDetailsByInvoiceId(Scanner  scanner) {
        int invoiceId = Validator.validateInt(scanner, "Nhập mã hóa đơn (invoice_id) muốn xem chi tiết: ");

        List<InvoiceDetail> list = invoiceDetailService.findByInvoiceId(invoiceId);
        if (list == null || list.isEmpty()) {
            System.out.println("Không tìm thấy chi tiết hóa đơn nào cho mã hóa đơn ID = " + invoiceId);
        } else {
            System.out.printf("%-5s %-10s %-10s %-10s %-10s\n", "ID", "InvoiceID", "ProductID", "Quantity", "UnitPrice");
            for (InvoiceDetail detail : list) {
                System.out.printf("%-5d %-10d %-10d %-10d %-10.2f\n",
                        detail.getId(),
                        detail.getInvoiceId(),
                        detail.getProductId(),
                        detail.getQuantity(),
                        detail.getUnitPrice());
            }
        }
    }

    public static void addInvoiceDetail(Scanner scanner, int invoiceId) {
        InvoiceDetail detail = new  InvoiceDetail();
        detail.setInvoiceId(invoiceId);
        detail.inputData(scanner);

        boolean result = invoiceDetailService.save(detail);
        if (result) {
            System.out.println("Thêm chi tiết hóa đơn thành công!");
        } else {
            System.out.println("Thêm thất bại. Vui lòng kiểm tra lại dữ liệu.");
        }
    }
}
