import oop.ex3.searchengine.Hotel;
import java.util.Comparator;

/**
 * compares a list by all the property names of the hotels
 */
public class StarComparer implements Comparator<Hotel> {

    /**
     * uses the compare function to compare between Hotels property names
     * @param firstHotel the first hotel
     * @param secondHotel our second hotel
     * @return the hotels sorted alphabetic
     */
    @Override
    public int compare(Hotel firstHotel, Hotel secondHotel) {

        // compares the hotels
        int starRating = secondHotel.getStarRating() - firstHotel.getStarRating();

        // checks if the star ratings are equal
        if(starRating != 0){
            return starRating;
        }
        // if the property ratings are equal get the one with the earlier name
        else{
            return firstHotel.getPropertyName().compareTo((secondHotel.getPropertyName()));
        }
    }
}
