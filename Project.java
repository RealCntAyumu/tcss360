import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Project {
    private String projectName; // Declaring a private field for storing project name
    
    // Setter method for setting project name
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private  HashMap<String, Subproject> subprojects; // Declaring a private field for storing subprojects

    // Constructor for creating a new Project instance with a given project name
    public Project(String projectName) {
        this.projectName = projectName;
        subprojects = new  HashMap<String, Subproject>();
    }
    
    // Getter method for retrieving the project name
    public String getProjectName() {
        return projectName;
    }

    // Method for adding a new subproject to the project
    public void addSubproject(Subproject subproject) {
        subprojects.put(subproject.getName(), subproject);
    }

    // Getter method for retrieving all subprojects of the project
    public  HashMap<String, Subproject> getSubprojects() {
        return subprojects;
    }
    
    
}
