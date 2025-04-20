package ra.edu.business.service.customer;

import ra.edu.business.model.Customer;
import ra.edu.utils.AppService;

public interface CustomerService extends AppService<Customer> {
    Customer findById(int id);
    Customer findByName(String name);
}
