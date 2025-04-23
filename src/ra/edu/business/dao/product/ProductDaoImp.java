package ra.edu.business.dao.product;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL PROC_GET_ALL_PRODUCT()}")) {
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getBoolean("status")
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean save(Product product) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL PROC_INSERT_PRODUCT(?, ?, ?, ?)}")) {
            call.setString(1, product.getName());
            call.setString(2, product.getBrand());
            call.setDouble(3, product.getPrice());
            call.setInt(4, product.getStock());
            call.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL PROC_UPDATE_PRODUCT(?, ?, ?, ?, ?)}")) {
            call.setInt(1, product.getId());
            call.setString(2, product.getName());
            call.setString(3, product.getBrand());
            call.setDouble(4, product.getPrice());
            call.setInt(5, product.getStock());
            call.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL PROC_DELETE_PRODUCT(?)}")) {
            call.setInt(1, product.getId());
            call.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_id(?)}");
            callSt.setInt(1, id);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public List<Product> findByBrand(String brand) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL PROC_FIND_BY_BRAND(?)}")) {
            call.setString(1, brand);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("brand"),
                        rs.getDouble("price"), rs.getInt("stock"), rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findByPriceRange(double min, double max) {
        List<Product> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement call = conn.prepareCall("{CALL PROC_FIND_BY_PRICE_RANGE(?, ?)}")) {
            call.setDouble(1, min);
            call.setDouble(2, max);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("brand"),
                        rs.getDouble("price"), rs.getInt("stock"), rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> findByStock(int min,  int max) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_FIND_PRODUCT_BY_STOCK(?, ?)}");
            callSt.setInt(1, min);
            callSt.setInt(2, max);

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return productList;
    }
}
