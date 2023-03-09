import javax.imageio.ImageIO;
import javax.swing.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author Benji Lee
 *
 */
public class ItemScreen extends JPanel {
private ArrayList<File> documents;
private JPanel documentPanel;
private JButton addButton;
private JButton deleteButton;
private JButton checkButton;
private Subproject thisSubproject;
public ItemScreen(Subproject subproject) {
	thisSubproject = subproject;
	documents = thisSubproject.getDocuments();
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


    documentPanel = new JPanel();
    documentPanel.setLayout(new BoxLayout(documentPanel, BoxLayout.Y_AXIS));
    documentPanel.setBorder(BorderFactory.createTitledBorder("Documents"));

    JScrollPane scrollPane = new JScrollPane(documentPanel);
    add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    addButton = new JButton("Add Document");
    deleteButton = new JButton("Delete Document");
    checkButton = new JButton("Check Document");
    addButton.addActionListener(e -> {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            addDocument(selectedFile);
        }
    });
    deleteButton.addActionListener(e -> {
        deleteSelectedDocument();
    });
    checkButton.addActionListener(e -> {
        checkSelectedDocument();
    });
    buttonPanel.add(addButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(checkButton);

    add(buttonPanel, BorderLayout.SOUTH);
	updateDocumentPanel();
}

private void addDocument(File selectedFile) {
    documents.add(selectedFile);
    //add a document from doc lists.
    thisSubproject.setDocuments(documents);
    updateDocumentPanel();
     
}

private void deleteSelectedDocument() {
    Component[] components = documentPanel.getComponents();
    for (Component component : components) {
        if (component instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                documents.remove(checkBox.getClientProperty("document"));
            }
        }
    }
    //delete a document from doc lists.
    thisSubproject.setDocuments(documents);
    updateDocumentPanel();

}

private void checkSelectedDocument() {
    Component[] components = documentPanel.getComponents();
    for (Component component : components) {
        if (component instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                File document = (File) checkBox.getClientProperty("document");
                if (document.getName().toLowerCase().endsWith(".pdf")) {
                    try {
                        Desktop.getDesktop().open(document);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error opening PDF document: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot check non-PDF document", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}


private void updateDocumentPanel() {
    documentPanel.removeAll();
    
    for (File document : thisSubproject.getDocuments()) {
        JCheckBox checkBox = new JCheckBox(document.getName());
        checkBox.putClientProperty("document", document);
        documentPanel.add(checkBox);

        if (document.getName().endsWith(".pdf")) {
            try (PDDocument pdfDocument = PDDocument.load(document)) {
                PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
                BufferedImage image = pdfRenderer.renderImage(0, 2.0f); // adjust the scale as needed
                ImagePanel imagePanel = new ImagePanel(image);
                documentPanel.add(imagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isImage(document)) {
            try {
                Image image = ImageIO.read(document);
                ImagePanel imagePanel = new ImagePanel(image);
                documentPanel.add(imagePanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    documentPanel.revalidate();
    documentPanel.repaint();
}

private boolean isImage(File file) {
    String name = file.getName().toLowerCase();
    return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif");
}

// Helper class to display images in a JPanel
private static class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}

}
