import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    
    public void addDocument(File document) {
        documents.add(document);
    }
    
    public ArrayList<File> getDocuments() {
        return documents;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    }

public class OverviewPage {
public void displaySubprojectDetails(Subproject subproject) {
String subprojectName = subproject.getName();
List<Item> subprojectItems = subproject.getItems();
ArrayList<File> subprojectDocuments = subproject.getDocuments();
    System.out.println("Subproject Name: " + subprojectName);
    System.out.println("Subproject Items: ");
    for (Item item : subprojectItems) {
        System.out.println("- " + item.getName());
    }

    System.out.println("Subproject Documents: ");
    for (File document : subprojectDocuments) {
        System.out.println("- " + document.getName());
    }
}
}
