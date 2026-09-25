import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceCalculatorGUI extends JFrame implements ActionListener {

    private JTextField initialBalanceField, transactionAmountField, updatedBalanceField;
    private JButton depositButton, withdrawButton, resetButton;
    private double currentBalance = 0.0;
    private boolean isInitialSet = false;

    public BankBalanceCalculatorGUI() {
        setTitle("Bank Balance Calculator");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel titleLabel = new JLabel("Bank Account Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Input Grid Layout
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        formPanel.add(new JLabel("Initial Balance ($):"));
        initialBalanceField = new JTextField();
        formPanel.add(initialBalanceField);

        formPanel.add(new JLabel("Transaction Amount ($):"));
        transactionAmountField = new JTextField();
        formPanel.add(transactionAmountField);

        formPanel.add(new JLabel("Current Balance ($):"));
        updatedBalanceField = new JTextField();
        updatedBalanceField.setEditable(false);
        updatedBalanceField.setFont(new Font("Segoe UI", Font.BOLD, 14));
        formPanel.add(updatedBalanceField);

        add(formPanel, BorderLayout.CENTER);

        // Transaction Control Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        depositButton = new JButton("Deposit (+)");
        withdrawButton = new JButton("Withdraw (-)");
        resetButton = new JButton("Reset");

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        resetButton.addActionListener(this);

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            initialBalanceField.setText("");
            transactionAmountField.setText("");
            updatedBalanceField.setText("");
            initialBalanceField.setEditable(true);
            currentBalance = 0.0;
            isInitialSet = false;
            return;
        }

        try {
            // Read initial balance on first transaction if not set yet
            if (!isInitialSet) {
                String initText = initialBalanceField.getText().trim();
                if (initText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter an initial balance.", "Input Missing", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                currentBalance = Double.parseDouble(initText);
                if (currentBalance < 0) {
                    JOptionPane.showMessageDialog(this, "Initial balance cannot be negative.", "Invalid Value", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                isInitialSet = true;
                initialBalanceField.setEditable(false); // Lock initial field once active
            }

            String transText = transactionAmountField.getText().trim();
            if (transText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a transaction amount.", "Input Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double amount = Double.parseDouble(transText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Transaction amount must be greater than zero.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Perform Deposit or Withdrawal
            if (e.getSource() == depositButton) {
                currentBalance += amount;
                JOptionPane.showMessageDialog(this, String.format("Successfully deposited $%.2f", amount), "Deposit Complete", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == withdrawButton) {
                if (amount > currentBalance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance for this withdrawal!", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                currentBalance -= amount;
                JOptionPane.showMessageDialog(this, String.format("Successfully withdrew $%.2f", amount), "Withdrawal Complete", JOptionPane.INFORMATION_MESSAGE);
            }

            // Update display
            updatedBalanceField.setText(String.format("%.2f", currentBalance));
            transactionAmountField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for amounts.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankBalanceCalculatorGUI().setVisible(true);
        });
    }
}