package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.util.List;

/**
 * Dao interface for Tour domain bean
 * @author Ilhan Hasicic
 */
public interface TourDao extends Dao<Tour>{
    /**
     * returns a tour with given city name
     * @param name
     * @return list of a tour because city = location = tour
     */
    List<Tour> searchByCity(String name) throws MyException;
}
