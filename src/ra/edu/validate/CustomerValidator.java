package ra.edu.validate;

import ra.edu.business.model.Customer;

import java.util.List;
import java.util.Scanner;

import static ra.edu.presentation.CustomerUI.customerService;

public class CustomerValidator {

    public static String isEmailUnique(Scanner scanner, String value) {
        List<Customer> listCustomer = customerService.findAll();

        do {
            boolean isDuplicate = false;
            for (Customer customer : listCustomer) {
                if (customer.getEmail().equals(value)) {
                    isDuplicate = true;
                    break;
                }
            }

            // Nếu trùng lặp, yêu cầu nhập lại
            if (isDuplicate) {
                System.err.println("Email đã tồn tại, vui lòng nhập lại");
                value = scanner.nextLine();
            } else {
                // Không trùng lặp, thoát vòng lặp
                break;
            }
        } while (true);

        return value;
    }

    public static String isPhoneUnique(Scanner scanner, String value) {
        List<Customer> listCustomer = customerService.findAll(); // Lấy danh sách mới nhất

        do {
            boolean isDuplicate = false;
            for (Customer customer : listCustomer) {
                if (customer.getPhone().equals(value)) {
                    isDuplicate = true;
                    break;
                }
            }

            // Nếu trùng lặp, yêu cầu nhập lại
            if (isDuplicate) {
                System.err.println("Số điện thoại đã tồn tại, vui lòng nhập lại");
                value = scanner.nextLine();
            } else {
                // Không trùng lặp, thoát vòng lặp
                break;
            }
        } while (true);

        return value;
    }
}