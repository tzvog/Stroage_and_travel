import oop.ex3.searchengine.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoopingSiteTest {

    private BoopingSite smallBoopingSite;
    private BoopingSite emptyBoopingSite;
    private BoopingSite bigBoopingSite;

    private final String DATA_SET_ONE = "hotels_tst1.txt";
    private final String DATA_SET_TWO = "hotels_tst2.txt";
    private final String DATA_BIG = "hotels_dataset.txt";
    private final String FAKE_CITY = "New York";
    private final String REAL_CITY = "manali";
    private final double correctLatitude = 20.0;
    private final double correctLongitude = 30.0;
    private final double illegalLatitude = -200.0;
    private final double illegalLongitude = -200.0;

    private Hotel [] hotels;

    /**
     * the setup for every test gonna be made
     */
    @Before
    public void setup(){

        this.smallBoopingSite = new BoopingSite(DATA_SET_ONE);
        this.emptyBoopingSite = new BoopingSite(DATA_SET_TWO);
        this.bigBoopingSite = new BoopingSite(DATA_BIG);
    }

    /**
     * checks that all the hotels come from that city small
     */
    @Test
    public void getHotelsInCityByRatingCheckCityIsCorrectSmall() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        // goes through all our hotels
        for(Hotel hotel:this.hotels){
            // checks that the city is the one we want
            if (hotel.getCity().compareTo(REAL_CITY) != 0){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds hotels from the right city", isCorrect);
    }

    /**
     * checks that the ratings are in the correct order
     */
    @Test
    public void getHotelsInCityByRatingCheckCorrectOrderOfRatingSmall() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        for(int i = 0; i < this.hotels.length - 1; i++){

            if (this.hotels[i].getStarRating() < this.hotels[i + 1].getStarRating()){
                 isCorrect = false;
                 break;
            }
        }

        assertTrue("checks if the function really adds the data " +
                "in a correct order by stars ",isCorrect);
    }

    /**
     * checks that the names are in the correct order if stars are equal
     */
    @Test
    public void getHotelsInCityByRatingCheckIfNamesCorrectSmall() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        for(int i = 0; i < this.hotels.length - 1; i++){

            // checks if the ratings are equal
            if(this.hotels[i].getStarRating() == this.hotels[i + 1].getStarRating()){

                // checks that the names are in correct order
                if(this.hotels[i].getPropertyName().compareTo(this.hotels[i + 1].getPropertyName()) > 0 ){
                    isCorrect = false;
                    break;
                }
            }
        }

        assertTrue("checks if the function really adds the data " +
                "in a correct order by name if stars are equal",isCorrect);
    }

    /**
     * checks that all the hotels come from that city empty file
     */
    @Test
    public void getHotelsInCityByRatingCheckCityIsCorrectEmpty() {

        this.hotels = this.emptyBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        // goes through all our hotels
        for(Hotel hotel:this.hotels){
            // checks that the city is the one we want
            if (hotel.getCity().compareTo(REAL_CITY) != 0){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds hotels from the right city", isCorrect);
    }

    /**
     * checks that the ratings are in the correct order empty file
     */
    @Test
    public void getHotelsInCityByRatingCheckCorrectOrderOfRatingEmpty() {

        this.hotels = this.emptyBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        for(int i = 0; i < this.hotels.length - 1; i++){

            if (this.hotels[i].getStarRating() < this.hotels[i + 1].getStarRating()){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds the data " +
                "in a correct order by stars ",isCorrect);
    }

    /**
     * checks that the names are in the correct order if stars are equal empty file
     */
    @Test
    public void getHotelsInCityByRatingCheckIfNamesCorrectEmpty() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        for(int i = 0; i < this.hotels.length - 1; i++){

            // checks if the ratings are equal
            if(this.hotels[i].getStarRating() == this.hotels[i + 1].getStarRating()){

                // checks that the names are in correct order
                if(this.hotels[i].getPropertyName().compareTo(this.hotels[i + 1].getPropertyName()) > 0 ){
                    isCorrect = false;
                    break;
                }
            }
        }

        assertTrue("checks if the function really adds the data " +
                "in a correct order by name if stars are equal",isCorrect);
    }

    /**
     * checks that all the hotels come from that city big
     */
    @Test
    public void getHotelsInCityByRatingCheckCityIsCorrectBig() {

        this.hotels = this.bigBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        // goes through all our hotels
        for(Hotel hotel:this.hotels){
            // checks that the city is the one we want
            if (hotel.getCity().compareTo(REAL_CITY) != 0){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds hotels from the right city", isCorrect);
    }

    /**
     * checks that the ratings are in the correct order big file
     */
    @Test
    public void getHotelsInCityByRatingCheckCorrectOrderOfRatingBig() {

        this.hotels = this.bigBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        for(int i = 0; i < this.hotels.length - 1; i++){

            if (this.hotels[i].getStarRating() < this.hotels[i + 1].getStarRating()){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds the data " +
                "in a correct order by stars ",isCorrect);
    }

    /**
     * checks that the names are in the correct order if stars are equal Big File
     */
    @Test
    public void getHotelsInCityByRatingCheckIfNamesCorrectBig() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByRating(REAL_CITY);

        boolean isCorrect = true;

        for(int i = 0; i < this.hotels.length - 1; i++){

            // checks if the ratings are equal
            if(this.hotels[i].getStarRating() == this.hotels[i + 1].getStarRating()){

                // checks that the names are in correct order
                if(this.hotels[i].getPropertyName().compareTo(this.hotels[i + 1].getPropertyName()) > 0 ){
                    isCorrect = false;
                    break;
                }
            }
        }

        assertTrue("checks if the function really adds the data " +
                "in a correct order by name if stars are equal",isCorrect);
    }
    /**
     * checks what happens when you put a fake city
     */
    @Test
    public void getHotelsInCityByRatingFakeCity() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByRating(FAKE_CITY);

        assertEquals("no hotel in that city", 0, this.hotels.length);
    }

    /**
     * checks the hotels are sorted by proximity small
     */
    @Test
    public void getHotelsByProximityChecksHotelsInAscendingOrderSmall() {

        this.hotels = this.smallBoopingSite.getHotelsByProximity(correctLatitude, correctLongitude);
        boolean isCorrect = this.isDistancesSorted();

        assertTrue("checks if hotels are sorted by proximity",isCorrect);
    }

    /**
     * checks the hotels are sorted by proximity small
     */
    @Test
    public void getHotelsByProximityChecksHotelsInAscendingOrderBig() {

        this.hotels = this.bigBoopingSite.getHotelsByProximity(correctLatitude, correctLongitude);
        boolean isCorrect = this.isDistancesSorted();

        assertTrue("checks if hotels are sorted by proximity",isCorrect);
    }

    /**
     * checks if proximity is same if names are sorted by points of interest small
     */
    @Test
    public void getHotelsByProximityPointsOfInterestComparerSmall() {

        this.hotels = this.smallBoopingSite.getHotelsByProximity(correctLatitude, correctLongitude);

        boolean isCorrect = isPointsOfInterestSortedEqualDistance();

        assertTrue("checks if hotels are sorted by proximity" +
                " and POI are descending if equal", isCorrect);
    }


    /**
     * checks if proximity is same if names are sorted by points of interest big
     */
    @Test
    public void getHotelsByProximityPointsOfInterestComparerBig() {

        this.hotels = this.bigBoopingSite.getHotelsByProximity(correctLatitude, correctLongitude);

        boolean isCorrect = isPointsOfInterestSortedEqualDistance();

        assertTrue("checks if hotels are sorted by proximity" +
                " and POI are descending if equal", isCorrect);
    }

    /**
     * gets an illegal input in latitude
     */
    @Test
    public void getHotelsByProximityIllegalLatitude() {

        this.hotels = this.smallBoopingSite.getHotelsByProximity(illegalLatitude, correctLongitude);

        assertEquals("wrong latitude", 0, this.hotels.length);
    }

    /**
     * gets an illegal input in longitude
     */
    @Test
    public void getHotelsByProximityIllegalLongitude() {

        this.hotels = this.smallBoopingSite.getHotelsByProximity(correctLatitude, illegalLongitude);

        assertEquals("wrong longitude", 0, this.hotels.length);
    }

    /**
     * gets an illegal input in longitude and latitude
     */
    @Test
    public void getHotelsByProximityIllegalLongitudeAndLatitude() {

        this.hotels = this.smallBoopingSite.getHotelsByProximity(illegalLatitude, illegalLongitude);

        assertEquals("wrong latitude and longitude", 0, this.hotels.length);
    }

    /**
     * get hotels in city by proximity checks all hotels in chosen city small file
     */
    @Test
    public void getHotelsInCityByProximityTestCitesSmall() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY, correctLatitude,
                correctLongitude);

        boolean isCorrect = true;

        // goes through all our hotels
        for(Hotel hotel:this.hotels){
            // checks that the city is the one we want
            if (hotel.getCity().compareTo(REAL_CITY) != 0){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds hotels from the right city", isCorrect);
    }

    /**
     * get hotels in city by proximity checks all hotels in the in ascending order small file
     */
    @Test
    public void getHotelsInCityByProximityTestDistanceSmall() {

        // gets the hotel data from the function
        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY, correctLatitude,
                correctLongitude);

        boolean isCorrect = isDistancesSorted();

        assertTrue("checks if hotels are sorted by proximity",isCorrect);
    }


    /**
     * checks hotels in city if distance is equal than measure by distance then by points of interest
     * small file
     */
    @Test
    public void getHotelsInCityByProximityTestPOISmall() {

        // gets the hotel data from the function
        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY, correctLatitude,
                correctLongitude);

        boolean isCorrect = isPointsOfInterestSortedEqualDistance();

        assertTrue("checks if hotels are sorted by proximity",isCorrect);
    }

    /**
     * get hotels in city by proximity checks all hotels in chosen city big file
     */
    @Test
    public void getHotelsInCityByProximityTestCitesBig() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY, correctLatitude,
                correctLongitude);

        boolean isCorrect = true;

        // goes through all our hotels
        for(Hotel hotel:this.hotels){
            // checks that the city is the one we want
            if (hotel.getCity().compareTo(REAL_CITY) != 0){
                isCorrect = false;
                break;
            }
        }

        assertTrue("checks if the function really adds hotels from the right city", isCorrect);
    }

    /**
     * get hotels in city by proximity checks all hotels in the in ascending order big file
     */
    @Test
    public void getHotelsInCityByProximityTestDistanceBig() {

        // gets the hotel data from the function
        this.hotels = this.bigBoopingSite.getHotelsInCityByProximity(REAL_CITY, correctLatitude,
                correctLongitude);

        boolean isCorrect = isDistancesSorted();

        assertTrue("checks if hotels are sorted by proximity",isCorrect);
    }


    /**
     * checks hotels in city if distance is equal than measure by distance then by points of interest
     */
    @Test
    public void getHotelsInCityByProximityTestPOIBig() {

        // gets the hotel data from the function
        this.hotels = this.bigBoopingSite.getHotelsInCityByProximity(REAL_CITY, correctLatitude,
                correctLongitude);

        boolean isCorrect = isPointsOfInterestSortedEqualDistance();

        assertTrue("checks if hotels are sorted by proximity",isCorrect);
    }

    /**
     * illegal city
     */
    @Test
    public void getHotelsInCityByProximityIllegalCity() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(FAKE_CITY, correctLatitude,
                correctLongitude);

        assertEquals("fake city", 0, this.hotels.length);
    }

    /**
     * fake latitude
     */
    @Test
    public void getHotelsInCityByProximityIllegalLatitude() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY, illegalLatitude,
                correctLongitude);

        assertEquals("fake latitude", 0, this.hotels.length);
    }

    /**
     * fake longitude
     */
    @Test
    public void getHotelsInCityByProximityIllegalLongitude() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY,
                correctLatitude, illegalLongitude);

        assertEquals("fake longitude", 0, this.hotels.length);
    }

    /**
     * fake longitude and latitude
     */
    @Test
    public void getHotelsInCityByProximityIllegalLongitudeAndLatitude() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(REAL_CITY,
                illegalLatitude, illegalLongitude);

        assertEquals("fake longitude and latitude", 0, this.hotels.length);
    }

    /**
     * fake longitude and fake city
     */
    @Test
    public void getHotelsInCityByProximityIllegalCityAndLongitude() {
        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(FAKE_CITY,
                correctLatitude, illegalLongitude);

        assertEquals("fake longitude and fake city", 0, this.hotels.length);
    }

    /**
     * fake latitude and fake city
     */
    @Test
    public void getHotelsInCityByProximityIllegalCityAndLatitude() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(FAKE_CITY,
                illegalLatitude, correctLongitude);

        assertEquals("fake latitude and fake city", 0, this.hotels.length);
    }

    /**
     * fake longitude, latitude and fake city
     */
    @Test
    public void getHotelsInCityByProximityIllegalCityLatitudeAndLongitude() {

        this.hotels = this.smallBoopingSite.getHotelsInCityByProximity(FAKE_CITY,
                illegalLatitude, illegalLongitude);

        assertEquals("everything is fake", 0, this.hotels.length);
    }

    /**
     * cleanup after every test
     */
    @After
    public void done(){
        this.hotels = null;
    }

    /**
     * a function to help us calculate the distance between a hotel and a coordinate
     * @param hotel our hotel
     * @param latitude the latitude
     * @param longitude the longitude
     * @return the distance
     */
    private double getDistance(Hotel hotel, double latitude, double longitude){
        double latitudeDistance = latitude - hotel.getLatitude();
        double longitudeDistance = longitude - hotel.getLongitude();
        return Math.sqrt((latitudeDistance * latitudeDistance) + (longitudeDistance * longitudeDistance));
    }

    /**
     * checks if the distances are in ascending order
     * @return if the distances are in ascending order
     */
    private boolean isDistancesSorted() {

        // goes through all our hotels
        for (int i = 0; i < this.hotels.length - 1; i++) {

            // checks that the distances are in ascending order
            if (getDistance(this.hotels[i], correctLatitude, correctLongitude) > getDistance(this.hotels[i + 1],
                    correctLatitude, correctLongitude)) {

                return false;
            }
        }
        return true;
    }

    /**
     * checks if the points of interest are sorted if the distance is equal
     * @return if its sorted by the POI when distances are eqal
     */
    private boolean isPointsOfInterestSortedEqualDistance() {

        // goes through all the hotels
        for (int i = 0; i < this.hotels.length - 1; i++) {

            // checks if the distances are equal
            if (getDistance(this.hotels[i], correctLatitude,
                    correctLongitude) == getDistance(this.hotels[i + 1],
                    correctLatitude, correctLongitude)) {

                // if the points are interset are not in the correct order
                if (this.hotels[i].getNumPOI() < this.hotels[i].getNumPOI()) {
                    return false;
                }
            }
        }

        return true;
    }
}