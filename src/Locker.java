import oop.ex3.spaceship.Item;
import java.util.Map;

/**
 * a class that will represent lockers
 */
public class Locker implements Storage {

    protected static LongTermStorage longTermStorage = new LongTermStorage();
    private StorageHelper storageHelper;
    private final int FAIL = -1;
    private final int SUCCESS = 0;
    private final int WORKED_WITH_PROBLEM = 1;
    private final int ITEM_TYPE_COLLISION = -2;

    /**
     * constructor for the locker
     * @param capacity how big our locker can be
     */
    public Locker(int capacity){
        this.storageHelper = new StorageHelper(capacity);
    }

    /**
     *  adds an item to the locker
     * @param item the item we wanna add
     * @param n the amount of that item we wanna add
     * @return did we successfully add that item
     */
    @Override
    public int addItem(Item item, int n) {

        // checks if we can put that
        if(itemTypeCollision(item))
        {
           System.out.println("Error: Your request cannot be " +
                   "completed at this time. Problem: the locker cannot contain " +
                   "items of type" + item.getType() + ", as it contains a contradicting" +
                   "item");
           return ITEM_TYPE_COLLISION;
        }

        // tries adding the item to our storage locker
        if (this.storageHelper.addItem(item, n) == SUCCESS)
        {
            // checks if the new capacity to much to hold
            if (((this.storageHelper.getItemCount(item.getType()) * item.getVolume())
                    > (this.storageHelper.getCapacity() / 2)))
            {
                // checks how much needs to be removed
                int removeAmount = this.removeAmount(item);

                // checks if we can add it to the long term storage
                if(longTermStorage.addItem(item, removeAmount) == SUCCESS)
                {
                    this.removeItem(item, removeAmount);

                    System.out.println("Warning: Action successful, but has caused items to be moved " +
                            "to storage");

                    return WORKED_WITH_PROBLEM;
                }
                else{
                    this.removeItem(item, n);
                    return FAIL;
                }

            }

            return SUCCESS;
        }

        System.out.println("Error: Your request cannot be completed at this time. " +
                "Problem: no room for " + n + " items of type " + item.getType());
        return FAIL;
    }

    /**
     * did we remove that item
     * @param item the item we wanted to remove
     * @param n the amount of that item we wanted to remove
     * @return was the remove successful
     */
    public int removeItem(Item item, int n){

        // checks if n is a negative number
        if (n < 0) {

            System.out.println("Error: Your request cannot be completed at this time. Problem: cannot "  +
                    "remove a negative number of items of type " + item.getType());

            return FAIL;
        }

        // tries removing the items
        if(this.storageHelper.removeItem(item, n) != 0){

            System.out.println("Error: Your request cannot be completed at " +
                    "this time. Problem: the locker does not contain " + n + " items of type " +
                    item.getType());

            return FAIL;
        }

        return SUCCESS;
    }

    /**
     * gets the item count
     * @param type what type of item we want
     * @return what the item count is
     */
    @Override
    public int getItemCount(String type) {
        return this.storageHelper.getItemCount(type);
    }

    /**
     *  gets the inventory map
     * @return the map with the inventory
     */
    @Override
    public Map<String, Integer> getInventory() {
        return this.storageHelper.getInventory();
    }

    /**
     *  gets the capacity of the locker
     * @return the locker capacity
     */
    @Override
    public int getCapacity() {
        return this.storageHelper.getCapacity();
    }

    /**
     *  gets the available capacity
     * @return the available capacity
     */
    @Override
    public int getAvailableCapacity() {
        return this.storageHelper.getAvailableCapacity();
    }

    /**
     * gets the amount to remove to get less than 20% of the locker
     * @param item the type of item we want to remove
     * @return the amount to remove
     */
    public int removeAmount(Item item){

        int currentAmount = this.storageHelper.getItemCount(item.getType());
        int volume = item.getVolume();
        double dedicatedAmount = this.storageHelper.getCapacity() / 5;

        // keeps checking how many of those items needed to be removed and put into long term
        while(volume * currentAmount > dedicatedAmount)
        {
            currentAmount --;
        }

        // returns the amount that needs to be removed
        return (this.storageHelper.getItemCount(item.getType()) - currentAmount);
    }

    private boolean itemTypeCollision(Item item)
    {
        String itemType = item.getType();

        // checks if item is baseball bat
        if(itemType.equals("baseball bat")) {

            return this.storageHelper.getInventory().containsKey("football");

        }

        // checks if item is football
        if(itemType.equals("football")) {
            return this.storageHelper.getInventory().containsKey("baseball bat");
        }

        return false;
    }

}
