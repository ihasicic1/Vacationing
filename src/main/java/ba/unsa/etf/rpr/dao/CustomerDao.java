package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;

import java.util.List;

/**
 * Dao interface for Customer domain bean
 * @author Ilhan Hasicic
 */
public interface CustomerDao extends Dao<Customer>{
    /**
     * returns a customer with given id
     * @param id
     * @return list containing one customer
     */
    List<Customer> searchByCustomerId(int id);

    /**
     * returns all customers with given first name
     * @param firstName
     * @return list of customers
     */
    List<Customer> searchByFirstName(String firstName);


    /**
     * returns all customers with given last name
     * @param lastName
     * @return list of customers
     */
    List<Customer> searchByLastName(String lastName);
}
