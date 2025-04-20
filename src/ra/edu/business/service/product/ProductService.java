package ra.edu.business.service.product;

import ra.edu.business.model.Product;
import ra.edu.utils.AppService;

import java.util.List;

public interface ProductService extends AppService<Product> {
    Product findById(int id);
    Product findByName(String name);

    List<Product> findByBrand(String brand);
    List<Product> findByPriceRange(double min, double max);
    List<Product> findByStock(int minStock, int maxStock);
}
