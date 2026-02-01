import javax.swing.*;

public class ATMGUI {

    JFrame frame;
    ATM atm;

    public ATMGUI() {
        frame = new JFrame("ATM Simulation");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        atm = new ATM(); // SINGLE shared ATM object

        showWelcomeScreen();
        frame.setVisible(true);
    }

    void clearFrame() {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }

    // ---------------- WELCOME ----------------
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

        registerBtn.addActionListener(e -> showRegisterScreen());
        loginBtn.addActionListener(e -> showLoginScreen());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    // ---------------- REGISTER ----------------
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

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(10, 10, 80, 25);
        frame.add(backBtn);

        backBtn.addActionListener(e -> showWelcomeScreen());

        createBtn.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String acc = accField.getText().trim();
                int bal = Integer.parseInt(balField.getText().trim());
                int pin = Integer.parseInt(new String(pinField.getPassword()));

                if (atm.register(acc, name, pin, bal)) {
                    JOptionPane.showMessageDialog(frame, "Account created successfully!");
                    showWelcomeScreen();
                } else {
                    JOptionPane.showMessageDialog(frame, "Account already exists!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });
    }

    // ---------------- LOGIN ----------------
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
            try {
                String acc = accField.getText().trim();
                int pin = Integer.parseInt(new String(pinField.getPassword()));

                if (atm.login(acc, pin)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    showDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid account or PIN!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });
    }

    // ---------------- DASHBOARD ----------------
    void showDashboard() {
        clearFrame();

        JLabel title = new JLabel("Welcome, " + atm.userName);
        title.setBounds(110, 30, 250, 30);
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
            JPasswordField pinField = new JPasswordField();

int option = JOptionPane.showConfirmDialog(
        frame,
        pinField,
        "Enter PIN to view balance",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
);

if (option != JOptionPane.OK_OPTION) return;

int pin;
try {
    pin = Integer.parseInt(new String(pinField.getPassword()));
} catch (Exception ex) {
    JOptionPane.showMessageDialog(frame, "Invalid PIN!");
    return;
}

if (atm.verifyPin(pin)) {
    JOptionPane.showMessageDialog(
            frame,
            "Current Balance: " + atm.bal,
            "Balance",
            JOptionPane.INFORMATION_MESSAGE
    );
} else {
    JOptionPane.showMessageDialog(
            frame,
            "Incorrect PIN!",
            "Access Denied",
            JOptionPane.ERROR_MESSAGE
    );
}

        });

        depBtn.addActionListener(e -> showDepositScreen());
        witBtn.addActionListener(e -> showWithdrawScreen());
        logoutBtn.addActionListener(e -> showWelcomeScreen());
    }

    // ---------------- DEPOSIT ----------------
    void showDepositScreen() {
        clearFrame();

        JLabel title = new JLabel("Deposit Money");
        title.setBounds(140, 30, 200, 30);
        frame.add(title);


        JLabel amtLbl = new JLabel("Amount:");
        amtLbl.setBounds(50, 80, 100, 25);
        frame.add(amtLbl);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 80, 180, 25);
        frame.add(amountField);

        JLabel pinLbl = new JLabel("PIN:");
        pinLbl.setBounds(50, 120, 100, 25);
        frame.add(pinLbl);

        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(150, 120, 180, 25);
        frame.add(pinField);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(130, 170, 120, 30);
        frame.add(depositBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(10, 10, 80, 25);
        frame.add(backBtn);

        backBtn.addActionListener(e -> showDashboard());

        depositBtn.addActionListener(e -> {
            try {
                int amt = Integer.parseInt(amountField.getText());
                int pin = Integer.parseInt(new String(pinField.getPassword()));

                if (!atm.verifyPin(pin)) {
                    JOptionPane.showMessageDialog(frame, "Incorrect PIN!");
                    return;
                }

                if (atm.deposit(amt)) {
                    JOptionPane.showMessageDialog(frame, "Deposit Successful!");
                    showDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Deposit Failed!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });
    }

    // ---------------- WITHDRAW ----------------
    void showWithdrawScreen() {
        clearFrame();

        JLabel title = new JLabel("Deposit Money");
        title.setBounds(140, 30, 200, 30);
        frame.add(title);

        JLabel amtLbl = new JLabel("Amount:");
        amtLbl.setBounds(50, 80, 100, 25);
        frame.add(amtLbl);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 80, 180, 25);
        frame.add(amountField);

        JLabel pinLbl = new JLabel("PIN:");
        pinLbl.setBounds(50, 120, 100, 25);
        frame.add(pinLbl);

        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(150, 120, 180, 25);
        frame.add(pinField);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(130, 170, 120, 30);
        frame.add(withdrawBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(10, 10, 80, 25);
        frame.add(backBtn);

        backBtn.addActionListener(e -> showDashboard());

        withdrawBtn.addActionListener(e -> {
            try {
                int amt = Integer.parseInt(amountField.getText());
                int pin = Integer.parseInt(new String(pinField.getPassword()));

                if (!atm.verifyPin(pin)) {
                    JOptionPane.showMessageDialog(frame, "Incorrect PIN!");
                    return;
                }

                if (atm.withdraw(amt)) {
                    JOptionPane.showMessageDialog(frame, "Withdrawal Successful!");
                    showDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}