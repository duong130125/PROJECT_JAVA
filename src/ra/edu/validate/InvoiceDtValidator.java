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
                    System.out.println(Product.getTableHeader());
                    System.out.println(product);
                    System.out.println(Product.getSeparatorLine());
                    break;
                }
            }

            if (!exists) {
                System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại.");
                System.out.println("============== DANH SÁCH SẢN PHẨM ================");
                System.out.println(Product.getTableHeader());
                for (Product p : productList) {
                    System.out.println(p);
                    System.out.println(Product.getSeparatorLine());
                }
                System.out.print("Nhập lại mã sản phẩm: ");
                productId = Integer.parseInt(scanner.nextLine());
            } else {
                break;
            }
        } while (true);

        return productId;
    }

    public static boolean validateProductStock(int productId, int quantity) {
        Product product = productService.findById(productId);
        if (product != null) {
            if (product.getStock() >= quantity) {
                return true;
            } else {
                System.err.println("Số lượng tồn kho không đủ. Hiện chỉ có " + product.getStock() + " sản phẩm.");
                return false;
            }
        }
        System.err.println("Không tìm thấy sản phẩm với mã " + productId);
        return false;
    }

    public static boolean validateProductStatus(int productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            if (product.isStatus()) {
                return true;
            } else {
                System.err.println("Sản phẩm hiện đang không hoạt động và không thể được đặt hàng.");
                return false;
            }
        }
        System.err.println("Không tìm thấy sản phẩm với mã " + productId);
        return false;
    }
}