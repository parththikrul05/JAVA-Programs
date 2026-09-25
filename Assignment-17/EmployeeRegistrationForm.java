import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeRegistrationForm extends JFrame implements ActionListener {

    // Components
    private JTextField empIdField, nameField, deptField, salaryField;
    private JButton registerButton, resetButton;

    public EmployeeRegistrationForm() {
        // Window frame configuration
        setTitle("Employee Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form Title
        JLabel titleLabel = new JLabel("Employee Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Input Grid Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        inputPanel.add(new JLabel("Employee ID:"));
        empIdField = new JTextField();
        inputPanel.add(empIdField);

        inputPanel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Department:"));
        deptField = new JTextField();
        inputPanel.add(deptField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);

        add(inputPanel, BorderLayout.CENTER);

        // Control Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register");
        resetButton = new JButton("Reset");

        registerButton.addActionListener(this);
        resetButton.addActionListener(this);

        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String empId = empIdField.getText().trim();
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            String salary = salaryField.getText().trim();

            // Simple validation check
            if (empId.isEmpty() || name.isEmpty() || dept.isEmpty() || salary.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill in all input fields.", 
                    "Input Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Display submitted information in a dialog box
            String outputMessage = String.format(
                "--- Employee Details Registered ---\n\n" +
                "ID: %s\n" +
                "Name: %s\n" +
                "Department: %s\n" +
                "Salary: $ %s",
                empId, name, dept, salary
            );

            JOptionPane.showMessageDialog(
                this, 
                outputMessage, 
                "Registration Summary", 
                JOptionPane.INFORMATION_MESSAGE
            );

        } else if (e.getSource() == resetButton) {
            empIdField.setText("");
            nameField.setText("");
            deptField.setText("");
            salaryField.setText("");
        }
    }

    public static void main(String[] args) {
        // Execute UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new EmployeeRegistrationForm().setVisible(true);
        });
    }
}