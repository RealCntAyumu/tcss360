import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author Benji Lee
 *
 */
public class Subproject {
    private String name;
    // list to save documents saved.
    private ArrayList<File> documents;
    
    public void setDocuments(ArrayList<File> documents) {
		this.documents = documents;
	}

	// list to save items. 
    private List<Item> items;
    
    
    public List<Item> getItems() {
		return items;
	}
    
	public ArrayList<File> getDocuments() {
		return documents;
	}
    

	public void setName(String name) {
		this.name = name;
	}

	

   // private List<String> ;

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
