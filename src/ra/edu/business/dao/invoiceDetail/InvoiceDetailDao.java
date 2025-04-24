package ra.edu.business.dao.invoiceDetail;

import ra.edu.business.model.InvoiceDetail;
import ra.edu.utils.AppDao;

import java.util.List;

public interface InvoiceDetailDao extends AppDao<InvoiceDetail> {
    List<InvoiceDetail> findByInvoiceId(int invoiceId);
}
