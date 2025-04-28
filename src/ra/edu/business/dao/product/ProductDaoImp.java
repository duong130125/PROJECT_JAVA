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
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_GET_ALL_PRODUCT()}");

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

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_INSERT_PRODUCT(?, ?, ?, ?)}");
            callSt.setString(1, product.getName());
            callSt.setString(2, product.getBrand());
            callSt.setDouble(3, product.getPrice());
            callSt.setInt(4, product.getStock());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return false;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_UPDATE_PRODUCT(?, ?, ?, ?, ?)}");
            callSt.setInt(1, product.getId());
            callSt.setString(2, product.getName());
            callSt.setString(3, product.getBrand());
            callSt.setDouble(4, product.getPrice());
            callSt.setInt(5, product.getStock());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return false;
    }

    @Override
    public boolean delete(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_DELETE_PRODUCT(?)}");
            callSt.setInt(1, product.getId());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return false;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL get_product_by_id(?)}");
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
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_FIND_BY_PRO_NAME(?)}");
            callSt.setString(1, name);

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

    @Override
    public List<Product> findByBrand(String brand) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_FIND_BY_BRAND(?)}");
            callSt.setString(1, brand);

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

    @Override
    public List<Product> findByPriceRange(double min, double max) {
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_FIND_BY_PRICE_RANGE(?, ?)}");
            callSt.setDouble(1, min);
            callSt.setDouble(2, max);

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
