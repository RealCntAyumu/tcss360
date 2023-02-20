package teamViolet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/***
 * 
 * @author Ayumu Oshiro
 *
 */
public class ItemScreen extends JPanel {
    private ArrayList<JButton> folderButtons;
    private JPanel documentPanel;
    private JButton addButton;
    private JButton deleteButton;

    public ItemScreen() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel folderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        folderPanel.setPreferredSize(new Dimension(150, getHeight()));
        folderPanel.setBorder(BorderFactory.createTitledBorder("Folders"));

        folderButtons = new ArrayList<>();
        JButton folderButton1 = new JButton("Folder 1");
        JButton folderButton2 = new JButton("Folder 2");
        JButton folderButton3 = new JButton("Folder 3");
        folderButtons.add(folderButton1);
        folderButtons.add(folderButton2);
        folderButtons.add(folderButton3);

        for (JButton folderButton : folderButtons) {
            folderButton.addActionListener(e -> {
                updateDocumentPanel(); // update the document panel with the selected folder's documents
            });
            folderPanel.add(folderButton);
        }

        add(folderPanel, BorderLayout.WEST);

        documentPanel = new JPanel();
        documentPanel.setLayout(new BoxLayout(documentPanel, BoxLayout.Y_AXIS));
        documentPanel.setBorder(BorderFactory.createTitledBorder("Documents"));

        JScrollPane scrollPane = new JScrollPane(documentPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton = new JButton("Add Document");
        deleteButton = new JButton("Delete Document");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateDocumentPanel() {
        // TODO: update the document panel with the selected folder's documents
    }
}
