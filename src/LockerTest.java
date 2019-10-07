import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;
import static org . junit . Assert .*;
import java.util.HashMap;

public class LockerTest {

    private final int TEST_LOCKER_SIZE = 100;
    private final int SUCCESS_INDICATOR = 0;
    private final int FAIL_INDICATOR = -1;
    private final int LTS_MOVE = 1;
    private final int COLISION_INIDCATOR = -2;

    private Locker lockerOne;
    private HashMap<String, Integer> map;

    // creates items to work with
    private Item baseBallBat = ItemFactory.createSingleItem("baseball bat");
    private Item sporesEngine = ItemFactory.createSingleItem("spores engine");
    private Item helmetSizeOne = ItemFactory.createSingleItem("helmet, size 1");;
    private Item helmetSizeThree = ItemFactory.createSingleItem("helmet, size 3");
    private Item football = ItemFactory.createSingleItem("football");
    private Item fakeItem = ItemFactory.createSingleItem("fake");

    /**
     * set up for every single test
     */
    @Before
    public void SetUp(){
        map = new HashMap<String, Integer>();
        lockerOne = new Locker(TEST_LOCKER_SIZE);
        Locker.longTermStorage.resetInventory();
    }

    /**
     * adds a legal single item to an empty lockerOne
     */
    @Test
    public void addItemSingleItem() {

        // tries adding it to the lockerOne
        assertEquals("adding item to empty lockerOne", SUCCESS_INDICATOR,
                lockerOne.addItem(this.sporesEngine, 1));
    }

    /**
     * adds a legal item to a to a lockerOne with a value in it already
     */
    @Test
    public void addItemNonEmptyLocker() {

        lockerOne.addItem(this.sporesEngine, 5);

        // tries adding the next item to the lockerOne
        assertEquals("adding item after lockerOne is not empty", SUCCESS_INDICATOR,
                lockerOne.addItem(this.football, 1));
    }

    /**
     * adds legal item to fill the max capacity of the lockerOne
     */
    @Test
    public void addItemToFillLocker() {

        lockerOne.addItem(this.sporesEngine, 4);
        lockerOne.addItem(this.helmetSizeThree, 10);

        // tries adding the next item to the lockerOne
        assertEquals("filling the lockerOne till the end", SUCCESS_INDICATOR,
                lockerOne.addItem(this.sporesEngine, 1));
    }

    /**
     * adds an illegal item to the lockerOne
     */
    @Test(expected = NullPointerException.class)
    public void addItemfakeItemAddition(){

        // tries adding it to the lockerOne
        assertEquals("trying to add null item", FAIL_INDICATOR ,
                lockerOne.addItem(this.fakeItem, 1));
    }

    /**
     * adds a negative amount of a type to the lockerOne
     */
    @Test
    public void addItemNegativeAmount(){

        // tries adding it to the lockerOne
        assertEquals("trying to add negative amount to lockerOne", FAIL_INDICATOR
                , lockerOne.addItem(this.sporesEngine, -1));
    }

    /**
     * adds a 0 amount of a type to the lockerOne
     */
    @Test
    public void addItemZeroAmount(){
        // tries adding it to the lockerOne
        assertEquals("trying to add negative amount to lockerOne", SUCCESS_INDICATOR
                , lockerOne.addItem(this.sporesEngine, 0));
    }

    /**
     * adding item to full lockerOne
     */
    @Test
    public void addItemOneItemTOFullLocker(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 5);
        lockerOne.addItem(this.helmetSizeThree, 10);

