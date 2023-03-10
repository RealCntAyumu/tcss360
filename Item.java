/**
 * 
 * @author Ayumu Oshiro
 * @author Abdirizak Ali
 *
 */
public class Item {
	private String filename;
	private String filepath;
	private String name;
    private int cost;

	
    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public Item(String filename, String filepath) {
		this.filename = filename;
		this.filepath = filepath;
	}

}
