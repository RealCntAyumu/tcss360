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
    private ArrayList<File> documents;
    private List<Item> items;
    
    public Subproject(String name) {
        this.name = name;
        items = new ArrayList<>();
        documents = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<File> getDocuments() {
        return documents;
    }
    
    public void setDocuments(ArrayList<File> documents) {
        this.documents = documents;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}

class Item {
    private String name;
    private String description;
    private boolean completed;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        completed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
