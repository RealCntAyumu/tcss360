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
public class OverviewPage extends JPanel {
    public OverviewPage() {
        
        // add tabs on panel
        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        JPanel overviewPanel = new JPanel();
        JPanel budgetPanel = new JPanel();
        JPanel itemPanel = new JPanel();

        tabbedPane.addTab("Overview", overviewPanel);
        tabbedPane.addTab("Budget", budgetPanel);
        tabbedPane.addTab("Item", itemPanel);
        
        // Overview screen component: 1: Budget Bar Graph 2: top 3 expenses box 3: item list box.

        //Budget Screen components: 1: Budget Bar Graph, 2: cost input box and 3: cost estimation box
       
        // Item Screen components: file management system to add and delete documents
        
    }
}