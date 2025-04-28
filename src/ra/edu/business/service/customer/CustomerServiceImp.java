package ra.edu.business.service.customer;

import ra.edu.business.dao.customer.CustomerDao;
import ra.edu.business.dao.customer.CustomerDaoImp;
import ra.edu.business.model.Customer;

import java.util.List;

public class CustomerServiceImp implements CustomerService {
    private final CustomerDao customerDao = new CustomerDaoImp();

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public boolean save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerDao.delete(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerDao.findByName(name);
    }
}
