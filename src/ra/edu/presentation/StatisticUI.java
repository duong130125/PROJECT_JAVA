package ra.edu.presentation;

import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

import static ra.edu.presentation.InvoiceUI.invoiceService;

public class StatisticUI {
    public static void displayStatisticMenu(Scanner scanner) {
        do {
            System.out.println("========== THỐNG KÊ DOANH THU ==========");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo năm");
            System.out.println("4. Quay lại menu chính");
            System.out.println("========================================");
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    displayRevenue(invoiceService.getRevenueByDay(), "Ngày");
                    break;
                case 2:
                    displayRevenue(invoiceService.getRevenueByMonth(), "Tháng");
                    break;
                case 3:
                    displayRevenue(invoiceService.getRevenueByYear(), "Năm");
                    break;
                case 4:
                    System.out.println("Quay lại menu chính...");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại 1 -> 4.");
            }
        } while (true);
    }

    private static void displayRevenue(List<Object[]> list, String label) {
        System.out.printf("%-15s | %-20s\n", label, "Tổng doanh thu");
        System.out.println("-------------------------------");
        for (Object[] row : list) {
            System.out.printf("%-15s | %,20.2f\n", row[0], row[1]);
        }
    }
}
