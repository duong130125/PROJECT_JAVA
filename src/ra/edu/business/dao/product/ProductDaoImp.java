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
                        rs.getInt("stock")
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
            return call.executeUpdate() > 0;
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
            return call.executeUpdate() > 0;
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
            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
