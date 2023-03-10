import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetScreen extends JPanel {
    private JProgressBar budgetBar;
    private JLabel costLabel;
    private JLabel maxBudget;
    private JButton editButton;
    private JButton addCost;
    private JButton removeCost;
    private JButton resetButton;
    private List<Item> itemList;

    public BudgetScreen() {
        setLayout(new GridLayout(4, 1));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Progress Bar Panel
        JPanel progressBarPanel = new JPanel(new GridLayout(2, 1));
        progressBarPanel.setBackground(Color.WHITE);
        progressBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // Budget Label on top of Progress Bar
        JLabel progressBarLabel = new JLabel("Budget", SwingConstants.CENTER);
        progressBarLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        progressBarPanel.add(progressBarLabel);
        // Progress Bar
        budgetBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        budgetBar.setStringPainted(true);
        progressBarPanel.add(budgetBar);
        add(progressBarPanel);

        // Label Panel and Labels
        JPanel labelPanel = new JPanel(new GridLayout(1, 3));
        labelPanel.setBackground(getBackground());
        // Cost Label
        costLabel = new JLabel("Cost: $" + budgetBar.getValue(), SwingConstants.CENTER);
        costLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
        labelPanel.add(costLabel);
        labelPanel.add(new JPanel()); // adding empty JPanel for visual
        // Budget label
        maxBudget = new JLabel("Max Budget: $100", SwingConstants.CENTER);
        maxBudget.setFont(new Font("Helvetica", Font.BOLD, 14));
        labelPanel.add(maxBudget);
        add(labelPanel);

        // Button Panel and Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        add(buttonPanel);

        // Creating a JPanel for OptionPane
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


        // Button to remove costs
        removeCost = new JButton("Remove Cost");
        buttonPanel.add(removeCost);
        buttonPanel.add(new JPanel()); // adding empty JPanel for visual

        

        // Button to change the max value of the budget
        editButton = new JButton("Edit");
        buttonPanel.add(editButton);

        // Items Panel
        JPanel itemListPanel = new JPanel();
        itemListPanel.setLayout(new BoxLayout(itemListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(itemListPanel);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        itemListPanel.setBackground(Color.WHITE);
        itemListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(itemListPanel);

        JLabel itemListLabel = new JLabel("Item List", SwingConstants.CENTER);
        itemListLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        itemListPanel.add(itemListLabel, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel(new GridLayout(10, 1));
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);
        itemsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemListPanel.add(itemsScrollPane, BorderLayout.CENTER);

        // Button to reset the budget
        resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);
        buttonPanel.add(new JPanel()); // adding empty JPanel for visual

        //Adding Behavior to Buttons
        // Button to add an item
        
        addCost.addActionListener(e -> {
            int optionSelect = JOptionPane.showConfirmDialog(null, itemCostPanel, "Enter Item and Cost", JOptionPane.OK_CANCEL_OPTION);
            if (optionSelect == JOptionPane.OK_OPTION) {
                int cost = Integer.parseInt(costField.getText());
                if (budgetBar.getValue() + cost > budgetBar.getMaximum()) {
                    JOptionPane.showMessageDialog(null, "This cost exceeds the project budget. Please enter a lower cost.");
                    return;
                }
                budgetBar.setValue(budgetBar.getValue() + cost);
                //Update Progress Bar
                costLabel.setText("Cost: $" + budgetBar.getValue());
                budgetBar.revalidate();
                budgetBar.repaint();

                JPanel item = new JPanel(new GridLayout(1,3));
                item.add(new JLabel("Item: " + itemField.getText()));
                item.add(new JLabel("Cost: $" + cost));
                JButton remove = new JButton("Remove");
                remove.addActionListener(evt -> {
                    itemsPanel.remove(item);
                    budgetBar.setValue(budgetBar.getValue() - cost);
                    //Update Progress Bar
                    costLabel.setText("Cost: $" + budgetBar.getValue());
                    budgetBar.revalidate();
                    budgetBar.repaint();
                    itemsPanel.revalidate();
                    itemsPanel.repaint();
                });
                item.add(remove);
                item.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                itemsPanel.add(item);

                itemField.setText(null);
                costField.setText(null);
            }
        });


        
        

        //Remove Cost
        removeCost.addActionListener(e -> {
            int costValue = Integer.parseInt(JOptionPane.showInputDialog(BudgetScreen.this, "Enter Removed Cost:"));
            if (budgetBar.getValue() - costValue < 0) {
                JOptionPane.showMessageDialog(null, "The entered cost is greater than the current total cost. Please enter a lower cost.");
                return;
            }
            budgetBar.setValue(budgetBar.getValue() - costValue);
            costLabel.setText("Cost: $" + budgetBar.getValue());
            budgetBar.revalidate();
            budgetBar.repaint();
        });


        //Edit Cost
        editButton.addActionListener(e -> {
            String inputValue = JOptionPane.showInputDialog(BudgetScreen.this, "Enter Project Budget:");
            try {
                int projectValue = Integer.parseInt(inputValue);
                if (projectValue < budgetBar.getValue()) {
                    JOptionPane.showMessageDialog(BudgetScreen.this, "Project Budget can't be less than current cost!");
                    return;
                }
                budgetBar.setMaximum(projectValue);
                maxBudget.setText("Max Budget: $" + projectValue);
                costLabel.setText("Cost: $" + budgetBar.getValue());
                budgetBar.revalidate();
                budgetBar.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BudgetScreen.this, "Invalid input!");
            }
        });
        // Reset Button
        resetButton.addActionListener(e -> {
            int optionSelect = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the budget?", "Reset Budget", JOptionPane.OK_CANCEL_OPTION);
            if (optionSelect == JOptionPane.OK_OPTION) {
                budgetBar.setValue(0);
                costLabel.setText("Cost: $" + budgetBar.getValue());
                itemsPanel.removeAll();
                itemsPanel.revalidate();
                itemsPanel.repaint();
            }
        });
        

    }
}
