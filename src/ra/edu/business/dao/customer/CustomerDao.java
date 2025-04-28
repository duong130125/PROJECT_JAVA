package ra.edu.business.dao.customer;

import ra.edu.business.model.Customer;
import ra.edu.utils.AppDao;

import java.util.List;

public interface CustomerDao extends AppDao<Customer> {
    Customer findById(int id);
    List<Customer> findByName(String name);
}
