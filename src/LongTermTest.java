import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class LongTermTest {

    private LongTermStorage longTermStorage;
    private HashMap<String, Integer> map;

    private final int SUCCESS_INDICATOR = 0;
    private final int FAIL_INDICATOR = -1;
    private final int LTS_SIZE = 1000;

    // creates the items for use
    private Item baseBallBat = ItemFactory.createSingleItem("baseball bat");
    private Item sporesEngine = ItemFactory.createSingleItem("spores engine");
    private Item helmetSizeOne = ItemFactory.createSingleItem("helmet, size 1");;
    private Item helmetSizeThree = ItemFactory.createSingleItem("helmet, size 3");
    private Item football = ItemFactory.createSingleItem("football");


    /**
     * things to do before every test
     */
    @Before
    public void setup()
    {
        this.longTermStorage = new LongTermStorage();
        this.map = new HashMap<String, Integer>();
    }

    /**
     * tries adding an Single item to an empty LTS
     */
    @Test
    public void addItemSingleItemEmptyLTS() {

        assertEquals("single item added to Empty LTS", SUCCESS_INDICATOR,
                this.longTermStorage.addItem(this.football, 1));
    }

    /**
     * tries adding multiple items to an empty LTS
     */
    @Test
    public void addItemMultipleItemEmptyLTS() {

        assertEquals("multiple items added to empty LTS", SUCCESS_INDICATOR,
                this.longTermStorage.addItem(this.football, 3));
    }

    /**
     * tries adding an item to not empty LTS
     */
    @Test
    public void addItemsToNonEmptyLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 3);

        assertEquals("single item added to non empty LTS", SUCCESS_INDICATOR,
                this.longTermStorage.addItem(this.football, 1));
    }

    /**
     * tries adding the last item to the LTS
     */
    @Test
    public void addsItemLastItem() {
        this.longTermStorage.addItem(this.sporesEngine, 99);

        assertEquals("added last item to LTS", SUCCESS_INDICATOR,
                this.longTermStorage.addItem(this.sporesEngine, 1));

    }

    /**
     * tries adding an item to overfill LTS
     */
    @Test
    public void addItemSingleItemToOverfillLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 99);
        this.longTermStorage.addItem(this.football, 1);

        assertEquals("single item added to try overfill LTS", FAIL_INDICATOR,
                this.longTermStorage.addItem(this.sporesEngine, 1));
    }

    /**
     * tries adding an multiple items to overfill LTS
     */
    @Test
    public void addItemMultipleItemsToOverfillLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 99);

        assertEquals("multiple item added to try overfill LTS", FAIL_INDICATOR,
                this.longTermStorage.addItem(this.football, 10));

    }

    /**
     * tries adding an item to a full LTS
     */
    @Test
    public void addItemToFullLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 100);

        assertEquals("try adding item to try full LTS", FAIL_INDICATOR,
                this.longTermStorage.addItem(this.football, 1));

    }

    /**
     * tries adding an to much of an item to a LTS
     */
    @Test
    public void addItemToMuchToLTS() {
        assertEquals("try adding item to try fill to much to LTS", FAIL_INDICATOR,
                this.longTermStorage.addItem(this.sporesEngine, 150));

    }

    /**
     * tries adding adding 0 of item type to LTS
     */
    @Test
    public void addItemAddZero() {
        assertEquals("try adding item to try add 0 of type to LTS", SUCCESS_INDICATOR,
                this.longTermStorage.addItem(this.sporesEngine, 0));

    }

    /**
     * tries adding adding negative of item type to LTS
     */
    @Test
    public void addItemNegativeAmount() {
        assertEquals("try adding item to try add 0 of type to LTS", FAIL_INDICATOR,
                this.longTermStorage.addItem(this.sporesEngine, -1));

    }

    /**
     * gets the item count LTS with no value
     */
    @Test
    public void getItemCountEmptyLTS() {

        assertEquals("item count on empty LTS", 0,
                this.longTermStorage.getItemCount(this.baseBallBat.getType()));
    }

    /**
     * gets the item count LTS with single value
     */
    @Test
    public void getItemCountSingleValueLTS(){

        this.longTermStorage.addItem(this.sporesEngine, 1);

        assertEquals("item count on single value LTS", 1,
                this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * gets the item count LTS with multiple values
     */
    @Test
    public void getItemCountWithMultipleValues() {

        this.longTermStorage.addItem(this.sporesEngine, 10);

        assertEquals("item count on single value LTS", 10,
                this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * gets the item count LTS with many items in the LTS
     */
    @Test
    public void getItemCountWithMultipleItemsInLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 1);
        this.longTermStorage.addItem(this.baseBallBat, 5);
        longTermStorage.addItem(this.helmetSizeOne, 7);

        assertEquals("item count on single value LTS", 1,
                this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }


    /**
     * gets the item count LTS with the same item value added twice
     */
    @Test
    public void getItemCountWithSameItemAddedTwice() {

        this.longTermStorage.addItem(this.sporesEngine, 1);
        this.longTermStorage.addItem(this.baseBallBat, 5);
        longTermStorage.addItem(this.helmetSizeOne, 7);

        assertEquals("item count on single value LTS", 1,
                this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * gets the item count LTS with the illegal amount added to LTS
     */
    @Test
    public void getItemCountAfterAddingIlleagalAmount() {

        this.longTermStorage.addItem(this.sporesEngine, -1);

        assertEquals("item count on single value LTS when illegal amount added", 0,
                this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * gets the item count LTS with the illegal amount added to LTS after legal amount added
     */
    @Test
    public void getItemCountAddingLegalThenIllegalAmount() {

        this.longTermStorage.addItem(this.sporesEngine, 5);
        this.longTermStorage.addItem(this.sporesEngine, -1);

        assertEquals("item count on single value LTS when illegal amount added after legal amount",
                5, this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }

    /**
     * gets the item count LTS with the illegal amount added to LTS before legal amount added
     */
    @Test
    public void getItemCountIllegalAmountThenLegalAmount() {

        this.longTermStorage.addItem(this.sporesEngine, -1);
        this.longTermStorage.addItem(this.sporesEngine, 5);

        assertEquals("item count on single value LTS when illegal amount added before legal amount",
                5, this.longTermStorage.getItemCount(this.sporesEngine.getType()));
    }

    /**
     *  gets the inventory count empty LTS
     */
    @Test
    public void getInventoryEmptyLTS() {

        assertEquals("getInventory in empty LTS", this.map,
                this.longTermStorage.getInventory());
    }

    /**
     *  gets the inventory count LTS with items of one type
     */
    @Test
    public void getInventoryWithSingleItem() {

        this.longTermStorage.addItem(this.sporesEngine, 1);
        this.map.put(this.sporesEngine.getType(), 1);

        assertEquals("getInventory with single item", this.map,
                this.longTermStorage.getInventory());
    }

    /**
     *  gets the inventory count LTS with items of multiple type
     */
    @Test
    public void getInventoryWithMultipleTypes() {

        this.longTermStorage.addItem(this.sporesEngine, 1);
        this.longTermStorage.addItem(this.helmetSizeThree, 4);
        this.map.put(this.sporesEngine.getType(), 1);
        this.map.put(this.helmetSizeThree.getType(), 4);

        assertEquals("getInventory in LTS with multiple tests", this.map,
                this.longTermStorage.getInventory());
    }

    /**
     *  gets the inventory count LTS with full LTS
     */
    @Test
    public void getInventoryFullLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 90);
        this.longTermStorage.addItem(this.helmetSizeThree, 20);
        this.map.put(this.sporesEngine.getType(), 90);
        this.map.put(this.helmetSizeThree.getType(), 20);

        assertEquals("getInventory in full LTS", this.map,
                this.longTermStorage.getInventory());
    }

    /**
     *  gets the inventory count LTS after resetting the LTS
     */
    @Test
    public void getInventoryAfterReset() {

        this.longTermStorage.addItem(this.helmetSizeThree, 20);
        this.longTermStorage.resetInventory();

        assertEquals("getInventory in Reseted LTS", this.map,
                this.longTermStorage.getInventory());
    }

    /**
     * gets the capacity of the LTS
     */
    @Test
    public void getCapacityEmptyLTS() {

        assertEquals("gets LTS capacity", LTS_SIZE,
                this.longTermStorage.getCapacity());
    }

    /**
     * gets the capacity of the LTS after adding an item
     */
    @Test
    public void getCapacityItemInLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 1);

        assertEquals("gets LTS capacity after adding an item", LTS_SIZE,
                this.longTermStorage.getCapacity());
    }

    /**
     * gets the capacity of the LTS when LTS is full
     */
    @Test
    public void getCapacityFullLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 100);

        assertEquals("gets LTS capacity when LTS is full", LTS_SIZE,
                this.longTermStorage.getCapacity());
    }

    /**
     * get capacity of empty LTS
     */
    @Test
    public void getAvailableCapacityEmptyLTS() {

        assertEquals("gets LTS available capacity with empty LTS", LTS_SIZE,
                this.longTermStorage.getCapacity());
    }

    /**
     * get capacity of full LTS
     */
    @Test
    public void getAvailableCapacityFullLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 100);

        assertEquals("gets LTS available capacity with full LTS", 0,
                this.longTermStorage.getAvailableCapacity());
    }


    /**
     * get capacity of LTS with many types
     */
    @Test
    public void getAvailableCapacityFilledWithDifferentTypes() {

        this.longTermStorage.addItem(this.sporesEngine, 50);
        this.longTermStorage.addItem(this.helmetSizeOne, 2);
        this.longTermStorage.addItem(this.baseBallBat, 4);

        assertEquals("gets LTS available capacity with half full LTS ", 486,
                this.longTermStorage.getAvailableCapacity());
    }

    /**
     * get capacity of LTS with adding a bad amount of an item
     */
    @Test
    public void getAvailableCapacityAddingNegativeAmount() {

        this.longTermStorage.addItem(this.sporesEngine, -1);

        assertEquals("gets LTS available capacity after attempting to add bad item", LTS_SIZE,
                this.longTermStorage.getAvailableCapacity());
    }

    /**
     * resets inventory after empty LTS
     */
    @Test
    public void resetInventoryEmptyLTS() {

        this.longTermStorage.resetInventory();

        assertEquals("resets LTS available capacity empty LTS", LTS_SIZE,
                this.longTermStorage.getAvailableCapacity());
    }

    /**
     * resets inventory after full LTS
     */
    @Test
    public void resetInventoryAfterFullLTS() {

        this.longTermStorage.addItem(this.sporesEngine, 100);
        this.longTermStorage.resetInventory();

        assertEquals("resets LTS available capacity with full LTS", LTS_SIZE,
                this.longTermStorage.getAvailableCapacity());
    }

    /**
     * resets inventory after LTS with some items in it
     */
    @Test
    public void resetInventoryAfterHalfFullWithItems() {

        this.longTermStorage.addItem(this.sporesEngine, 50);
        this.longTermStorage.resetInventory();

        assertEquals("resets LTS available capacity with half full LTS", LTS_SIZE,
                this.longTermStorage.getAvailableCapacity());
    }

    /**
     * things to do after every test
     */
    @After
    public void cleanUp(){
        this.longTermStorage = null;
        this.map = null;
    }
}