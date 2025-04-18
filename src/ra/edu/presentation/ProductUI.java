package ra.edu.presentation;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class ProductUI {
    public static final ProductService productService = new ProductServiceImp();

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
            int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    displayListProducts();
                    break;
                case 2:
                    createProducts(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchByBrand(scanner);
                    break;
                case 6:
                    searchByPriceRange(scanner);
                    break;
                case 7:
                    searchByStock(scanner);
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
                System.out.println("Thêm mới sản phẩm thành công");
            } else {
                System.err.println("Có lỗi trong quá trình thêm mới");
            }
        }
    }

    public static void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật:");
        int productId = Integer.parseInt(scanner.nextLine());
        if (productService.findById(productId) != null) {
            Product product = new Product();
            product.setId(productId);
            product.inputData(scanner);
            boolean result = productService.update(product);
            if (result) {
                System.out.println("Cập nhật thành công sản phẩm");
            } else {
                System.err.println("Có lỗi trong quá trình cập nhật");
            }
        } else {
            System.err.println("Không tồn tại mã sản phẩm");
        }
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập mã sinh viên cần xóa:");
        int productId = Integer.parseInt(scanner.nextLine());
        if (productService.findById(productId) != null) {
            Product product = new Product();
            product.setId(productId);
            boolean result = productService.delete(product);
            if (result) {
                System.out.println("Xóa sản phẩm thành công");
            } else {
                System.err.println("Có lỗi trong quá trình xóa.");
            }
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void searchByBrand(Scanner scanner) {
        System.out.println("Nhập nhãn hàng cần tìm:");
        String brand = scanner.nextLine();
        List<Product> products = productService.findByBrand(brand);

        if (!products.isEmpty()) {
            System.out.println("Danh sách sản phẩm thuộc nhãn hàng " + brand + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm nào thuộc nhãn hàng " + brand);
        }
    }

    public static void searchByPriceRange(Scanner scanner) {
        System.out.println("Nhập giá tối thiểu:");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập giá tối đa:");
        double maxPrice = Double.parseDouble(scanner.nextLine());

        List<Product> products = productService.findByPriceRange(minPrice, maxPrice);

        if (!products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trong khoảng giá từ " + minPrice + " đến " + maxPrice + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá này");
        }
    }

    public static void searchByStock(Scanner scanner) {
        System.out.println("Nhập số lượng tồn kho tối thiểu:");
        int minStock = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập số lượng tồn kho tối đa:");
        int maxStock = Integer.parseInt(scanner.nextLine());

        List<Product> products = productService.findByStock(minStock, maxStock);

        if (!products.isEmpty()) {
            System.out.println("Danh sách sản phẩm có tồn kho từ " + minStock + " đến " + maxStock + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm nào có tồn kho trong khoảng này");
        }
    }

}
