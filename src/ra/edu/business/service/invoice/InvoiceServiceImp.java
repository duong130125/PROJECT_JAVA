package ra.edu.business.service.invoice;

import ra.edu.business.dao.invoice.InvoiceDao;
import ra.edu.business.dao.invoice.InvoiceDaoImp;
import ra.edu.business.model.Invoice;

import java.util.List;

public class InvoiceServiceImp implements InvoiceService {
    private final InvoiceDao invoiceDao = new InvoiceDaoImp();

    @Override
    public List<Invoice> findAll() {
        return invoiceDao.findAll();
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
    public boolean save(Invoice invoice) {
        return invoiceDao.save(invoice);
    }


    @Override
    public List<Invoice> searchByCustomerName(String cusName) {
        return invoiceDao.searchByCustomerName(cusName);
    }

    @Override
    public List<Invoice> searchByDate(String date) {
        return invoiceDao.searchByDate(date);
    }
}
