package ra.edu.business.dao.customer;

import ra.edu.business.model.Customer;
import ra.edu.utils.AppDao;

public interface CustomerDao extends AppDao<Customer> {
    Customer findById(int id);
    Customer findByName(String name);
}
