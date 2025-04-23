package ra.edu.validate;

import ra.edu.business.model.Product;

import java.util.List;
import java.util.Scanner;

import static ra.edu.presentation.ProductUI.productService;

public class ProductValidator {
    public static String isProductExist(Scanner scanner, String value) {
        List<Product> listProducts = productService.findAll();

        do {
            boolean isExist = false;
            for (Product product : listProducts) {
                if (product.getName().equals(value)) {
                    isExist = true;
                    break;
                }
            }

            // Nếu sản phẩm đã tồn tại, yêu cầu nhập lại
            if (isExist) {
                System.err.println("Sản phẩm đã tồn tại, vui lòng nhập lại");
                value = scanner.nextLine();
            } else {
                // Không tồn tại, thoát vòng lặp
                break;
            }
        } while (true);

        return value;
    }
}