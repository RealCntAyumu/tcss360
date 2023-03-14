
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Abdirizak ali
 * @author Benji Lee
 *
 */
public class OverviewPage extends JPanel {
	private Subproject thisSubproject;


    public OverviewPage(Subproject subproject) {
    	thisSubproject = subproject;
    	setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create a header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        headerPanel.setBackground(new Color(51, 102, 255));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        JLabel headerLabel = new JLabel("Overview of " + thisSubproject.getName(), SwingConstants.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        headerPanel.add(headerLabel, BorderLayout.CENTER);


        JPanel pervButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton pervButton = new JButton("Back to Subproject");
        pervButton.setBackground(new Color(0, 153, 0));
        pervButton.setForeground(Color.black);
        pervButton.setFocusPainted(false);
        pervButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show the perv page
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Subproject");
            }
        });
        

        pervButtonPanel.setBackground(new Color(51, 102, 255));
        pervButtonPanel.add(pervButton);
        add(pervButtonPanel, BorderLayout.PAGE_END);

        //headerPanel.add(backButtonPanel, BorderLayout.PAGE_END);

        add(headerPanel, BorderLayout.NORTH);

        // Create a panel with tabs
        JTabbedPane tabbedPane = new JTabbedPane();
     // Change the font and color of the tab labels
        Font tabFont = new Font("Helvetica", Font.BOLD, 16);
        Color tabColor = new Color(51, 102, 255);
        tabbedPane.setFont(tabFont);
        tabbedPane.setForeground(tabColor);
        
        add(tabbedPane, BorderLayout.CENTER);

        // Create the Overview tab
        JPanel overviewPanel = new JPanel(new BorderLayout());
        overviewPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Overview", overviewPanel);

        // Create the Budget tab
        BudgetScreen budgetPanel = new BudgetScreen();
        budgetPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Budget", budgetPanel);

        // Create the Item tab
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        tabbedPane.addTab("Documents", itemPanel);
        
        //Item screen
        ItemScreen itempanel = new ItemScreen(thisSubproject);
        itemPanel.add(itempanel, BorderLayout.CENTER);

        // Add components to the Overview tab
        JPanel overviewTopPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        overviewTopPanel.setBackground(Color.WHITE);
        overviewTopPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        overviewPanel.add(overviewTopPanel, BorderLayout.NORTH);

        // Add the budget bar graph to the top panel
        JPanel budgetGraphPanel = new JPanel();
        budgetGraphPanel.setBackground(Color.WHITE);
        budgetGraphPanel.setPreferredSize(new Dimension(300, 200));
        budgetGraphPanel.setBorder(BorderFactory.createLineBorder(new Color(51, 102, 255), 2));
        overviewTopPanel.add(budgetGraphPanel);
        
        
        // Add the top 3 expenses panel to the top panel
        JPanel expensesPanel = new JPanel(new BorderLayout());
        expensesPanel.setBackground(Color.WHITE);
        expensesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        overviewTopPanel.add(expensesPanel);

        JLabel expensesLabel = new JLabel("Top 3 Expenses", SwingConstants.CENTER);
        expensesLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        expensesPanel.add(expensesLabel, BorderLayout.NORTH);
        
        // Add the expense items to the expenses panel
        JPanel expenseItemsPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        expenseItemsPanel.setBackground(Color.WHITE);
        expensesPanel.add(expenseItemsPanel, BorderLayout.CENTER);

        for (int i = 0; i < 3; i++) {
            JLabel expenseItemLabel = new JLabel("Expense Item " + (i + 1), SwingConstants.CENTER);
            expenseItemLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            expenseItemsPanel.add(expenseItemLabel);
        }

        // Add the item list panel to the overview panel
        JPanel itemListPanel = new JPanel(new BorderLayout());
        itemListPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        itemListPanel.setBackground(Color.WHITE);
        itemListPanel.setPreferredSize(new Dimension(300, 400));
        itemListPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        overviewPanel.add(itemListPanel, BorderLayout.CENTER);

        JLabel itemListLabel = new JLabel("Item List", SwingConstants.CENTER);
        itemListLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        itemListPanel.add(itemListLabel, BorderLayout.NORTH);

        // Add the item list to the item list panel
        JPanel itemsPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        itemsPanel.setBackground(Color.WHITE);
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);
        itemsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemListPanel.add(itemsScrollPane, BorderLayout.CENTER);

        for (int i = 0; i < 10; i++) {
            JLabel itemLabel = new JLabel("Item " + (i + 1));
            itemLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            itemsPanel.add(itemLabel);
        }
        

    }
}
