import oop.ex3.spaceship.Item;
import java.util.Map;

/**
 * long term storage class
 */
public class LongTermStorage implements Storage {

    private final int LTS_SIZE = 1000;
    private final int FAIL = -1;
    private final int SUCCESS = 0;
    private StorageHelper storageHelper;

    public LongTermStorage()
    {
        storageHelper = new StorageHelper(LTS_SIZE);
    }

    /**
     *  resets the all the inventory by sending all the items into space
     */
    public void resetInventory(){
        this.storageHelper.resetInventory();
    }

    /**
     * adds an item to the long term storage
     * @param item the item we wanna add
     * @param n the amount we wanna add
     * @return could we add it
     */
    @Override
    public int addItem(Item item, int n) {

        if (this.storageHelper.addItem(item, n) != 0)
        {
            System.out.println("Error: Your request cannot be completed at this " +
                    "time. Problem: no room for " + n + " Items of type " + item.getType());
            return FAIL;
        }

        return SUCCESS;
    }

    /**
     *  gets the item count
     * @param type the type of item we want the count from
     * @return the amount that item has
     */
    @Override
    public int getItemCount(String type) {
        return this.storageHelper.getItemCount(type);
    }

    /**
     * gets our inventory
     * @return a map with the inventory
     */
    @Override
    public Map<String, Integer> getInventory() {
        return this.storageHelper.getInventory();
    }

    /**
     * gets the capacity of the long term storage
     * @return the capacity of the storage
     */
    @Override
    public int getCapacity() {
        return this.storageHelper.getCapacity();
    }

    /**
     * get the available capacity
     * @return the available capacity
     */
    @Override
    public int getAvailableCapacity() {
        return this.storageHelper.getAvailableCapacity();
    }
}
