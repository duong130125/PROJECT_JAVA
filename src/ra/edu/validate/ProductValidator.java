package ra.edu.validate;

import ra.edu.business.model.Product;

import java.util.List;
import java.util.Scanner;

import static ra.edu.presentation.ProductUI.productService;

public class ProductValidator {
    public static String isProductExist(Scanner scanner, String value) {
        List<Product> listProducts = productService.findAll();
        do {
            boolean isDuplicate = false;
            for (Product product : listProducts) {
                if (product.getName().equals(value)) {
                    isDuplicate = true;
                    break;
                }
            }

            // Nếu trùng lặp, yêu cầu nhập lại
            if (isDuplicate) {
                System.err.println("Dữ liệu bị trùng lặp, vui lòng nhập lại");
                value = scanner.nextLine();
            } else {
                // Không trùng lặp, thoát vòng lặp
                break;
            }
        } while (true);

        return value;
    }
}
