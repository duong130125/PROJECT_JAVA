package ra.edu.business.service.product;

import ra.edu.business.dao.product.ProductDao;
import ra.edu.business.dao.product.ProductDaoImp;
import ra.edu.business.model.Product;

import java.util.List;

public class ProductServiceImp implements ProductService {
    private final ProductDao productDao = new ProductDaoImp();

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product);
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product>  findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public List<Product> findByBrand(String brand) {
        return productDao.findByBrand(brand);
    }

    @Override
    public List<Product> findByPriceRange(double min, double max) {
        return productDao.findByPriceRange(min, max);
    }

    @Override
    public List<Product> findByStock(int min, int max) {
        return productDao.findByStock(min, max);
    }
}
