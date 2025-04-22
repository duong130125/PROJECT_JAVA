package ra.edu.business.dao.invoice;

import ra.edu.business.model.Invoice;
import ra.edu.utils.AppDao;

import java.util.List;

public interface InvoiceDao extends AppDao<Invoice> {
    List<Invoice> searchByCustomerName(String cusName);
    List<Invoice> searchByDate(String date);
}
