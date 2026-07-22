import java.awt.*;
import javax.swing.*;

public class LoginPage extends JFrame {

   
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "123";

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome Back", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(100, 20, 200, 30);
        add(welcomeLabel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(40, 80, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(40, 120, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 180, 25);
        add(passwordField);

       
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(70, 190, 100, 30);
        add(loginBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(220, 190, 100, 30);
        add(exitBtn);

        loginBtn.addActionListener(e -> checkLogin());
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }

        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            new MainMenu();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Wrong username or password");
        }
    }
}
