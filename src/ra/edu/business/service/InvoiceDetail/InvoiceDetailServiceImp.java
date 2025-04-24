package ra.edu.business.service.InvoiceDetail;

import ra.edu.business.dao.invoiceDetail.InvoiceDetailDao;
import ra.edu.business.dao.invoiceDetail.InvoiceDetailDaoImp;
import ra.edu.business.model.InvoiceDetail;

import java.util.List;

public class InvoiceDetailServiceImp implements InvoiceDetailService {
    private final InvoiceDetailDao invoiceDetailDao = new InvoiceDetailDaoImp();

    @Override
    public List<InvoiceDetail> findAll() {
        return invoiceDetailDao.findAll();
    }

    @Override
    public boolean save(InvoiceDetail invoiceDetail) {
        return invoiceDetailDao.save(invoiceDetail);
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
        return invoiceDetailDao.findByInvoiceId(invoiceId);
    }
}
