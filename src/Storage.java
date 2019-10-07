import oop . ex3 . spaceship .*;
import java.util.Map;

/**
 * creating an interface to make the storage lockers work with
 */
public interface Storage {

    /**
     * adds an item to the storage
     * @param item the item to add to storage
     * @param n the amount of that item added to storage
     * @return if the action succeeded
     */
    // adds a item to the storage
    int addItem(Item item, int n);

    /**
     *
     * @param type gets the item count of a certain type
     * @return the type
     */
    // get item count
    int getItemCount(String type);

    /**
     * gets the inventory
     * @return the inventory of the locker
     */
    // counts the amount of a certain item
    Map<String, Integer> getInventory();

    /**
     *  gets the storage units capacity
     * @return the capacity
     */
    // gets the capacity
    int getCapacity();

    /**
     * gets tha available capacity
     * @return the available capacity
     */
    //  gets the available capacity
    int getAvailableCapacity();
}
