package ba.unsa.etf.rpr.domain;

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


}
