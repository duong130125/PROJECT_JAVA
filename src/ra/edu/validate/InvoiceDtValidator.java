package ra.edu.validate;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;

import java.util.List;
import java.util.Scanner;

public class InvoiceDtValidator {
    private static final ProductService productService = new ProductServiceImp();

    public static int validateProductExists(Scanner scanner, int productId) {
        List<Product> productList = productService.findAll();

        do {
            boolean exists = false;
            for (Product product : productList) {
                if (product.getId() == productId) {
                    exists = true;
                    System.out.println("Sản phẩm: " + product.getName() + " | Giá: " + product.getPrice());
                    break;
                }
            }

            if (!exists) {
                System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại.");
                System.out.println("Danh sách sản phẩm hiện có:");
                for (Product product : productList) {
                    System.out.println("ID: " + product.getId() + " - " + product.getName() + " - Giá: " + product.getPrice());
                }
                System.out.print("Nhập lại mã sản phẩm: ");
                productId = scanner.nextInt();
            } else {
                break;
            }
        } while (true);

        return productId;
    }
}
