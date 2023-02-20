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
class SubprojectPage extends JPanel {
    public SubprojectPage() {
        // Set the panel properties
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Create the components for the subproject page
        JLabel title = new JLabel("Create a Subproject");
        JButton createSubprojectButton = new JButton("Create Subproject");

        // Add the components to the panel
        add(title);
        add(createSubprojectButton);

        // Add an action listener for the create subproject button
        createSubprojectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the overview page
                OverviewPage overviewPage = new OverviewPage();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SubprojectPage.this);
                frame.getContentPane().add(overviewPage, "Overview");
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), "Overview");
            }
        });
    }
}








