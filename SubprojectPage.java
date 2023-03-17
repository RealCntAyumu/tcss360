import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
/**
 * 
 * @author Ayumu Oshiro
 *
 */
public class SubprojectPage extends JPanel {
    private JPanel subprojectsPanel;
    private int subprojectCount = 0;
    private Project project;
	private HashMap<String, Subproject> subprojects;


    public SubprojectPage(Project project) {
    	this.project = project;
    	subprojects =  project.getSubprojects();
    	
    	
    	
        setLayout(new BorderLayout());

        // Create a header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        

        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel headerLabel = new JLabel("Subprojects of " + project.getProjectName(), SwingConstants.CENTER);
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        headerPanel.setBackground(new Color(255, 165, 0)); // set the background color

        headerPanel.add(headerLabel, BorderLayout.CENTER);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Projects");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the HomePage screen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SubprojectPage.this);
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), "Home");

                // Remove the SubprojectPage from the content pane
                
                Container contentPane = frame.getContentPane();
                contentPane.remove(SubprojectPage.this);
                contentPane.revalidate();
                contentPane.repaint();
                
            }
        });
        backButtonPanel.add(backButton);


        // Create a content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create a panel to hold the subprojects
        subprojectsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        subprojectsPanel.setBackground(new Color(245, 245, 245)); // Light gray background color

        
     // get all sub projects. 
    	for(Subproject subproject : subprojects.values()) {
            SubprojectCard subprojectCard = new SubprojectCard(subproject.getName(), subproject);
            subprojectsPanel.add(subprojectCard);
    	}
    	

        
        
        
        JScrollPane subprojectsScrollPane = new JScrollPane(subprojectsPanel);
        subprojectsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        subprojectsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add the subprojects panel to the content panel
        contentPanel.add(subprojectsScrollPane, BorderLayout.CENTER);

        // Create a panel for the "create subproject" button
        JPanel createSubprojectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton createSubprojectButton = new JButton("Create Subproject");
        createSubprojectButton.setPreferredSize(new Dimension(200, 50));

        createSubprojectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask the user for the project name
                String projectName = JOptionPane.showInputDialog(SubprojectPage.this, "Enter subproject name:");

                if (projectName != null && !projectName.trim().isEmpty()) {
                    // Create a new project card
                	subprojectCount++;
                    String subprojectName = projectName;

                    
                    Subproject subproject = new Subproject(subprojectName);
                    SubprojectCard subprojectCard = new SubprojectCard(subprojectName, subproject);

                    // add subproject to the project.
                    project.addSubproject(subproject);

                    // Add the subproject card to the subprojects panel
                    subprojectsPanel.add(subprojectCard);
                    subprojectsPanel.revalidate();
                    subprojectsPanel.repaint();
                }
            }
        });
        
        createSubprojectPanel.add(backButtonPanel);


        createSubprojectPanel.add(createSubprojectButton);

        // Add the header, subprojects, and "create subproject" button panels to the main panel
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(createSubprojectPanel, BorderLayout.SOUTH);
    }

    // A class to represent a subproject card
    private class SubprojectCard extends JPanel {
        private String subprojectName;

        public SubprojectCard(String subprojectName, Subproject subproject) {
            this.subprojectName = subprojectName;

            // Set the panel layout
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.decode("#808080"), 2));

            // Create the card components
            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            headerPanel.setBackground(Color.decode("#1E90FF"));
            JLabel titleLabel = new JLabel(subprojectName, SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
            headerPanel.add(titleLabel);

            JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            contentPanel.setBackground(Color.WHITE);
            JButton overviewButton = new JButton("Overview");
            overviewButton.setPreferredSize(new Dimension(150, 30));
            overviewButton.setBackground(Color.decode("#1E90FF"));
            overviewButton.setForeground(Color.decode("#1E90FF"));
            overviewButton.setFocusPainted(false);
            overviewButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
            overviewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the overview page for this subproject

                    OverviewPage overviewPage = new OverviewPage(subproject);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SubprojectPage.this);
                    frame.getContentPane().add(overviewPage, "Overview");
                    CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                    cardLayout.show(frame.getContentPane(), "Overview");
                }
            });
            contentPanel.add(overviewButton);
            
          //edit button
            JButton editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(100, 30));
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display a dialog box for editing the project
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    
                    JTextField nameField = new JTextField(subprojectName);
                    panel.add(new JLabel("Subproject Name:"));
                    panel.add(nameField);
                    
                    int option = JOptionPane.showConfirmDialog(
                            SubprojectPage.this,
                            panel,
                            "Edit Subproject",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    
                    if (option == JOptionPane.OK_OPTION) {
                        String newName = nameField.getText().trim();
                        if (!newName.isEmpty() && !newName.equals(subprojectName)) {
                            // Update the project name
                        	subproject.setName(newName);
                            subprojects.put(newName, subproject);
                            subprojects.remove(subprojectName);
                            subproject.setName(newName);
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
                            SubprojectPage.this,
                            "Are you sure you want to delete this project?",
                            "Delete Project",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    
                    if (option == JOptionPane.YES_OPTION) {
                        // Remove the project from the projects map
                        subprojects.remove(subproject.getName());

                        // Remove the project card from the projects panel
                        subprojectsPanel.remove(SubprojectCard.this);
                        subprojectsPanel.revalidate();
                        subprojectsPanel.repaint();
                    }
                }
            });
            
            // add button
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
       
            add(buttonPanel, BorderLayout.SOUTH);
            
            
            
            

            // Add the components to the panel
            add(headerPanel, BorderLayout.NORTH);
            add(contentPanel, BorderLayout.CENTER);

            // Set the panel size
            setPreferredSize(new Dimension(250, 150));
        }
    }

}

           








