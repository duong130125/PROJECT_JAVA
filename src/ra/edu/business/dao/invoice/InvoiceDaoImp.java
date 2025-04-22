package ra.edu.business.dao.invoice;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Invoice;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImp implements InvoiceDao {
    @Override
    public List<Invoice> findAll() {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_FIND_ALL_INVOICES()}");
            rs = callSt.executeQuery();

            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getString("customer_id"));
                i.setCreated_At(rs.getDate("created_at").toLocalDate());
                i.setTotal_Amount(rs.getDouble("total_amount"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public boolean save(Invoice invoice) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_INSERT_INVOICE(?, ?, ?)}");
            callSt.setString(1, invoice.getCustomer_Id());
            LocalDate createdAt = invoice.getCreated_At();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateString = createdAt.format(formatter);
            callSt.setString(2, dateString);
            callSt.setDouble(3, invoice.getTotal_Amount());
            result = callSt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
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
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_NAME(?)}");
            callSt.setString(1, "%" + keyword + "%");
            rs = callSt.executeQuery();

            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getString("customer_id"));
                i.setCreated_At(rs.getDate("created_at").toLocalDate());
                i.setTotal_Amount(rs.getDouble("total_amount"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Invoice> searchByDate(String date) {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_DATE(?)}");
            callSt.setString(1, date);
            rs = callSt.executeQuery();

            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getString("customer_id"));
                i.setCreated_At(rs.getDate("created_at").toLocalDate());
                i.setTotal_Amount(rs.getDouble("total_amount"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }
}