package ra.edu.business.dao.product;

import ra.edu.business.model.Product;
import ra.edu.utils.AppDao;

import java.util.List;

public interface ProductDao extends AppDao<Product> {
    Product findById(int id);

    List<Product> findByBrand(String brand);
    List<Product> findByPriceRange(double min, double max);
    List<Product> findByStock(int minStock, int maxStock);
}
