package ra.edu.validate;

import ra.edu.business.model.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;

import java.util.List;
import java.util.Scanner;

public class InvoiceValidator {
    private static final CustomerService customerService = new CustomerServiceImp();

    public static int validateCustomerExists(Scanner scanner, int customerId) {
        List<Customer> listCustomer = customerService.findAll();
        do {
            boolean exists = false;
            for (Customer customer : listCustomer) {
                if (customer.getId() == customerId) {
                    exists = true;
                    System.out.println(Customer.getTableHeader());
                    System.out.println(customer);
                    System.out.println(Customer.getSeparatorLine());
                    break;
                }
            }

            if (!exists) {
                System.err.println("Mã khách hàng không tồn tại, vui lòng nhập lại");
                System.out.println("============= DANH SÁCH KHÁCH HÀNG ================");
                System.out.println(Customer.getTableHeader());
                for (Customer customer : listCustomer) {
                    System.out.println(customer);
                    System.out.println(Customer.getSeparatorLine());
                }
                System.out.print("Nhập lại mã khách hàng: ");
                customerId = Integer.parseInt(scanner.nextLine());
            } else {
                break;
            }
        } while (true);
        return customerId;
    }

    public static boolean validateCustomerStatus(int customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer != null) {
            if (!customer.isStatus()) {
                // Khách hàng đang hoạt động
                System.out.println("Khách hàng đang hoạt động, có thể tiếp tục tạo hóa đơn.");
                return true;
            } else {
                // Khách hàng đang không hoạt động
                System.err.println("Khách hàng hiện không hoạt động, vui lòng chọn khách hàng khác.");
                return false;
            }
        }
        System.err.println("Không tìm thấy khách hàng với mã " + customerId);
        return false;
    }

}
