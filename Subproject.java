import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JProgressBar;

/***
 * 
 * @author Benji Lee
 * @author Abdirizak Ali
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
    
    public void setDocuments(ArrayList<File> documents) {
		this.documents = documents;
	}

    public List<Item> getItems() {
		return items;
	}
    
    public void setItems(ArrayList<Item> inputItems) {
        items = inputItems;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void sortItems() {
        Collections.sort(items);
    }
    
	public ArrayList<File> getDocuments() {
		return documents;
	}
    

	public void setName(String name) {
		this.name = name;
	}

    public void setBudget(JProgressBar inputBudget) {
        budget = inputBudget;
    }

    public JProgressBar getBudget() {
        return budget;
    }

    public Subproject(String name) {
        this.name = name;
        items = new ArrayList<>();
        documents = new ArrayList<>();
       // this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

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
