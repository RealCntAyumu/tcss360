import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Project {
    private String projectName;
    public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	private  HashMap<String, Subproject> subprojects;
	

    public Project(String projectName) {
        this.projectName = projectName;
        subprojects = new  HashMap<String, Subproject>();
    }
    
    public String getProjectName() {
    	return projectName;
    }

    public void addSubproject(Subproject subproject) {
        subprojects.put(subproject.getName(), subproject);
    }

    public  HashMap<String, Subproject> getSubprojects() {
        return subprojects;
    }
    
    

    // Other getters and setters
}
