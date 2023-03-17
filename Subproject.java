import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JProgressBar;

/***
 * 
 * @author Benji Lee
 * @author Abdirizak Ali
 * @author Kevin Hua
 *
 */
public class Subproject {
    private String name;
    // list to save documents saved.
    private ArrayList<File> documents;
    // list to save items. 
    private ArrayList<Item> items;
    //Budget Bar Information
    private JProgressBar budget;
    
    /**
     * Sets the current documents as the documents passed through
     * @param documents
     */
    public void setDocuments(ArrayList<File> documents) {
		this.documents = documents;
	}

    /**
     * Returns the current list of items
     * @return items
     */
    public List<Item> getItems() {
		return items;
	}
    
    /**
     * Sets the current list of items as the list of items inputItems
     * @param inputItems
     */
    public void setItems(ArrayList<Item> inputItems) {
        items = inputItems;
    }

    /**
     * Adds the inputed item into the list
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes the inputted item from the list
     * @param item
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Sorts the items in order of cost of item in descending order
     */
    public void sortItems() {
        Collections.sort(items);
    }
    
    /**
     * Returns the current list of files
     * @return documents
     */
	public ArrayList<File> getDocuments() {
		return documents;
	}
    

    /**
     * Sets the name of the current subproject
     * @param name
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Sets the current budget bar of the project to inputBudget
     * @param inputBudget
     */
    public void setBudget(JProgressBar inputBudget) {
        budget = inputBudget;
    }

    /**
     * Returns the current budget bar of the subproject
     * @return budget
     */
    public JProgressBar getBudget() {
        return budget;
    }

    /**
     * Creates a new subproject with the given name and initializes the lists
     * @param name
     */
    public Subproject(String name) {
        this.name = name;
        items = new ArrayList<>();
        documents = new ArrayList<>();
       // this.tasks = new ArrayList<>();
    }

    /**
     * Gets the name of the subproject
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Add the File item into the documents list
     * @param item
     */
    public void addDoc(File item) {
    	documents.add(item);
    }


    

   // public List<Task> getTasks() {
       // return tasks;
    //}

   // public void addTask(Task task) {
    //    tasks.add(task);
    //}
}
