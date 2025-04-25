package ra.edu.presentation;

import ra.edu.business.model.Admin;
import ra.edu.business.service.admin.AdminService;
import ra.edu.business.service.admin.AdminServiceImp;

import java.util.Scanner;

public class LoginAdminUI {
    private static final AdminService adminService = new AdminServiceImp();
    public static boolean LoginAdminMenu(Scanner scanner) {
        do {
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            Admin admin = new Admin();
            admin.inputData(scanner);
            String username = admin.getUsername();
            String password = admin.getPassword();

            boolean isLoginSuccess = adminService.loginAdmin(username, password);
            if (isLoginSuccess) {
                System.out.printf(">> Đăng nhập thành công. Vào trang quản lý với Tài khoản: %s!!!\n", username);
                return true;
            } else {
                System.out.println(">> Sai tài khoản hoặc mật khẩu. Đăng nhập lại!");
                return false;
            }
        } while (true);
    }
}
