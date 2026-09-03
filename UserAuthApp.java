import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class UserAuthApp {

    static HashMap<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomePage::new);
    }

    // ---------------- WELCOME PAGE ----------------
    static class WelcomePage extends JFrame {

        WelcomePage() {
            setTitle("FitBeat AI");
            setSize(400, 250);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JLabel title = new JLabel("FITBEAT AI", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 24));

            JButton loginBtn = new JButton("Log In");
            JButton registerBtn = new JButton("Create Account");

            loginBtn.addActionListener(e -> {
                dispose();
                new LoginPage();
            });

            registerBtn.addActionListener(e -> {
                dispose();
                new RegisterPage();
            });

            JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
            panel.add(title);
            panel.add(loginBtn);
            panel.add(registerBtn);

            add(panel);
            setVisible(true);
        }
    }

    // ---------------- LOGIN PAGE ----------------
    static class LoginPage extends JFrame {

        LoginPage() {
            setTitle("Login");
            setSize(400, 300);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(5, 2, 10, 10));

            JTextField username = new JTextField();
            JPasswordField password = new JPasswordField();

            JButton login = new JButton("Login");
            JButton back = new JButton("Back");

            add(new JLabel("Username:"));
            add(username);

            add(new JLabel("Password:"));
            add(password);

            add(login);
            add(back);

            login.addActionListener(e -> {
                String user = username.getText();
                String pass = new String(password.getPassword());

                if (users.containsKey(user)
                        && users.get(user).equals(pass)) {

                    JOptionPane.showMessageDialog(this,
                            "Login Successful");

                    dispose();
                    new Dashboard(user);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid Username or Password");
                }
            });

            back.addActionListener(e -> {
                dispose();
                new WelcomePage();
            });

            setVisible(true);
        }
    }

    // ---------------- REGISTER PAGE ----------------
    static class RegisterPage extends JFrame {

        RegisterPage() {
            setTitle("Create Account");
            setSize(400, 350);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(6, 2, 10, 10));

            JTextField name = new JTextField();
            JTextField username = new JTextField();
            JPasswordField password = new JPasswordField();
            JPasswordField confirm = new JPasswordField();

            JButton create = new JButton("Create Account");
            JButton back = new JButton("Back");

            add(new JLabel("Full Name:"));
            add(name);

            add(new JLabel("Username:"));
            add(username);

            add(new JLabel("Password:"));
            add(password);

            add(new JLabel("Confirm Password:"));
            add(confirm);

            add(create);
            add(back);

            create.addActionListener(e -> {

                String user = username.getText();
                String pass = new String(password.getPassword());
                String confirmPass = new String(confirm.getPassword());

                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Fill all fields");
                    return;
                }

                if (!pass.equals(confirmPass)) {
                    JOptionPane.showMessageDialog(this,
                            "Passwords do not match");
                    return;
                }

                if (users.containsKey(user)) {
                    JOptionPane.showMessageDialog(this,
                            "Username already exists");
                    return;
                }

                users.put(user, pass);

                JOptionPane.showMessageDialog(this,
                        "Account Created Successfully");

                dispose();
                new LoginPage();
            });

            back.addActionListener(e -> {
                dispose();
                new WelcomePage();
            });

            setVisible(true);
        }
    }

    // ---------------- DASHBOARD ----------------
    static class Dashboard extends JFrame {

        Dashboard(String username) {

            setTitle("Dashboard");
            setSize(450, 300);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JLabel welcome =
                    new JLabel("Welcome, " + username,
                            JLabel.CENTER);

            welcome.setFont(
                    new Font("Arial",
                            Font.BOLD,
                            22));

            JButton logout = new JButton("Logout");

            logout.addActionListener(e -> {
                dispose();
                new WelcomePage();
            });

            setLayout(new BorderLayout());

            add(welcome, BorderLayout.CENTER);
            add(logout, BorderLayout.SOUTH);

            setVisible(true);
        }
    }
}