

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author Ayumu Oshiro
 *
 */
public class HomePage extends JPanel {
    private JPanel projectsPanel;
    private int projectCount = 0;

    public HomePage() {
        setLayout(new BorderLayout());

        // Create a header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JLabel headerLabel = new JLabel("Project Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        headerPanel.add(headerLabel);

        // Create a content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

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
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new project card
                projectCount++;
                String projectName = "Project " + projectCount;
                ProjectCard projectCard = new ProjectCard(projectName);

                // Add the project card to the projects panel
                projectsPanel.add(projectCard);
                projectsPanel.revalidate();
                projectsPanel.repaint();
            }
        });
        createProjectPanel.add(createProjectButton);

        // Add the header, projects, and "create project" button panels to the main panel
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(createProjectPanel, BorderLayout.SOUTH);
    }

    // A class to represent a project card
    private class ProjectCard extends JPanel {
        private String projectName;

        public ProjectCard(String projectName) {
            this.projectName = projectName;

            // Set the panel layout
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Create the card components
            JLabel titleLabel = new JLabel(projectName, SwingConstants.CENTER);
            titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

            JButton subprojectButton = new JButton("Subprojects");
            subprojectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the subproject page for this project
                    SubprojectPage subprojectPage = new SubprojectPage();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(HomePage.this);
                    frame.getContentPane().add(subprojectPage, "Subproject");
                    CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                    cardLayout.show(frame.getContentPane(), "Subproject");
                }
            });

            // Add the components to the panel
            add(titleLabel, BorderLayout.CENTER);
            add(subprojectButton, BorderLayout.SOUTH);
        }

        public String getProjectName() {
            return projectName;
        }
    }
}
