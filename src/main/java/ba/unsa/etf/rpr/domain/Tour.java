package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * class for all available tours in our travel agency
 * @author Ilhan Hasicic
 */

public class Tour {
    private int tour_id;
    private City city_id;

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public City getCity_id() {
        return city_id;
    }

    public void setCity_id(City city_id) {
        this.city_id = city_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return tour_id == tour.tour_id && Objects.equals(city_id, tour.city_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tour_id, city_id);
    }
}
