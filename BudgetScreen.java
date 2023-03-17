import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 
 * @author Abdirizak ali
 * @author Kevin Tra Hua
 *
 */

public class BudgetScreen extends JPanel {
    private JProgressBar budgetBar;
    private JLabel costLabel;
    private JLabel maxBudget;
    private JButton editButton;
    private JButton addCost;
    private JButton resetButton;
    private JPanel itemsPanel;

    /**
     * Creates a new BudgetScreen object which initializes all of the Java Swing components in the budget tab.
     * If there is existing data in the passed subproject, will initialize that data.
     * Also creates buttons and adds behavior into the buttons whenever the BudgetScreen object is created.
     * @param subproject
     */
    public BudgetScreen(Subproject subproject) {
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

        // Button to change the max value of the budget
        editButton = new JButton("Edit Budget");
        buttonPanel.add(editButton);
        buttonPanel.add(new JPanel()); // adding empty JPanel for visual
        
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

        itemsPanel = new JPanel(new GridLayout(10, 1));
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);
        itemsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemListPanel.add(itemsScrollPane, BorderLayout.CENTER);

        //Load the BudgetScreen again after going from Subprojects Page
        if (itemsPanel.getComponentCount() == 0) {
            loadSubproject(subproject);
        }
        
        // Button to reset the budget
        resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);

        //Adding Behavior to Buttons

        // Add Button
        
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
                subproject.setBudget(budgetBar);

                Item addItem = new Item(itemField.getText(), cost);
                subproject.addItem(addItem);

                JPanel addedItemPanel = new JPanel(new GridLayout(1,3));
                addedItemPanel.add(new JLabel("Item: " + addItem.getName()));
                addedItemPanel.add(new JLabel("Cost: $" + addItem.getCost()));
                
                //Remove Button
                JButton remove = new JButton("Remove");
                remove.addActionListener(evt -> {
                    itemsPanel.remove(addedItemPanel);
                    budgetBar.setValue(budgetBar.getValue() - cost);
                    //Update Progress Bar
                    costLabel.setText("Cost: $" + budgetBar.getValue());
                    budgetBar.revalidate();
                    budgetBar.repaint();
                    subproject.setBudget(budgetBar);
                    itemsPanel.revalidate();
                    itemsPanel.repaint();

                    subproject.removeItem(addItem);
                });
                addedItemPanel.add(remove);
                addedItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                itemsPanel.add(addedItemPanel);

                itemField.setText(null);
                costField.setText(null);
            }
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
                subproject.setBudget(budgetBar);
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
                
                subproject.setBudget(budgetBar);
                subproject.setItems(new ArrayList<Item>());
            }
        });
    }

    /**
     * Method that runs only when there are 0 components in the items panel. It will then check the subproject object that is passed through
     * and fill out the existing datat hat was stored in the previous subproject object.
     * @param subproject
     */
    private void loadSubproject(Subproject subproject) {
        //Gets the subproject progress bar if there is existing data
        if (subproject.getBudget() != null) {
            budgetBar.setValue(subproject.getBudget().getValue());
            budgetBar.setMaximum(subproject.getBudget().getMaximum());
        }
        //Grabs all the items that were previouslly inputted
        for(Item i : subproject.getItems()) {
            JPanel addedItemPanel = new JPanel(new GridLayout(1,3));
            addedItemPanel.add(new JLabel("Item: " + i.getName()));
            addedItemPanel.add(new JLabel("Cost: $" + i.getCost()));
            JButton remove = new JButton("Remove");
                remove.addActionListener(evt -> {
                    itemsPanel.remove(addedItemPanel);
                    budgetBar.setValue(budgetBar.getValue() - i.getCost());
                    //Update Progress Bar
                    costLabel.setText("Cost: $" + budgetBar.getValue());
                    budgetBar.revalidate();
                    budgetBar.repaint();
                    subproject.setBudget(budgetBar);
                    itemsPanel.revalidate();
                    itemsPanel.repaint();

                    subproject.removeItem(i);
                });
                addedItemPanel.add(remove);
                addedItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                itemsPanel.add(addedItemPanel);
        }
    }
}
