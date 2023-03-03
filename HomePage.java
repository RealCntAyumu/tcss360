

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Ayumu Oshiro
 *
 */
public class HomePage extends JPanel {
	private HashMap<String, Project> projects;
    private JPanel projectsPanel;
    private int projectCount = 0;

    public HomePage() {
        projects = new HashMap<>();

        setLayout(new BorderLayout());
        setBackground(new Color(255, 222, 173)); // set the background color

        // Create a header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JLabel headerLabel = new JLabel("Project Management System", SwingConstants.CENTER);
        Font font = new Font("Montserrat", Font.BOLD, 32);
        headerLabel.setFont(font);
        headerPanel.setBackground(new Color(255, 165, 0)); // set the background color
        headerPanel.add(headerLabel);

        // Create a content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(new Color(255, 222, 173)); // set the background color

        // Create a panel to hold the projects
        projectsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane projectsScrollPane = new JScrollPane(projectsPanel);
        projectsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        projectsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the projects panel to the content panel
        contentPanel.add(projectsScrollPane, BorderLayout.CENTER);

        // Create a panel for the "create project" button
        JPanel createProjectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createProjectButton = new JButton("Create Project");
        createProjectButton.setPreferredSize(new Dimension(200, 50));
        createProjectButton.setBackground(new Color(255, 165, 0)); // set the background color
        createProjectButton.setForeground(Color.WHITE); // set the font color
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask the user for the project name
                String projectName = JOptionPane.showInputDialog(HomePage.this, "Enter project name:");

                if (projectName != null && !projectName.trim().isEmpty()) {
                    // Create a new project card
                    projectCount++;
                    // create a project
                    Project project = new Project(projectName);
                    ProjectCard projectCard = new ProjectCard(projectName, project);

                    projects.put(projectName, project);

                    // Add the project card to the projects panel
                    projectsPanel.add(projectCard);
                    projectsPanel.revalidate();
                    projectsPanel.repaint();
                }
            }
        });

        createProjectButton.setBorder(BorderFactory.createEmptyBorder()); // remove the button border
        createProjectPanel.setBackground(new Color(255, 165, 0)); // set the background color
        createProjectPanel.add(createProjectButton);

        // Add the header, projects, and "create project" button panels to the main panel
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(createProjectPanel, BorderLayout.SOUTH);
    }

    // A class to represent a project card
    private class ProjectCard extends JPanel {
        private String projectName;
        


        public ProjectCard(String projectName, Project project) {
            this.projectName = projectName;

            // Set the panel layout
            setLayout(new BorderLayout());
         
            setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
            setBackground(new Color(255, 218, 185));
            
            
            // Create the card components
            JLabel titleLabel = new JLabel(projectName, SwingConstants.CENTER);
            titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            titleLabel.setForeground(new Color(233, 150, 122));
            
            
            
            //edit button
            JButton editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(100, 30));
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display a dialog box for editing the project
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    
                    JTextField nameField = new JTextField(projectName);
                    panel.add(new JLabel("Project Name:"));
                    panel.add(nameField);
                    
                    int option = JOptionPane.showConfirmDialog(
                            HomePage.this,
                            panel,
                            "Edit Project",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    
                    if (option == JOptionPane.OK_OPTION) {
                        String newName = nameField.getText().trim();
                        if (!newName.isEmpty() && !newName.equals(projectName)) {
                            // Update the project name
                            projects.put(newName, project);
                            projects.remove(project.getProjectName());
                            project.setProjectName(newName);
                            titleLabel.setText(newName);
                        }
                    } else if (option == JOptionPane.CANCEL_OPTION) {
                        // Do nothing
                    }
                }
            });
            
           
           
            
            //delete button
            JButton deleteButton = new JButton("Delete");
           
            deleteButton.setPreferredSize(new Dimension(100, 30));
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int option = JOptionPane.showConfirmDialog(
                            HomePage.this,
                            "Are you sure you want to delete this project?",
                            "Delete Project",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    
                    if (option == JOptionPane.YES_OPTION) {
                        // Remove the project from the projects map
                        projects.remove(projectName);

                        // Remove the project card from the projects panel
                        projectsPanel.remove(ProjectCard.this);
                        projectsPanel.revalidate();
                        projectsPanel.repaint();
                    }
                }
            });
            
            // add button
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
       
            add(buttonPanel, BorderLayout.NORTH);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            add(titleLabel, BorderLayout.CENTER);



            JButton subprojectButton = new JButton("Subprojects");
            subprojectButton.setPreferredSize(new Dimension(200, 50));
            subprojectButton.setBackground(new Color(233, 150, 122));
            
            subprojectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the subproject page for this project
                    SubprojectPage subprojectPage = new SubprojectPage(project);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(HomePage.this);
                    frame.getContentPane().add(subprojectPage, "Subproject");
                    CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                    cardLayout.show(frame.getContentPane(), "Subproject");
                }
            });

            // Add the components to the panel
            add(subprojectButton, BorderLayout.SOUTH);
        }

        public String getProjectName() {
            return projectName;
        }
    }
}
