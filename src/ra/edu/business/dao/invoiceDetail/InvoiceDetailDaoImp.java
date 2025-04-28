package ra.edu.business.dao.invoiceDetail;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.InvoiceDetail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailDaoImp implements InvoiceDetailDao {
    @Override
    public List<InvoiceDetail> findAll() {
        List<InvoiceDetail> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_GET_ALL_INVOICE_DETAIL()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setId(rs.getInt("id"));
                invoiceDetail.setInvoiceId(rs.getInt("invoice_id"));
                invoiceDetail.setProductId(rs.getInt("product_id"));
                invoiceDetail.setQuantity(rs.getInt("quantity"));
                invoiceDetail.setUnitPrice(rs.getDouble("unit_price"));
                list.add(invoiceDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public boolean save(InvoiceDetail invoiceDetail) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_INSERT_INVOICE_DETAIL(?, ?, ?)}");
            callSt.setInt(1, invoiceDetail.getInvoiceId());
            callSt.setInt(2, invoiceDetail.getProductId());
            callSt.setInt(3, invoiceDetail.getQuantity());
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
    public boolean update(InvoiceDetail invoiceDetail) {
        return false;
    }

    @Override
    public boolean delete(InvoiceDetail invoiceDetail) {
        return false;
    }

    @Override
    public List<InvoiceDetail> findByInvoiceId(int invoiceId) {
        List<InvoiceDetail> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_invoice_detail_by_invoice_id(?)}");
            callSt.setInt(1, invoiceId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setId(rs.getInt("id"));
                invoiceDetail.setInvoiceId(rs.getInt("invoice_id"));
                invoiceDetail.setProductId(rs.getInt("product_id"));
                invoiceDetail.setQuantity(rs.getInt("quantity"));
                invoiceDetail.setUnitPrice(rs.getDouble("unit_price"));
                list.add(invoiceDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }
}
