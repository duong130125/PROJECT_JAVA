package ra.edu.business.service.invoice;

import ra.edu.business.model.Invoice;
import ra.edu.utils.AppService;

import java.util.List;

public interface InvoiceService extends AppService<Invoice> {
    List<Invoice> searchByCustomerName(String cusName);
    List<Invoice> searchByDate(String date);
}
