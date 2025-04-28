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

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_FIND_ALL_INVOICES()}");
            ResultSet rs = callSt.executeQuery();

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
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isSuccess = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL PROC_INSERT_INVOICE(?, ?, ?, ?)}");

            callSt.setInt(1, invoice.getCustomer_Id());
            callSt.setDate(2, java.sql.Date.valueOf(invoice.getCreated_At()));
            callSt.setDouble(3, invoice.getTotal_Amount());
            callSt.registerOutParameter(4, Types.INTEGER); // OUT param

            isSuccess = callSt.executeUpdate() > 0;

            if (isSuccess) {
                int invoiceId = callSt.getInt(4);
                invoice.setId(invoiceId); // gán lại cho object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return isSuccess;
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
    public List<Invoice> searchByCustomerName(String cusName) {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_NAME(?)}");
            callSt.setString(1, "%" + cusName + "%");
            ResultSet rs = callSt.executeQuery();

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

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_SEARCH_INVOICE_BY_DATE(?)}");
            callSt.setDate(1, Date.valueOf(date));
            ResultSet rs = callSt.executeQuery();

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
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall(query);

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getString(1); // ngày / tháng / năm
                row[1] = rs.getDouble(2); // tổng doanh thu
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return result;
    }
}