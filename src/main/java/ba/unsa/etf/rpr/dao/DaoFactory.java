package ba.unsa.etf.rpr.dao;

/**
 *Factory method for singleton implementation of DAOs
 */
public class DaoFactory {
    private static final CustomerDao customerDao = new CustomerDaoSQLImpl();
    private static final BookingDao bookingDao = new BookingDaoSQLImpl();
    private static final TourDao tourDao = new TourDaoSQLImpl();

    private DaoFactory(){

    }

    public static CustomerDao customerDao(){
        return customerDao;
    }

    public static BookingDao bookingDao(){
        return bookingDao;
    }

    public static TourDao tourDao(){
        return tourDao;
    }

}
