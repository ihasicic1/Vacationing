package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * class for all available tours in our travel agency
 * @author Ilhan Hasicic
 */

public class Tour implements Idable{
    private int id;
    private String destination;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id && Objects.equals(destination, tour.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination);
    }
}
