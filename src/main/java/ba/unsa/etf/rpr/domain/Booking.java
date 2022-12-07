package ba.unsa.etf.rpr.domain;

/**
 * class that holds info about tour reservations
 * @author Ilhan Hasicic
 */

public class Booking {
    private int booking_id;
    private Double ticket_price;
    private Tour tour_id;

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


}
