package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Tour;

import java.util.List;

/**
 * Dao interface for Tour domain bean
 * @author Ilhan Hasicic
 */
public interface TourDao extends Dao<Tour>{
    /**
     * returns a tour with given id
     * @param id
     * @return list of a tour
     */
    List<Tour> searchById(int id);

    /**
     * returns a tour with given city name
     * @param name
     * @return list of a tour because city = location = tour
     */
    List<Tour> searchByCity(String name);
}
