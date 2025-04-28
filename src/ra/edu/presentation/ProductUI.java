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
            System.out.println("5. Tìm kiếm theo tên sản phẩm");
            System.out.println("6. Tìm kiếm theo Brand");
            System.out.println("7. Tìm kiếm theo khoảng giá");
            System.out.println("8. Tìm kiếm theo tồn kho");
            System.out.println("9. Quay lại menu chính");
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
                    searchByProName(scanner);
                    break;
                case 6:
                    searchByBrand(scanner);
                    break;
                case 7:
                    searchByPriceRange(scanner);
                    break;
                case 8:
                    searchByStock(scanner);
                    break;
                case 9:
                    System.out.println("Quay lại menu chính...");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1-8!");
            }
        } while (true);
    }

    public static void displayListProducts() {
        System.out.println("========== DANH SÁCH SẢN PHẨM ==========");
        List<Product> listProducts = productService.findAll();
        if (listProducts.isEmpty()) {
            System.out.println("Không có sản phẩm nào trên hệ thống. Vui lòng thêm mới sản phẩm.");
        }
        System.out.println(Product.getTableHeader());
        for (Product p : listProducts) {
            System.out.println(p);
            System.out.println(Product.getSeparatorLine());
        }
    }

    public static void createProducts(Scanner scanner) {
        int size = Validator.validateInt(scanner, "Nhập vào số sản phẩm cần thêm mới: ");
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
        displayListProducts();
    }

    public static void updateProduct(Scanner scanner) {
        int productId = Validator.validateInt(scanner, "Nhập vào mã sản phẩm cần cập nhật: ");
        Product product = productService.findById(productId);

        if (product != null) {
            do {
                System.out.println("\n===== CHỌN THÔNG TIN MUỐN CẬP NHẬT =====");
                System.out.println("1. Cập nhật tên sản phẩm.");
                System.out.println("2. Cập nhật giá sản phẩm.");
                System.out.println("3. Cập nhật số lượng tồn kho sản phẩm.");
                System.out.println("4. Cập nhật thương hiệu sản phẩm.");
                System.out.println("5. Hoàn tất và lưu thay đổi");
                int choice = Validator.validateInt(scanner, "Nhập lựa chọn của bạn: ");
                switch (choice) {
                    case 1:
                        product.setName(Validator.validateString(scanner, "Nhập vào tên mới của sản phẩm: ", 1, 100));
                        break;
                    case 2:
                        product.setPrice(Validator.validateDouble(scanner, "Nhập vào giá mới của sản phẩm: "));
                        break;
                    case 3:
                        product.setStock(Validator.validateInt(scanner, "Nhập vào số lượng tồn kho mới của sản phẩm: "));
                        break;
                    case 4:
                        product.setBrand(Validator.validateString(scanner, "Nhập vào thương hiệu mới của sản phẩm: ", 1, 50));
                        break;
                    case 5:
                        boolean result = productService.update(product);
                        if (result) {
                            System.out.println("Cập nhật sản phẩm thành công!");
                            displayListProducts();
                        } else {
                            System.err.println("Có lỗi trong quá trình cập nhật");
                        }
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1->5!!!");
                }
            } while (true);
        } else {
            System.err.println("Mã sản phẩm vừa nhập không tồn tại, vui lòng nhập mã sản phẩm phù hợp.");
        }
    }

    public static void deleteProduct(Scanner scanner) {
        int productId = Validator.validateInt(scanner, "Nhập mã sản phẩm cần xóa: ");
        if (productService.findById(productId) != null) {
            Product product = new Product();
            product.setId(productId);
            boolean result = productService.delete(product);
            if (result) {
                System.out.println("Xóa sản phẩm thành công");
                displayListProducts();
            } else {
                System.err.println("Có lỗi trong quá trình xóa.");
            }
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void searchByProName(Scanner scanner) {
        String name = Validator.validateString(scanner, "Nhập tên sản phẩm muốn tìm: ", 1, 100);
        List<Product> products = productService.findByName(name);

        if (!products.isEmpty()) {
            System.out.println("Danh sách sản phẩm thuộc tên " + name + ":");
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm nào thuộc tên: " + name);
        }
    }

    public static void searchByBrand(Scanner scanner) {
        String brand = Validator.validateString(scanner, "Nhập nhãn hàng cần tìm: ", 1, 50);
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
        double minPrice = Validator.validateDouble(scanner, "Nhập giá tối thiểu: ");
        double maxPrice = Validator.validateDouble(scanner, "Nhập giá tối đa: ");

        if (minPrice > maxPrice) {
            System.err.println("Lỗi: Giá tối thiểu không được lớn hơn giá tối đa. Vui lòng nhập lại!");
        }
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
        int minStock = Validator.validateInt(scanner, "Nhập số lượng tồn kho tối thiểu: ");
        int maxStock = Validator.validateInt(scanner, "Nhập số lượng tồn kho tối đa: ");

        if (minStock > maxStock) {
            System.err.println("Lỗi: Số lượng tối thiểu không được lớn hơn số lượng tối đa. Vui lòng nhập lại!");
        }
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
