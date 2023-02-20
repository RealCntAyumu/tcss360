package teamViolet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/***
 * 
 * @author Ayumu Oshiro(Class Template)
 *
 */
class HomePage extends JPanel {
    public HomePage() {
        // Set the panel properties
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Create the components for the home page
        JLabel title = new JLabel("Project Management System");
        JButton createProjectButton = new JButton("Create Project");

        // Add the components to the panel
        add(title);
        add(createProjectButton);

        // Add an action listener for the create project button
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the subproject page
                SubprojectPage subprojectPage = new SubprojectPage();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(HomePage.this);
                frame.getContentPane().add(subprojectPage, "Subproject");
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), "Subproject");
            }
        });
    }
}

