import java.util.ArrayList;
import java.util.List;

public class Subproject {
    private String name;
    public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<Item> items;

   // private List<String> ;

    public Subproject(String name) {
        this.name = name;
        items = new ArrayList<>();
       // this.tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
    	items.add(item);
    }

   // public List<Task> getTasks() {
       // return tasks;
    //}

   // public void addTask(Task task) {
    //    tasks.add(task);
    //}
}
