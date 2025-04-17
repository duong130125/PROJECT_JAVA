package ra.edu.presentation;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class ProductUI {
    private static final ProductService productService = new ProductServiceImp();

    public static void displayProductMenu(Scanner scanner) {
        do {
            System.out.println("========== QUẢN LÝ SẢN PHẨM ==========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("=====================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = Validator.validateInt(scanner);
            switch (choice) {
                case 1:
                    displayListProducts();
                    break;
                case 2:
                    createProducts(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Quay lại menu chính");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1-8!");
            }
        } while (true);
    }

    public static void displayListProducts() {
        List<Product> listProducts = productService.findAll();
        listProducts.forEach(System.out::println);
    }

    public static void createProducts(Scanner scanner) {
        System.out.println("Nhập vào số sản phẩm cần thêm mới:");
        int size = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.inputData(scanner);
            boolean result = productService.save(product);
            if (result) {
                System.out.println("Thêm mới thành công");
            } else {
                System.err.println("Có lỗi trong quá trình thêm mới");
            }
        }
    }

}
