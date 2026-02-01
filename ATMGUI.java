import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    String name = nameField.getText().trim();
    String acc = accField.getText().trim();
    String balText = balField.getText().trim();
    String pinText = new String(pinField.getPassword()).trim();

    // 1. Empty field check
    if (name.isEmpty() || acc.isEmpty() || balText.isEmpty() || pinText.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "All fields are required!");
        return;
    }

    // 2. Numeric check
    int bal;
    int pin;
    try {
        bal = Integer.parseInt(balText);
        pin = Integer.parseInt(pinText);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "Balance and PIN must be numbers!");
        return;
    }

    // 3. PIN length check
    if (pinText.length() != 4) {
        JOptionPane.showMessageDialog(frame, "PIN must be exactly 4 digits!");
        return;
    }

    // 4. Balance sanity check
    if (bal < 0) {
        JOptionPane.showMessageDialog(frame, "Balance cannot be negative!");
        return;
    }

    // 5. Call backend
    ATM atm = new ATM();
    boolean success = atm.register(acc, name, pin, bal);

    if (success) {
        JOptionPane.showMessageDialog(frame, "Account created successfully!");
        showWelcomeScreen();
    } else {
        JOptionPane.showMessageDialog(frame, "Account already exists or error occurred.");
    }
});

    }

    void showLoginScreen() {
    clearFrame();

    JLabel title = new JLabel("Login");
    title.setBounds(160, 30, 100, 30);
    frame.add(title);

    JLabel accLbl = new JLabel("Account No:");
    accLbl.setBounds(60, 80, 100, 25);
    frame.add(accLbl);

    JTextField accField = new JTextField();
    accField.setBounds(160, 80, 160, 25);
    frame.add(accField);

    JLabel pinLbl = new JLabel("PIN:");
    pinLbl.setBounds(60, 120, 100, 25);
    frame.add(pinLbl);

    JPasswordField pinField = new JPasswordField();
    pinField.setBounds(160, 120, 160, 25);
    frame.add(pinField);

    JButton loginBtn = new JButton("Login");
    loginBtn.setBounds(140, 170, 100, 30);
    frame.add(loginBtn);

    JButton backBtn = new JButton("Back");
    backBtn.setBounds(10, 10, 80, 25);
    frame.add(backBtn);

    backBtn.addActionListener(e -> showWelcomeScreen());

    loginBtn.addActionListener(e -> {

        String acc = accField.getText().trim();
        String pinText = new String(pinField.getPassword()).trim();

        if (acc.isEmpty() || pinText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required!");
            return;
        }

        int pin;
        try {
            pin = Integer.parseInt(pinText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "PIN must be numeric!");
            return;
        }

        ATM atm = new ATM();
        if (atm.login(acc, pin)) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            showDashboard(atm);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid account number or PIN!");
        }
    });
    }

    void showDashboard(ATM atm) {
    clearFrame();

    JLabel title = new JLabel("ATM Dashboard");
    title.setBounds(140, 30, 200, 30);
    frame.add(title);

    JButton balBtn = new JButton("Check Balance");
    balBtn.setBounds(120, 80, 160, 30);
    frame.add(balBtn);

    JButton depBtn = new JButton("Deposit");
    depBtn.setBounds(120, 120, 160, 30);
    frame.add(depBtn);

    JButton witBtn = new JButton("Withdraw");
    witBtn.setBounds(120, 160, 160, 30);
    frame.add(witBtn);

    JButton logoutBtn = new JButton("Logout");
    logoutBtn.setBounds(120, 200, 160, 30);
    frame.add(logoutBtn);

    balBtn.addActionListener(e -> {

    String pinInput = JOptionPane.showInputDialog(
            frame,
            "Enter PIN to view balance:"
    );

    if (pinInput == null) {
        return; // user pressed cancel
    }

    int pin;
    try {
        pin = Integer.parseInt(pinInput);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "PIN must be numeric!");
        return;
    }

    if (atm.verifyPin(pin)) {
        JOptionPane.showMessageDialog(
                frame,
                "Current Balance: " + atm.bal
        );
    } else {
        JOptionPane.showMessageDialog(
                frame,
                "Incorrect PIN! Access denied."
        );
    }
});


    depBtn.addActionListener(e -> showDepositScreen(atm));
    witBtn.addActionListener(e -> showWithdrawScreen(atm));
    logoutBtn.addActionListener(e -> showWelcomeScreen());
}

void showDepositScreen(ATM atm) {
    clearFrame();

    JLabel title = new JLabel("Deposit");
    title.setBounds(160, 30, 100, 30);
    frame.add(title);

    JButton backBtn = new JButton("Back");
    backBtn.setBounds(10, 10, 80, 25);
    frame.add(backBtn);

    backBtn.addActionListener(e -> showDashboard(atm));
}

void showWithdrawScreen(ATM atm) {
    clearFrame();

    JLabel title = new JLabel("Withdraw");
    title.setBounds(160, 30, 100, 30);
    frame.add(title);

    JButton backBtn = new JButton("Back");
    backBtn.setBounds(10, 10, 80, 25);
    frame.add(backBtn);

    backBtn.addActionListener(e -> showDashboard(atm));
}

    public static void main(String[] args) {
        new ATMGUI();
    }
}
