package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.util.List;

public class TourManager {

    public Tour getById(int id) throws MyException {
        return DaoFactory.tourDao().getById(id);
    }

    public Tour add(Tour tour) throws MyException {
        return DaoFactory.tourDao().add(tour);
    }

    public void update(Tour tour) throws MyException {
        DaoFactory.tourDao().update(tour);
    }

    public void delete(int id) throws MyException {
        DaoFactory.tourDao().delete(id);
    }

    public List<Tour> getAll() throws MyException {
        return DaoFactory.tourDao().getAll();
    }

}
