package ra.edu.validate;

import ra.edu.business.model.Customer;

import java.util.List;
import java.util.Scanner;

import static ra.edu.presentation.CustomerUI.customerService;


public class CustomerValidator {
    static List<Customer> listCustomer = customerService.findAll();
    public static String isEmailUnique(Scanner scanner, String value) {
        do {
            boolean isDuplicate = false;
            for (Customer customer : listCustomer) {
                if (customer.getName().equals(value)) {
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
}
