import javax.swing.*;
import java.awt.event.*;

public class ATMGUI {

    JFrame frame;

    public ATMGUI() {
    frame = new JFrame("ATM Simulation");
    frame.setSize(400, 350);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);

    showWelcomeScreen();

    frame.setVisible(true);
    }

    void clearFrame() {
    frame.getContentPane().removeAll();
    frame.repaint();
    }

    void showWelcomeScreen() {

    clearFrame();

    JLabel title = new JLabel("Welcome to ATM");
    title.setBounds(140, 30, 200, 30);
    frame.add(title);

    JButton registerBtn = new JButton("Register");
    registerBtn.setBounds(130, 80, 120, 30);
    frame.add(registerBtn);

    JButton loginBtn = new JButton("Login");
    loginBtn.setBounds(130, 130, 120, 30);
    frame.add(loginBtn);

    JButton exitBtn = new JButton("Exit");
    exitBtn.setBounds(130, 180, 120, 30);
    frame.add(exitBtn);

    exitBtn.addActionListener(e -> System.exit(0));
    registerBtn.addActionListener(e -> showRegisterScreen());
    loginBtn.addActionListener(e -> showLoginScreen());
    }

    void showRegisterScreen() {
    clearFrame();

    JLabel title = new JLabel("Create New Account");
    title.setBounds(120, 20, 200, 30);
    frame.add(title);

    JLabel nameLbl = new JLabel("Name:");
    nameLbl.setBounds(50, 70, 100, 25);
    frame.add(nameLbl);

    JTextField nameField = new JTextField();
    nameField.setBounds(150, 70, 180, 25);
    frame.add(nameField);

    JLabel accLbl = new JLabel("Account No:");
    accLbl.setBounds(50, 110, 100, 25);
    frame.add(accLbl);

    JTextField accField = new JTextField();
    accField.setBounds(150, 110, 180, 25);
    frame.add(accField);

    JLabel balLbl = new JLabel("Initial Balance:");
    balLbl.setBounds(50, 150, 100, 25);
    frame.add(balLbl);

    JTextField balField = new JTextField();
    balField.setBounds(150, 150, 180, 25);
    frame.add(balField);

    JLabel pinLbl = new JLabel("PIN:");
    pinLbl.setBounds(50, 190, 100, 25);
    frame.add(pinLbl);

    JPasswordField pinField = new JPasswordField();
    pinField.setBounds(150, 190, 180, 25);
    frame.add(pinField);

    JButton createBtn = new JButton("Create Account");
    createBtn.setBounds(120, 230, 160, 30);
    frame.add(createBtn);

    JButton back = new JButton("Back");
    back.setBounds(10, 10, 80, 25);
    frame.add(back);

    back.addActionListener(e -> showWelcomeScreen());

    createBtn.addActionListener(e -> {
        String name = nameField.getText();
        String acc = accField.getText();
        int bal = Integer.parseInt(balField.getText());
        int pin = Integer.parseInt(new String(pinField.getPassword()));

        if (new ATM().register(acc, name, pin, bal)) {
            JOptionPane.showMessageDialog(frame, "Account created successfully!");
            showWelcomeScreen();
        } else {
            JOptionPane.showMessageDialog(frame, "Account creation failed!");
            }
        });
    }

    void showLoginScreen() {
    clearFrame();

    JLabel label = new JLabel("Login Screen");
    label.setBounds(140, 30, 200, 30);
    frame.add(label);

    JButton back = new JButton("Back");
    back.setBounds(140, 200, 100, 30);
    frame.add(back);

    back.addActionListener(e -> showWelcomeScreen());
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}
