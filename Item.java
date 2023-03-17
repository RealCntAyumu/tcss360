/**
 * 
 * @author Ayumu Oshiro
 * @author Abdirizak Ali
 * @author Kevin Hua
 * 
 */
public class Item implements Comparable<Item>{
	private String name;
    private int cost;

	/**
	 * Creates an Item that initalizes name and cost with the inputted ones
	 * @param name
	 * @param cost
	 */
    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

	/**
	 * Returns the name of the item
	 * @return name
	 */
    public String getName() {
        return name;
    }
	
	/**
	 * Gets the cost of the item
	 * @return cost
	 */
    public int getCost() {
        return cost;
    }

	 /**
	  * Method to compare two items, returns the items in descending order.
	  */
	@Override
	public int compareTo(Item o) {
		return o.cost - this.cost;
	}

}