        // tries adding it to the lockerOne
        assertEquals("trying to add item to full lockerOne", FAIL_INDICATOR
                , lockerOne.addItem(this.helmetSizeOne, 1));
    }

    /**
     * adding an one item to over fill the lockerOne
     */
    @Test
    public void addItemSingleItemToOverflowLocker(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 5);
        lockerOne.addItem(this.helmetSizeThree, 9);
        lockerOne.addItem(this.helmetSizeOne, 1);

        // tries adding it to the lockerOne
        assertEquals("trying to add single item to overfill the lockerOne", FAIL_INDICATOR
                , lockerOne.addItem(this.helmetSizeOne, 1));
    }

    /**
     * adding multiplying multiple items to over fill the lockerOne
     */
    @Test
    public void addItemMultipleItemsToOverFlow(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 5);
        lockerOne.addItem(this.helmetSizeThree, 9);

        // tries adding it to the lockerOne
        assertEquals("trying to add multiple items to overfill the lockerOne", FAIL_INDICATOR
                , lockerOne.addItem(this.helmetSizeOne, 2));

    }

    /**
     * adding item larger than locker size
     */
    @Test
    public void addItemToLockerBiggerThanSize(){

        lockerOne = new Locker(1);

        // trying to add item larger than locker
        assertEquals("trying to add item larger than locker size", FAIL_INDICATOR
                , lockerOne.addItem(this.helmetSizeOne, 1));
    }

    /**
     * causes item to be moved to long term storage by adding one item
     */
    @Test
    public void addItemMoveToLTSSingleItem(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 5);

        // tries adding it to the lockerOne
        assertEquals("getting item to be added to the long term with single item", LTS_MOVE
                , lockerOne.addItem(this.sporesEngine, 1));
    }

    /**
     * causes item to be moved to long term storage with multiple items
     */
    @Test
    public void addItemMoveToLTSMultipleItems(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 5);

        // tries adding it to the lockerOne
        assertEquals("getting items to be added to long term with multiple items ", LTS_MOVE
                , lockerOne.addItem(this.sporesEngine, 3));
    }

    /**
     * filling up storage locker and long-term storage
     */
    @Test
    public void addItemFillLockerAndLTS(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 2);

        for(int i=0; i< 12; i++){
            lockerOne.addItem(this.sporesEngine, 8);
        }

        // tries adding it to the lockerOne
        assertEquals("trying to fill storage locker till the end ", LTS_MOVE
                , lockerOne.addItem(this.sporesEngine, 4));
    }

    /**
     * over filing the long term storage by one item
     */
    @Test
    public void addItemOverfillLTSBySingleItem(){

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 2);

        for(int i=0; i< 12; i++){
            lockerOne.addItem(this.sporesEngine, 8);
        }

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 3);

        // tries adding it to the long term storage
        assertEquals("trying to add a item to over fill the long term storage ", FAIL_INDICATOR
                , lockerOne.addItem(this.sporesEngine, 5));

    }

    /**
     * adds football to locker with baseball bat
     */
    @Test
    public void addItemFootballToLockerWithBaseball(){

        // fills the lockerOne
        lockerOne.addItem(this.football, 1);

        // tries adding it to the long term storage
        assertEquals("trying to add a football with baseball bat in locker", COLISION_INIDCATOR
                , lockerOne.addItem(this.baseBallBat, 1));

    }

    /**
     * adds baseball bat to locker with football
     */
    @Test
    public void addItemBaseballTOLockerWithFootball(){

        // fills the lockerOne
        lockerOne.addItem(this.football, 1);

        // tries adding it to the long term storage
        assertEquals("trying to add a baseball bat with football in locker", COLISION_INIDCATOR
                , lockerOne.addItem(this.baseBallBat, 1));

    }

    /**
     * adds baseball bat to locker without football
     */
    @Test
    public void addItemBaseballBatWithoutFootball(){

        // fills the lockerOne
        lockerOne.addItem(this.helmetSizeThree, 1);

        // tries adding it to the long term storage
        assertEquals("trying to add a baseball bat with no football in locker", SUCCESS_INDICATOR
                , lockerOne.addItem(this.baseBallBat, 1));

    }

    /**
     * adds baseball bat to locker without football
     */
    @Test
    public void addItemFootballWithoutBaseballBat(){

        // fills the lockerOne
        lockerOne.addItem(this.helmetSizeThree, 1);

        // tries adding it to the long term storage
        assertEquals("trying to add a football with no baseball bat in locker", SUCCESS_INDICATOR
                , lockerOne.addItem(this.football, 1));

    }

    /**
     * successfully remove an item
     */
    @Test
    public void removeItemSingleItem() {

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 1);

        // removes the single item
        assertEquals("remove a single item", SUCCESS_INDICATOR,
                lockerOne.removeItem(this.sporesEngine, 1));
    }

    /**
     * tries removing from empty locker
     */
    @Test
    public void removeSingleItemFromEmptyLocker() {

        // removes the single item
        assertEquals("remove a single item from empty locker", FAIL_INDICATOR,
                lockerOne.removeItem(this.sporesEngine, 1));
    }

    /**
     * tries removing from multiple items from empty locker
     */
    @Test
    public void removeMultipleItemsFromEmptyLocker() {

        // removes the single item
        assertEquals("remove multiple items from empty locker", FAIL_INDICATOR,
                lockerOne.removeItem(this.sporesEngine, 3));
    }

    /**
     * tries removing one item from locker with many types
     */
    @Test
    public void removeItemWithManyTypes() {

        // fills the lockerOne
        lockerOne.addItem(this.sporesEngine, 3);
        lockerOne.addItem(this.helmetSizeOne, 2);

        // removes the single item
        assertEquals("remove a single item from locker with more than one item", SUCCESS_INDICATOR,
                lockerOne.removeItem(this.sporesEngine, 1));

    }

    /**
     * tries removing to much of a certain item
     */
    @Test
    public void removeItemRemoveToMuch() {

        lockerOne.addItem(this.sporesEngine,1 );

        // removes the single item
        assertEquals("try's removing to much of a certain item", FAIL_INDICATOR,
                lockerOne.removeItem(this.sporesEngine, 4));
    }

    /**
     * tries removing item that does not exists in locker
     */
    @Test
    public void removeItemThatDoesNotExists() {

        lockerOne.addItem(this.sporesEngine,1 );

        // removes the single item
        assertEquals("try's removing item that does not exist in locker", FAIL_INDICATOR,
                lockerOne.removeItem(this.helmetSizeOne, 1));
    }

    /**
     * tries removing from full locker
     */
    @Test
    public void removeItemFromFullLocker() {

        lockerOne.addItem(this.sporesEngine,5 );
        lockerOne.addItem(this.helmetSizeThree,10 );

        // removes the single item from full locker
        assertEquals("try's removing item from full locker", SUCCESS_INDICATOR,
                lockerOne.removeItem(this.helmetSizeThree, 1));
    }

    /**
     * tries removing from 0 of item
     */
    @Test
    public void removeItemZeroOfType() {
        lockerOne.addItem(this.helmetSizeThree,10);

        // removes zero item from locker
        assertEquals("try's removing 0 of item", SUCCESS_INDICATOR,
                lockerOne.removeItem(this.helmetSizeThree, 0));
    }

    /**
     * tries removing from 0 of item that not in locker
     */
    @Test
    public void removeItemZeroOfTypeThatIsNotInLocker() {
        // removes zero item from locker
        assertEquals("try's removing 0 of item", SUCCESS_INDICATOR,
                lockerOne.removeItem(this.helmetSizeThree, 0));
    }

    /**
     * get Item count empty locker
     */
    @Test
    public void getItemCountEmptyLocker() {

        assertEquals("empty locker", 0,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * get Item count with item of one type in locker
     */
    @Test
    public void getItemCountWithSingleItem() {

        lockerOne.addItem(this.sporesEngine,2 );

        assertEquals("get with single type of item", 2,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }


    /**
     * get Item count with item of twos types of items
     */
    @Test
    public void getItemCountWithMultipleItems() {

        lockerOne.addItem(this.sporesEngine,3 );
        lockerOne.addItem(this.helmetSizeThree,1 );

        assertEquals("item count with multiple items in the locker", 3,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * get Item count with full locker
     */
    @Test
    public void getItemCountWithFullLocker() {

        lockerOne.addItem(this.sporesEngine,5 );
        lockerOne.addItem(this.helmetSizeThree,10 );

        assertEquals("item count with full locker", 5,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }


    /**
     * get Item count with adding same item twice
     */
    @Test
    public void getItemCountAfterAddingSameItemTwice() {

        lockerOne.addItem(this.sporesEngine,2 );
        lockerOne.addItem(this.sporesEngine,1 );

        assertEquals("adding same item multiple times", 3,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * get Item count with adding 0 to that key
     */
    @Test
    public void getItemCountAfterAddingZero() {

        lockerOne.addItem(this.sporesEngine,0);

        assertEquals("checks if 0 was added of certain item", 0,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * get Item count with adding negative to that key
     */
    @Test
    public void getItemCountAfterNegativeKey() {

        lockerOne.addItem(this.sporesEngine,-2);

        assertEquals("checks if negative amount was added of certain item", 0,
                lockerOne.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * get the inventory of empty locker
     */
    @Test
    public void getInventoryEmptyLocker() {

        assertEquals("checking empty inventory", this.map, lockerOne.getInventory());
    }

    /**
     * get the inventory with single item
     */
    @Test
    public void getInventoryWithSingleItem() {

        map.put("baseball bat", 1);
        lockerOne.addItem(this.baseBallBat, 1);

        assertEquals("checking single item", this.map, lockerOne.getInventory());
    }


    /**
     * get the inventory tests with half empty locker
     */
    @Test
    public void getInventoryWithHalfEmptyLocker() {

        map.put("baseball bat", 1);
        map.put("helmet, size 3", 5);
        lockerOne.addItem(this.baseBallBat, 1);
        lockerOne.addItem(this.helmetSizeThree, 5);

        assertEquals("checking multiple items", this.map, lockerOne.getInventory());

    }

    /**
     * get the inventory with full locker
     */
    @Test
    public void getInventoryWithFullLocker() {

        map.put("spores engine", 5);
        map.put("helmet, size 3", 10);
        lockerOne.addItem(this.sporesEngine, 5);
        lockerOne.addItem(this.helmetSizeThree, 10);

        assertEquals("checking on full locker", this.map, lockerOne.getInventory());

    }

    /**
     * get the inventory after adding zero to locker
     */
    @Test
    public void getInventoryAddingZeroToLocker() {

        this.lockerOne.addItem(this.helmetSizeThree, 0);

        assertEquals("checking empty inventory", this.map, lockerOne.getInventory());
    }

    /**
     * get the inventory after adding negative amount to locker
     */
    @Test
    public void getInventoryAfterAddingNegativeAmount() {

        this.lockerOne.addItem(this.helmetSizeThree, -2);

        assertEquals("checking empty inventory", this.map, lockerOne.getInventory());
    }

    /**
     * get the capacity of average locker tests
     */
    @Test
    public void getCapacityOfLockerWhenEmpty() {
        assertEquals("gets the capacity of a locker", 100, lockerOne.getCapacity());
    }

    /**
     * get the capacity after starting 0 size locker
     */
    @Test
    public void getCapacityOfLockerWhenLockerSizeIsZero() {

        lockerOne = new Locker(0);

        assertEquals("gets the capacity of 0 size locker", 0, lockerOne.getCapacity());
    }


    /**
     * get the capacity after half full locker
     */
    @Test
    public void getCapacityOfLockerWhenHalfFull() {

        lockerOne.addItem(this.sporesEngine, 5);

        assertEquals("gets the capacity with half full locker", 100,
                lockerOne.getCapacity());
    }


    /**
     * get the capacity after full locker
     */
    @Test
    public void getCapacityWhenLockerIsFull() {

        lockerOne.addItem(this.sporesEngine, 5);
        lockerOne.addItem(this.helmetSizeThree, 10);

        assertEquals("gets the capacity with a full locker", 100, lockerOne.getCapacity());
    }


    /**
     * get the capacity after failed addition
     */
    @Test
    public void getCapacityAfterBadAddition() {

        this.lockerOne.addItem(this.football, -2);

        assertEquals("gets the capacity after failed addition",
                100, lockerOne.getCapacity());
    }

    /**
     * get the available capacity test empty locker
     */
    @Test
    public void getAvailableCapacityEmptyLocker() {
        assertEquals("gets the capacity of empty locker", 100,
                lockerOne.getAvailableCapacity());
    }


    /**
     * get the available capacity test with single item in it
     */
    @Test
    public void getAvailableCapacityWithSingleItemInIt() {

        lockerOne.addItem(this.sporesEngine, 1);

        assertEquals("checking a locker with one type of item in it",
                90, lockerOne.getAvailableCapacity());
    }


    /**
     * get the available capacity test with multiple items in it
     */
    @Test
    public void getAvailableCapacityWithMultipleItemsInIt() {

        lockerOne.addItem(this.sporesEngine, 3);
        lockerOne.addItem(this.helmetSizeThree, 5);

        assertEquals("checking a locker with items in it",
                45, lockerOne.getAvailableCapacity());
    }


    /**
     * get the available capacity test with full locker
     */
    @Test
    public void getAvailableCapacityFullLocker() {

        lockerOne.addItem(this.sporesEngine, 5);
        lockerOne.addItem(this.helmetSizeThree, 10);

        assertEquals("checking on full locker", 0, lockerOne.getAvailableCapacity());
    }

    /**
     * resets things after every test
     */
    @After
    public void cleanUp(){
        lockerOne = null;
        map = null;
    }
}
