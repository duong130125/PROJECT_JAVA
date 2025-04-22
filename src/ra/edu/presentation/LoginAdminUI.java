package ra.edu.presentation;

import ra.edu.business.service.admin.AdminService;
import ra.edu.business.service.admin.AdminServiceImp;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class LoginAdminUI {
    private static final AdminService adminService = new AdminServiceImp();
    public static boolean LoginAdminMenu(Scanner scanner) {
        do {
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            String username = Validator.validateString(scanner, "Tài khoản: ", 1, 50);

            String password = Validator.validateString(scanner, "Mật Khẩu: ", 1, 225);

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
