import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * a class used so the storage types can use its function
 */
public class StorageHelper implements Storage {

    private int capacity;
    private HashMap<String, Integer> itemsAndAmounts;

    /**
     * constructor for every item of the type of Storage Helper
     * @param capacity the capacity the storage object
     */
    public StorageHelper(int capacity)
    {
        this.capacity = capacity;
        this.itemsAndAmounts = new HashMap<String, Integer>();
    }

    /**
     * adds the item to the list
     * @param item the type of item we want to add
     * @param n the amount of items we want
     * @return if we added the item successfully 0 , if the item was illegal 1, if not enough capacity 2
     */
    @Override
    public int addItem(Item item, int n) {

        // checks if n equals zero and if so wont do a thing to the map
        if(n == 0)
        {
            return 0;
        }

        // checks if n is negative
        if(n < 0)
        {
            return 1;
        }

        // checks if the item is illegal one means illegal item
        if (!this.isLegalItem(item))
        {
            return 2;
        }

        // checks if there is enough room for all the items
        if (getAvailableCapacity() < (n * item.getVolume())){
            return 3;
        }

        // checks if the item is already in our map
        if(this.itemsAndAmounts.containsKey(item.getType()))
        {
            // it passed the test and puts it in the locker
            this.itemsAndAmounts.put(item.getType(), this.itemsAndAmounts.get(item.getType()) + n);
        }
        else{
            this.itemsAndAmounts.put(item.getType(), n);

        }

        // it passed the test and puts it in the locker
        return 0;
    }

    /**
     * counts the amount of items in the map
     * @param type the of object we want
     * @return the amount of that objects appear in our storage
     */
    @Override
    public int getItemCount(String type) {

        Integer count = this.itemsAndAmounts.get(type);

        // makes sure that the integer is not null and if so returns 0
        if(count == null){
            return 0;
        }

        // returns the count
        return count;
    }

    /**
     *  gets the available capacity
     * @return the available capacity
     */
    @Override
    public int getAvailableCapacity() {

        int total = 0;

        // goes through all the items and adds its amounts to the total amount
        for(Map.Entry<String, Integer> set: this.itemsAndAmounts.entrySet()){
            Item currentItem = ItemFactory.createSingleItem(set.getKey());
            total += (currentItem.getVolume() * set.getValue());

        }

        // returns the amount left
        return (this.getCapacity() - total);
    }

    /**
     * gets the capacity of the ship
     * @return the ships capacity
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * gets the inventory amounts of all items
     * @return map with all the inventory
     */
    @Override
    public Map<String, Integer> getInventory() {
        return new HashMap<String, Integer>(this.itemsAndAmounts);
    }

    /**
     * check if an item is in the legal list
     * @param currentItem our current item
     * @return if it is in our legal list
     */
    private boolean isLegalItem(Item currentItem)
    {
        // goes through all the legal items
        for (Item item: ItemFactory.createAllLegalItems())
        {
            // if that item appears in the array its legal
            if (item.getType().equals(currentItem.getType())){
                return true;
            }
        }
        // that item is not in the legal list
        return false;
    }

    /**
     * a function to reset our inventory
     */
    public void resetInventory()
    {
        this.itemsAndAmounts.clear();
    }

    /**
     *  removes an item from the list
     * @param item the type of item to remove
     * @param n the amount to remove
     * @return 0 if the action was Successful or indicator why it failed
     */
    public int removeItem(Item item, int n){

        // removing zero of an item nothing happens to its ok
        if(n == 0)
        {
            return 0;
        }

        // checks if item is valid
        if(!this.isLegalItem(item))
        {
            return 1;
        }

        // checks if that item is in our hash map
        if(!this.itemsAndAmounts.containsKey(item.getType())) {
            return 2;
        }

        int itemAmount = this.itemsAndAmounts.get(item.getType());

        // not enough to remove from the
        if(itemAmount < n)
        {
            return 3;
        }

        // calculates the new amount needed for the value
        int newAmount = (this.itemsAndAmounts.get(item.getType()) - n);

        // if it goes down to 0 just remove the key
        if(newAmount == 0){
            this.itemsAndAmounts.remove(item.getType());
        }
        else
        {
            // remove the desired amount
            itemsAndAmounts.put(item.getType(), newAmount);
        }

        return 0;
    }

}
