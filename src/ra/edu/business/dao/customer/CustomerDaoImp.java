package ra.edu.business.dao.customer;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImp implements CustomerDao {
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_GET_ALL_CUSTOMERS()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return customers;
    }

    @Override
    public boolean save(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isSuccess = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_INSERT_CUSTOMER(?, ?, ?, ?)}");
            callSt.setString(1, customer.getName());
            callSt.setString(2, customer.getEmail());
            callSt.setString(3, customer.getPhone());
            callSt.setString(4, customer.getAddress());

            isSuccess = callSt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return isSuccess;
    }

    @Override
    public boolean update(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isSuccess = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_UPDATE_CUSTOMER(?, ?, ?, ?, ?)}");
            callSt.setInt(1, customer.getId());
            callSt.setString(2, customer.getName());
            callSt.setString(3, customer.getEmail());
            callSt.setString(4, customer.getPhone());
            callSt.setString(5, customer.getAddress());

            isSuccess = callSt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return isSuccess;
    }

    @Override
    public boolean delete(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isSuccess = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_DELETE_CUSTOMER(?)}");
            callSt.setInt(1, customer.getId());

            isSuccess = callSt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return isSuccess;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_FIND_CUSTOMER_BY_ID(?)}");
            callSt.setInt(1, id);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return customer;
    }

    @Override
    public Customer findByName(String name) {
        return null;
    }
}
