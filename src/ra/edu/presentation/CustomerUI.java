package ra.edu.presentation;

import ra.edu.business.model.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    public static final CustomerService customerService = new CustomerServiceImp();

    public static void displayCustomerMenu(Scanner scanner) {
        do {
            System.out.println("========== QUẢN LÝ KHÁCH HÀNG ==========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.println("=======================================");
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    showCustomerAll();
                    break;
                case 2:
                    addCustomer(scanner);
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    System.out.println("Quay lại menu chính");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1-5!");
            }
        } while (true);
    }

    public static void showCustomerAll() {
        System.out.println("========== DANH SÁCH KHÁCH HÀNG ==========");
        List<Customer> list = customerService.findAll();
        if (list.isEmpty()) {
            System.out.println("Không có khách hàng nào trong DB.");
        } else {
            list.forEach(System.out::println);
        }
    }

    public static void addCustomer(Scanner scanner) {
        int size = Validator.validateInt(scanner, "Nhập vào số khách hàng cần thêm mới: ");
        for (int i = 0; i < size; i++) {
            Customer customer = new Customer();
            customer.inputData(scanner);
            boolean result = customerService.save(customer);
            if (result) {
                System.out.println("Thêm mới khách hàng thành công");
            } else {
                System.err.println("Có lỗi trong quá trình thêm mới");
            }
        }
    }

    public static void updateCustomer(Scanner scanner) {
        int customerId = Validator.validateInt(scanner, "Nhập vào mã khách hàng cần cập nhật: ");
        Customer c = customerService.findById(customerId);

        if (c != null) {
            do {
                System.out.println("\n===== CHỌN THÔNG TIN MUỐN CẬP NHẬT =====");
                System.out.println("1. Cập nhật tên khách hàng.");
                System.out.println("2. Cập nhật email khách hàng.");
                System.out.println("3. Cập nhật Số điện thoại khách hàng.");
                System.out.println("4. Cập nhật Địa chỉ khách hàng.");
                System.out.println("5. Hoàn tất và lưu thay đổi");
                int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
                switch (choice) {
                    case 1:
                        c.setName(Validator.validateString(scanner, "Nhập vào tên mới của khách hàng: ", 1, 100));
                        break;
                    case 2:
                        c.setEmail(Validator.validateEmail(scanner, "Nhập vào email mới của khách hàng: "));
                        break;
                    case 3:
                        c.setPhone(Validator.validatePhone(scanner, "Nhập vào số điện thoại mới của khách hàng: "));
                        break;
                    case 4:
                        c.setAddress(Validator.validateString(scanner, "Nhập vào địa chỉ mới của khách hàng: ", 1, 225));
                        break;
                    case 5:
                        boolean result = customerService.update(c);
                        System.out.println(result ? "Cập nhật khách hàng thành công!" : "Cập nhật khách hàng thất bại!");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1->5!!!");
                }

            } while (true);
        } else {
            System.out.println("Không tìm thấy khách hàng với ID này.");
        }
    }

    public static void deleteCustomer(Scanner scanner) {
        int customerId = Validator.validateInt(scanner, "Nhập mã sản phẩm cần xóa: ");
        if (customerService.findById(customerId) != null) {
            Customer customer = new Customer();
            customer.setId(customerId);
            boolean result = customerService.delete(customer);
            if (result) {
                System.out.println("Xóa khách hàng thành công");
            } else {
                System.err.println("Có lỗi trong quá trình xóa.");
            }
        } else {
            System.err.println("Mã khách hàng không tồn tại");
        }
    }
}
