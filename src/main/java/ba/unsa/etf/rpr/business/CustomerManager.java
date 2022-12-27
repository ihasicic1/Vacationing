package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.MyException;

/**
 * Business Logic Layer for Customers
 */
public class CustomerManager {

    public Customer getById(int id) throws MyException {
        return DaoFactory.customerDao().getById(id);
    }

    public Customer add(Customer customer) throws MyException {
        return DaoFactory.customerDao().add(customer);
    }

    public void update(Customer customer) throws MyException {
        DaoFactory.customerDao().update(customer);
    }
}
