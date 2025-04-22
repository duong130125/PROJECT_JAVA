package ra.edu.business.dao.invoice;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImp implements InvoiceDao {
    @Override
    public List<Invoice> findAll() {
        List<Invoice> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{call PROC_FIND_ALL_INVOICES()}");
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getString("customer_id"));
                i.setCreated_At(rs.getString("created_at"));
                i.setTotal_Amount(rs.getDouble("total_amount"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Invoice invoice) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{call PROC_INSERT_INVOICE(?, ?, ?)}")) {
            cs.setString(1, invoice.getCustomer_Id());
            cs.setString(2, invoice.getCreated_At());
            cs.setDouble(3, invoice.getTotal_Amount());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Invoice invoice) {
        return false;
    }

    @Override
    public boolean delete(Invoice invoice) {
        return false;
    }

    @Override
    public List<Invoice> searchByCustomerName(String keyword) {
        List<Invoice> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_NAME(?)}")) {
            cs.setString(1, "%" + keyword + "%");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getString("customer_id"));
                i.setCreated_At(rs.getString("created_at"));
                i.setTotal_Amount(rs.getDouble("total_amount"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Invoice> searchByDate(String date) {
        List<Invoice> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_DATE(?)}")) {
            cs.setString(1, date);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getString("customer_id"));
                i.setCreated_At(rs.getString("created_at"));
                i.setTotal_Amount(rs.getDouble("total_amount"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}