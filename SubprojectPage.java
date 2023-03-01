


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
public class SubprojectPage extends JPanel {
    private JPanel subprojectsPanel;
    private int subprojectCount = 0;

    public SubprojectPage() {
        setLayout(new BorderLayout());

        // Create a header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel headerLabel = new JLabel("Subprojects", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Projects");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the HomePage screen
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SubprojectPage.this);
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), "HomePage");

                // Remove the SubprojectPage from the content pane
                Container contentPane = frame.getContentPane();
                contentPane.remove(SubprojectPage.this);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        backButtonPanel.add(backButton);
        headerPanel.add(backButtonPanel, BorderLayout.PAGE_END);


        // Create a content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create a panel to hold the subprojects
        subprojectsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
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
                // Create a new subproject card
                subprojectCount++;
                String subprojectName = "Subproject " + subprojectCount;
                SubprojectCard subprojectCard = new SubprojectCard(subprojectName);

                // Add the subproject card to the subprojects panel
                subprojectsPanel.add(subprojectCard);
                subprojectsPanel.revalidate();
                subprojectsPanel.repaint();
            }
        });
        createSubprojectPanel.add(createSubprojectButton);

        // Add the header, subprojects, and "create subproject" button panels to the main panel
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(createSubprojectPanel, BorderLayout.SOUTH);
    }

    // A class to represent a subproject card
    private class SubprojectCard extends JPanel {
        private String subprojectName;

        public SubprojectCard(String subprojectName) {
            this.subprojectName = subprojectName;

            // Set the panel layout
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Create the card components
            JLabel titleLabel = new JLabel(subprojectName, SwingConstants.CENTER);
            titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

            JButton overviewButton = new JButton("Overview");
            overviewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the overview page for this subproject
                    OverviewPage overviewPage = new OverviewPage();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SubprojectPage.this);
                    frame.getContentPane().add(overviewPage, "Overview");
                    CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                    cardLayout.show(frame.getContentPane(), "Overview");
                }
            });
            
            // Add the components to the panel
            add(titleLabel, BorderLayout.CENTER);
            add(overviewButton, BorderLayout.SOUTH);
           
        }
        public String getProjectName() {
            return subprojectName;
        }
    }
}

           








