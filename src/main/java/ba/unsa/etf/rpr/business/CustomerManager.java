package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.util.List;

/**
 * Business Logic Layer for Customers
 */
public class CustomerManager {

    public List<Customer> searchCustomersByFirstName(String firstName) throws MyException {
        return DaoFactory.customerDao().searchByFirstName(firstName);
    }

    public List<Customer> searchCustomersByLastName(String lastName) throws MyException {
        return DaoFactory.customerDao().searchByLastName(lastName);
    }

    public Customer getById(int id) throws MyException {
        return DaoFactory.customerDao().getById(id);
    }

    public Customer add(Customer customer) throws MyException {
        return DaoFactory.customerDao().add(customer);
    }

    public void update(Customer customer) throws MyException {
        DaoFactory.customerDao().update(customer);
    }

    public void delete(int id) throws MyException {
        DaoFactory.customerDao().delete(id);
    }

    public List<Customer> getAll() throws MyException {
        return DaoFactory.customerDao().getAll();
    }

    public Customer getByEmail(String email) throws MyException {
        return DaoFactory.customerDao().getByEmail(email);
    }

}
