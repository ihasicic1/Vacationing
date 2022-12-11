package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * class that holds info about tour reservations
 * @author Ilhan Hasicic
 */

public class Booking {
    private int booking_id;
    private Double ticket_price;
    private Tour tour_id;
    private Customer customer_id;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Tour getTour_id() {
        return tour_id;
    }

    public void setTour_id(Tour tour_id) {
        this.tour_id = tour_id;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "booking_id=" + booking_id +
                ", ticket_price=" + ticket_price +
                ", tour_id=" + tour_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return booking_id == booking.booking_id && Objects.equals(ticket_price, booking.ticket_price) && Objects.equals(tour_id, booking.tour_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking_id, ticket_price, tour_id);
    }
}
