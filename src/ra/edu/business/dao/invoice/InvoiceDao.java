package ra.edu.business.dao.invoice;

import ra.edu.business.model.Invoice;
import ra.edu.utils.AppDao;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDao extends AppDao<Invoice> {
    List<Invoice> searchByCustomerName(String cusName);
    List<Invoice> searchByDate(LocalDate date);

    List<Object[]> getRevenueByDay();
    List<Object[]> getRevenueByMonth();
    List<Object[]> getRevenueByYear();

}
