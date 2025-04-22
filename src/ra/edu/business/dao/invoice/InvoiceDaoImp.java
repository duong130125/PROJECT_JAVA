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
                i.setCustomer_Id(rs.getInt("customer_id"));
                i.setCreated_At(LocalDate.parse(rs.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
            callSt.setInt(1, invoice.getCustomer_Id());
            java.sql.Date sqlDate = java.sql.Date.valueOf(invoice.getCreated_At());
            callSt.setDate(2, sqlDate);
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
                i.setCustomer_Id(rs.getInt("customer_id"));
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
    public List<Invoice> searchByDate(LocalDate date) {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_DATE(?)}");
            callSt.setDate(1, Date.valueOf(date));
            rs = callSt.executeQuery();

            while (rs.next()) {
                Invoice i = new Invoice();
                i.setId(rs.getInt("id"));
                i.setCustomer_Id(rs.getInt("customer_id"));
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
    public List<Object[]> getRevenueByDay() {
        return getRevenueByQuery("CALL PROC_REVENUE_BY_DAY()");
    }

    @Override
    public List<Object[]> getRevenueByMonth() {
        return getRevenueByQuery("CALL PROC_REVENUE_BY_MONTH()");
    }

    @Override
    public List<Object[]> getRevenueByYear() {
        return getRevenueByQuery("CALL PROC_REVENUE_BY_YEAR()");
    }

    private List<Object[]> getRevenueByQuery(String query) {
        List<Object[]> result = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(query);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getString(1); // ngày / tháng / năm
                row[1] = rs.getDouble(2); // tổng doanh thu
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}