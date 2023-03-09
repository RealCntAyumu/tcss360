import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author Benji Lee
 * @author Abdirizak Ali
 *
 */


/***
 * A class representing a subproject in the app.
 * A subproject has a name, a list of documents, and a list of items.
 */
public class Subproject {
    private String name;
    private ArrayList<File> documents; // list of documents saved in the subproject
    private List<Item> items; // list of items in the subproject
    
    /**
     * Constructor for the Subproject class.
     * @param name the name of the subproject
     */
    public Subproject(String name) {
        this.name = name;
        items = new ArrayList<>();
        documents = new ArrayList<>();
    }
    
    /**
     * Get the name of the subproject.
     * @return the name of the subproject
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the subproject.
     * @param name the new name for the subproject
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the list of documents in the subproject.
     * @return the list of documents in the subproject
     */
    public ArrayList<File> getDocuments() {
        return documents;
    }
    
    /**
     * Set the list of documents in the subproject.
     * @param documents the new list of documents for the subproject
     */
    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
    }

    /**
     * Get the list of items in the subproject.
     * @return the list of items in the subproject
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Add an item to the list of items in the subproject.
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Remove an item from the list of items in the subproject.
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
}

/***
 * A class representing an item in the app.
 * An item has a name, a description, and a completion status.
 */
class Item {
    private String name;
    private String description;
    private boolean completed;

    /**
     * Constructor for the Item class.
     * @param name the name of the item
     * @param description the description of the item
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        completed = false;
    }

    /**
     * Get the name of the item.
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the item.
     * @param name the new name for the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of the item.
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the item.
     * @param description the new description for the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Check if the item is completed.
     * @return true if the item is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
