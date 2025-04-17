package ra.edu.presentation;

import ra.edu.business.service.admin.AdminService;
import ra.edu.business.service.admin.AdminServiceImp;

import java.util.Scanner;

public class LoginAdminUI {
    private static final AdminService adminService = new AdminServiceImp();
    public static boolean LoginAdminMenu(Scanner scanner) {
        do {
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            System.out.print("Tài khoản: ");
            String username = scanner.nextLine();

            System.out.print("Mật khẩu : ");
            String password = scanner.nextLine();

            boolean isLoginSuccess = adminService.loginAdmin(username, password);

            if (isLoginSuccess) {
                System.out.println(">> Đăng nhập thành công. Vào trang quản lý!");
                return true;
            } else {
                System.out.println(">> Sai tài khoản hoặc mật khẩu. Đăng nhập lại!");
                return false;
            }
        } while (true);
    }
}
