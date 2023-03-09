

import javax.swing.*;

import org.omg.PortableInterceptor.NON_EXISTENT;

import javafx.scene.layout.Background;

import java.awt.*;

/***
 * 
 * @author Ayumu Oshiro
 *
 */
public class BudgetScreen extends JPanel {
    private JProgressBar budgetBar;
    private JLabel costLabel;
    private JLabel maxBudget;
    private JButton editButton;
    private JButton addCost;
    private JButton removeCost;
    
    public BudgetScreen() {
        setLayout(new GridLayout(4, 1));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        //Progress Bar Panel
        JPanel progressBarPanel = new JPanel(new GridLayout(2,1));
        progressBarPanel.setBackground(Color.WHITE);
        progressBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            //Budget Label on top of Progress Bar
        JLabel progressBarLabel = new JLabel("Budget", SwingConstants.CENTER);
        progressBarLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        progressBarPanel.add(progressBarLabel);
            //Progress Bar
        budgetBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        budgetBar.setStringPainted(true);
        progressBarPanel.add(budgetBar);
        add (progressBarPanel);

        //Label Panel and Labels
        JPanel labelPanel = new JPanel(new GridLayout(1,3));
        labelPanel.setBackground(getBackground());
            //Cost Label
        costLabel = new JLabel("Cost: $" + budgetBar.getValue(), SwingConstants.CENTER);
        costLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        labelPanel.add(costLabel);
        labelPanel.add(new JPanel()); //adding empty JPanel for visual
            //Budget label
        maxBudget = new JLabel("Max Budget: $100", SwingConstants.CENTER);
        maxBudget.setFont(new Font("Helvetica", Font.BOLD, 14));
        labelPanel.add(maxBudget);
        add(labelPanel);

        //Button Panel and Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1,5));
        add(buttonPanel);

        //Creating a JPanel for OptionPane
        JTextField itemField = new JTextField(5);
        JTextField costField = new JTextField(5);
        JPanel itemCostPanel = new JPanel();

        itemCostPanel.add(new JLabel("Item: "));
        itemCostPanel.add(itemField);
        itemCostPanel.add(Box.createHorizontalStrut(15)); // SPACER
        itemCostPanel.add(new JLabel("Cost: "));
        itemCostPanel.add(costField);
        itemCostPanel.add(new JPanel());

        //Button to add costs
        addCost = new JButton("Add Cost");
        buttonPanel.add(addCost);
        buttonPanel.add(new JPanel());  //adding empty JPanel for visual

        //Button to remove costs
        removeCost = new JButton("Remove Cost");
        buttonPanel.add(removeCost);
        buttonPanel.add(new JPanel());  //adding empty JPanel for visual

        //Button to change the max value of the budget
        editButton = new JButton("Edit");
        buttonPanel.add(editButton);
        

        //Items Panel
        JPanel itemListPanel = new JPanel(new BorderLayout());
        itemListPanel.setBackground(Color.WHITE);
        itemListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add (itemListPanel);


        JLabel itemListLabel = new JLabel("Item List", SwingConstants.CENTER);
        itemListLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        itemListPanel.add(itemListLabel, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel(new GridLayout(10,1));
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);
        itemsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemListPanel.add(itemsScrollPane, BorderLayout.CENTER);

        //Adding Behavior to Buttons
        //Add Cost
        addCost.addActionListener(e -> {
            int optionSelect = JOptionPane.showConfirmDialog(null, itemCostPanel, "Enter Item and Cost", JOptionPane.OK_CANCEL_OPTION);
            if (optionSelect == JOptionPane.OK_OPTION) {
                budgetBar.setValue(budgetBar.getValue() + Integer.parseInt(costField.getText()));
                //Update Progress Bar
                costLabel.setText("Cost: $" + budgetBar.getValue());
                budgetBar.revalidate();
                budgetBar.repaint();

                JPanel item = new JPanel(new GridLayout(1,2));
                item.add(new JLabel("Item: " + itemField.getText()));
                item.add(new JLabel("Cost: $" + Integer.parseInt(costField.getText())));
                item.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                itemsPanel.add(item);

                itemField.setText(null);
                costField.setText(null);
            }
        });

        //Remove Cost
        removeCost.addActionListener(e -> {
            int costValue = Integer.parseInt(JOptionPane.showInputDialog(BudgetScreen.this, "Enter Removed Cost:"));
            budgetBar.setValue(budgetBar.getValue() - costValue);
            costLabel.setText("Cost: $" + budgetBar.getValue());
            budgetBar.revalidate();
            budgetBar.repaint();
        });

        //Edit Cost
        editButton.addActionListener(e -> {
            int projectValue = Integer.parseInt(JOptionPane.showInputDialog(BudgetScreen.this, "Enter Project Budget:"));
            budgetBar.setMaximum(projectValue);
            maxBudget.setText("Max Budget: $" + projectValue);
            budgetBar.revalidate();
            budgetBar.repaint();
        });
    }
}
