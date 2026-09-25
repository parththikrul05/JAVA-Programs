import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistrationForm extends JFrame implements ActionListener {
    
    // Components
    private JTextField nameField, rollField, emailField;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JComboBox courseCombo;
    private JTextArea addressArea;
    private JButton submitButton, clearButton;

    public StudentRegistrationForm() {
        // Frame setup
        setTitle("Student Registration Form");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // Title Header
        JLabel titleLabel = new JLabel("Student Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Main Input Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Name
        formPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        // Roll Number
        formPanel.add(new JLabel("Roll Number:"));
        rollField = new JTextField();
        formPanel.add(rollField);

        // Email
        formPanel.add(new JLabel("Email ID:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        // Gender
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);

        // Course Selection
        formPanel.add(new JLabel("Course:"));
        String[] courses = {"Computer Science", "Information Technology", "Mechanical", "Civil", "Electrical"};
        courseCombo = new JComboBox<>(courses);
        formPanel.add(courseCombo);

        // Address
        formPanel.add(new JLabel("Address:"));
        addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(addressArea);
        formPanel.add(scrollPane);

        add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String roll = rollField.getText();
            String email = emailField.getText();
            String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "Not Specified");
            String course = (String) courseCombo.getSelectedItem();
            String address = addressArea.getText();

            // Display confirmation dialog
            String details = String.format(
                "Registration Successful!\n\nName: %s\nRoll No: %s\nEmail: %s\nGender: %s\nCourse: %s\nAddress: %s",
                name, roll, email, gender, course, address
            );
            JOptionPane.showMessageDialog(this, details, "Student Details", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            rollField.setText("");
            emailField.setText("");
            genderGroup.clearSelection();
            courseCombo.setSelectedIndex(0);
            addressArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationForm().setVisible(true);
        });
    }
}