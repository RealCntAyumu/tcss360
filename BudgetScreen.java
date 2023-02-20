package teamViolet;

import javax.swing.*;
import java.awt.*;

/***
 * 
 * @author Ayumu Oshiro
 *
 */
public class BudgetScreen extends JPanel {
    private JProgressBar budgetBar;
    private JLabel costLabel;
    private JButton editButton;
    
    public BudgetScreen() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        budgetBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        budgetBar.setValue(50);
        budgetBar.setStringPainted(true);
        add(budgetBar);

        costLabel = new JLabel("Cost: $50");
        add(costLabel);

        editButton = new JButton("Edit");
        add(editButton);
    }
}
