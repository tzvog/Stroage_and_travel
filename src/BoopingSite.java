import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import java.util.*;
import java.lang.Math;

/**
 * implements a class of Boopingsite
 */
public class BoopingSite{

    private Hotel [] hotels;
    private final Hotel [] EMPTY = new Hotel[0];

    /**
     * creates a booping site object
     * @param name the name of the db we are dealing with
     */
    public BoopingSite(String name){
        this.hotels = HotelDataset.getHotels(name);
    }

    /**
     * gets the hotel by the city ratings
     * @param city the city we should get the hotels by
     * @return the list of hotels in the city by the ratings
     */
    public Hotel[] getHotelsInCityByRating(String city){

        // gets the hotels in my city
        ArrayList<Hotel> cityHotels = this.getHotelsInCity(city);

        // sorts my collection for me by city
        cityHotels.sort(new StarComparer());

        // converts it back to an array that it can work with
        return cityHotels.toArray(new Hotel[cityHotels.size()]);
    }

    /**
     * gets the hotel by proximity
     * @param latitude the hotel latitude
     * @param longitude the hotel longitude
     * @return gets the list of hotel by proximity
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){

        // checks if the coordinates are valid
        if(this.notValidCoordinates(latitude, longitude)) {
            return EMPTY;
        }

        // converts the hotel list into an Arraylist to work with
        ArrayList<Hotel> proximityHotels = new ArrayList<Hotel>(Arrays.asList(this.hotels));

        // sorts the array by the distance
        proximityHotels.sort(new ProximityComparer(longitude, latitude));

        // returns a sorted array
        return proximityHotels.toArray(new Hotel[proximityHotels.size()]);
    }

    /**
     * gets hotel in the city by proximity
     * @param city our city
     * @param latitude the latitude
     * @param longitude the longitude
     * @return the hotel in city by proximity
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){

        // checks if the coordinates are valid
        if(this.notValidCoordinates(latitude, longitude)){
            return EMPTY;
        }

        // gets the hotels in my city
        ArrayList<Hotel> cityHotels = this.getHotelsInCity(city);

        // sorts the hotels by distance
        cityHotels.sort(new ProximityComparer(longitude, latitude));

        // returns sorted array
        return cityHotels.toArray(new Hotel[cityHotels.size()]);
    }

    /**
     * helps us get all the hotels in our list within our chosen city
     * @param city the city we want to find hotels in
     * @return gets the hotels in the city we want
     */
    private ArrayList<Hotel> getHotelsInCity(String city){

        ArrayList<Hotel> hotelsInCity = new ArrayList<Hotel>();

        // goes through all our hotels
        for(Hotel hotel:this.hotels){

            // checks if hotel is in our city
            if(hotel.getCity().equals(city)){
                hotelsInCity.add(hotel);
            }
        }

        // returns the hotels in that city
        return hotelsInCity;
    }

    /**
     * checks if our coordinates are valid
     * @return if the coordinates are invalid
     */
    private boolean notValidCoordinates(double latitude, double longitude){

        // checks if the coordinates are out of range
        return (Math.abs(latitude) > 90 || Math.abs(longitude) > 180);
    }
}

