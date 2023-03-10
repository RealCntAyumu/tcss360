import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class LoginPage extends JPanel implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox rememberPasswordCheckbox;
    private JCheckBox createUserCheckbox;

    private Preferences preferences;

    public LoginPage() {
        setLayout(new BorderLayout());

        // create the login form
        JPanel loginForm = new JPanel(new GridLayout(5, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        rememberPasswordCheckbox = new JCheckBox("Remember password");
        createUserCheckbox = new JCheckBox("Create new user");
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginForm.add(usernameLabel);
        loginForm.add(usernameField);
        loginForm.add(passwordLabel);
        loginForm.add(passwordField);
        loginForm.add(new JLabel()); // empty label to align checkbox
        loginForm.add(rememberPasswordCheckbox);
        loginForm.add(new JLabel()); // empty label to align button
        loginForm.add(loginButton);
        loginForm.add(new JLabel()); // empty label to align checkbox
        loginForm.add(createUserCheckbox);

        add(loginForm, BorderLayout.CENTER);

        // load the saved preferences
        preferences = Preferences.userNodeForPackage(LoginPage.class);
        String savedUsername = preferences.get("username", "");
        String savedPassword = preferences.get("password", "");
        usernameField.setText(savedUsername);
        passwordField.setText(savedPassword);
        rememberPasswordCheckbox.setSelected(!savedPassword.isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check if the user wants to create a new account
        boolean createNewUser = createUserCheckbox.isSelected();
        if (createNewUser) {
            String newUsername = JOptionPane.showInputDialog(this, "Enter a new username:");
            if (newUsername == null || newUsername.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid username.");
                return;
            }

            String newPassword = JOptionPane.showInputDialog(this, "Enter a new password:");
            if (newPassword == null || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid password.");
                return;
            }

            preferences.put("username", newUsername);
            preferences.put("password", newPassword);

            // switch to the home page
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), "Home");
        } else {
            // check if the username and password are valid
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String savedUsername = preferences.get("username", "");
            String savedPassword = preferences.get("password", "");
            if (username.equals(savedUsername) && password.equals(savedPassword)) {
                // save the preferences if the "remember password" checkbox is checked
                if (rememberPasswordCheckbox.isSelected()) {
                    preferences.put("username", username);
                    preferences.put("password", password);
                } else {
                    preferences.remove("username");
                    preferences.remove("password");
                }

                // switch to the home page
                CardLayout cardLayout = (CardLayout) getParent().getLayout();
                cardLayout.show(getParent(), "Home");
            } else {
                // show an error message
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
    }
}
