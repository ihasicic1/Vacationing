package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * city_id == location for visiting
 * @author Ilhan Hasicic
 */

public class City {
    private int city_id;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return city_id == city.city_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id);
    }
}
