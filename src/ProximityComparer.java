import oop.ex3.searchengine.Hotel;
import java.util.Comparator;
import java.lang.Math;

/**
 * compares by proximity to the original longitude and latitude
 */
public class ProximityComparer implements Comparator<Hotel> {

    private double longitude;
    private double latitude;

    /**
     * constructor for the ProximetyComaprer
     * @param longitude the original longitude
     * @param latitude the the original latitude
     */
    public ProximityComparer(double longitude, double latitude){

        // sets the longitude and latitude
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * compares first hotel and second hotel and finds who is closer
     * @param firstHotel our first hotel
     * @param secondHotel our second hotel
     * @return which hotel is closer
     */
    @Override
    public int compare(Hotel firstHotel, Hotel secondHotel) {

        // finds the distance between that spot and the original spot
        double firstHotelDistance = this.Distance(firstHotel);
        double secondHotelDistance = this.Distance(secondHotel);

        // compares the values of the distance
        int compareValue = Double.valueOf(firstHotelDistance).compareTo(secondHotelDistance);

        // if the value of the distance is the same sort by numbers of points of interest
        if(compareValue == 0){
            return Integer.valueOf(secondHotel.getNumPOI()).compareTo(firstHotel.getNumPOI());
        }
        else{
            return compareValue;
        }
    }

    /**
     * gets the distance between the hotel and the spot
     * @param current our current hotel
     * @return the distance
     */
    private Double Distance(Hotel current){

        // calculates the distance for longitude and latitude
        double latitude_distance = Math.pow((this.latitude - current.getLatitude()), 2);
        double longitude_distance = Math.pow((this.longitude - current.getLongitude()), 2);

        // calculates the sqrt of that distance
        return Math.sqrt(longitude_distance + latitude_distance);
    }
}
