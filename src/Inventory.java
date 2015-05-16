/**
 * @author Justin Chang
 */

import java.util.*;

public class Inventory {

    private HashMap<Item, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> retArr = new ArrayList<Item>();
        for(Item i : inventory.keySet())
            retArr.add(i);
        return retArr;
    }

    public int howManyOf(Item item) {
        if(hasItem(item)) return inventory.get(item);
        else return -1;
    }

    public boolean hasItem(Item item) {
        return inventory.containsKey(item);
    }

    public void addItem(Item item) {
        if(inventory.containsKey(item)) inventory.put(item, inventory.get(item) + 1);
        else inventory.put(item, 1);
    }

    public void addItem(Item item, int count) {
        if(inventory.containsKey(item)) inventory.put(item, inventory.get(item) + count);
        else inventory.put(item, count);
    }

    public void removeItem(Item item) {
        if(inventory.get(item) > 1) inventory.put(item, inventory.get(item) - 1);
        else inventory.remove(item);
    }

    public void removeItem(Item item, int count) {
        if(inventory.get(item) > count + 1) inventory.put(item, inventory.get(item) - count);
        else inventory.remove(item);
    }

}
