package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * class for all available tours in our travel agency
 * @author Ilhan Hasicic
 */

public class Tour implements Idable{
    private int id;
    private String destination;

    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id == tour.id && Objects.equals(destination, tour.destination) && Objects.equals(price, tour.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destination, price);
    }

    @Override
    public String toString() {
        return destination;
    }
}
