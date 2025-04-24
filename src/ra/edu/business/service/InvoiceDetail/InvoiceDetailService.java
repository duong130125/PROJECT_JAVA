package ra.edu.business.service.InvoiceDetail;

import ra.edu.business.model.InvoiceDetail;
import ra.edu.utils.AppService;

import java.util.List;

public interface InvoiceDetailService extends AppService<InvoiceDetail> {
    List<InvoiceDetail> findByInvoiceId(int invoiceId);
}
