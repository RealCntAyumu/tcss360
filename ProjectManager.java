
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Ayumu Oshiro & Abdirizak Ali(Class Template)
 * 
 */
public class ProjectManager {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Project Manager");
        mainWindow.setSize(800, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create pages and add them to the main window
        HomePage homePage = new HomePage();
        //SubprojectPage subprojectPage = new SubprojectPage();
        //OverviewPage overviewPage = new OverviewPage();

        // create a CardLayout and add pages to it
        CardLayout cardLayout = new CardLayout();
        JPanel contentPane = new JPanel(cardLayout);
        LoginPage loginPage = new LoginPage();
        contentPane.add(loginPage, "Login");
        contentPane.add(loginPage, "Login");
        contentPane.add(homePage, "Home");
        //contentPane.add(subprojectPage, "Subproject");
       // contentPane.add(overviewPage, "Overview");

        mainWindow.setContentPane(contentPane);
        mainWindow.setVisible(true);
    }
}
