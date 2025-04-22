package ra.edu.validate;

import ra.edu.business.model.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;

import java.util.List;
import java.util.Scanner;

public class InvoiceValidator {
    static CustomerService customerService = new CustomerServiceImp();
    static List<Customer> listCustomer = customerService.findAll();
    public static int validateCustomerExists(Scanner scanner, int customerId) {
        do {
            boolean exists = false;
            for (Customer customer : listCustomer) {
                if (customer.getId() == customerId) {
                    exists = true;
                    System.out.println("Khách hàng: " + customer.getName());
                    break;
                }
            }

            if (!exists) {
                System.err.println("Mã khách hàng không tồn tại, vui lòng nhập lại");
                System.out.println("Danh sách khách hàng hiện có:");
                for (Customer customer : listCustomer) {
                    System.out.println("ID: " + customer.getId() + " - " + customer.getName());
                }
                customerId = scanner.nextInt();
            } else {
                break;
            }
        } while (true);
        return customerId;
    }
}
