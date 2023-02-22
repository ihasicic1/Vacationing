package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * class that holds info about tour reservations
 * @author Ilhan Hasicic
 */

public class Booking implements Idable {
    private int id;
    private Double ticket_price;

    private Tour tour_id;

    private int customer_id;

    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Tour getTourId() {
        return tour_id;
    }

    public void setTourId(Tour tour_id) {
        this.tour_id = tour_id;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && tour_id == booking.tour_id && customer_id == booking.customer_id && Objects.equals(ticket_price, booking.ticket_price) && Objects.equals(date, booking.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket_price, tour_id, customer_id, date);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", ticket_price=" + ticket_price +
                ", tour_id=" + tour_id +
                ", customer_id=" + customer_id +
                ", date=" + date +
                '}';
    }
}
