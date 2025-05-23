package ra.edu;

import ra.edu.presentation.*;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("========== HỆ THỐNG QUẢN LÝ CỬA HÀNG ==========");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("==================================================");

            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    boolean loginSuccess = LoginAdminUI.LoginAdminMenu(scanner);
                    if (loginSuccess) {
                        showMainMenu(scanner);
                    }
                    break;
                case 2:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ. Vui lòng thử lại.\u001B[0m");
            }
        } while (true);
    }

    private static void showMainMenu(Scanner scanner) {
        do {
            System.out.println("========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("================================");
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    System.out.println("Bắt đầu vào trang quản lý sản phẩm!!!");
                    ProductUI.displayProductMenu(scanner);
                    break;
                case 2:
                    System.out.println("Bắt đầu vào trang quản lý khách hàng!!!");
                    CustomerUI.displayCustomerMenu(scanner);
                    break;
                case 3:
                    System.out.println("Bắt đầu vào trang quản lý hóa đơn!!!");
                    InvoiceUI.displayInvoiceMenu(scanner);
                    break;
                case 4:
                    System.out.println("Bắt đầu vào trang quản lý thống kê!!!");
                    StatisticUI.displayStatisticMenu(scanner);
                    break;
                case 5:
                    System.out.println(">> Đã đăng xuất. Quay lại trang đăng nhập!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (true);
    }
}