package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.TourManager;
import ba.unsa.etf.rpr.domain.Tour;
import ba.unsa.etf.rpr.exceptions.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TourManagerTest {
    TourManager tourManager = new TourManager();
    @Test
    void searchByDestinationTest() throws MyException {
       tourManager.add(createTour());
       Assertions.assertEquals("Zimbabve", tourManager.searchByDestination("Zimbabve").getDestination());
       tourManager.delete(tourManager.searchByDestination("Zimbabve").getId());
    }

    @Test
    void getAllTest() throws MyException {
        List<Tour> tourList = tourManager.getAll();
        tourManager.add(createTour());
        boolean isValid = false;
        List<Tour> tempList = tourManager.getAll();
        if(tourList.size() + 1 == tempList.size()) isValid = true;
        Assertions.assertTrue(isValid);
        tourManager.delete(tourManager.searchByDestination("Zimbabve").getId());
    }

    private Tour createTour(){
        Tour tour = new Tour();
        tour.setDestination("Zimbabve");
        tour.setPrice(2.);
        return tour;
    }
}
