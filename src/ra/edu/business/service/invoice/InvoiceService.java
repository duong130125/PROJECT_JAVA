package ra.edu.business.service.invoice;

import ra.edu.business.model.Invoice;
import ra.edu.utils.AppService;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService extends AppService<Invoice> {
    List<Invoice> searchByCustomerName(String cusName);
    List<Invoice> searchByDate(LocalDate date);

    List<Object[]> getRevenueByDay();
    List<Object[]> getRevenueByMonth();
    List<Object[]> getRevenueByYear();

}
