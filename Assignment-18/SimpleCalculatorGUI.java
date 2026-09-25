import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorGUI extends JFrame implements ActionListener {

    private JTextField num1Field, num2Field, resultField;
    private JButton addButton, subButton, clearButton;

    public SimpleCalculatorGUI() {
        setTitle("Addition & Subtraction Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header Title
        JLabel titleLabel = new JLabel("Simple Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Input & Output Panel
        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        gridPanel.add(new JLabel("First Number:"));
        num1Field = new JTextField();
        gridPanel.add(num1Field);

        gridPanel.add(new JLabel("Second Number:"));
        num2Field = new JTextField();
        gridPanel.add(num2Field);

        gridPanel.add(new JLabel("Result:"));
        resultField = new JTextField();
        resultField.setEditable(false); // Make result field read-only
        gridPanel.add(resultField);

        add(gridPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        addButton = new JButton("+ Add");
        subButton = new JButton("- Subtract");
        clearButton = new JButton("Clear");

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            num1Field.setText("");
            num2Field.setText("");
            resultField.setText("");
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Field.getText().trim());
            double num2 = Double.parseDouble(num2Field.getText().trim());
            double result = 0;

            if (e.getSource() == addButton) {
                result = num1 + num2;
            } else if (e.getSource() == subButton) {
                result = num1 - num2;
            }

            resultField.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, 
                "Please enter valid numeric values.", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleCalculatorGUI().setVisible(true);
        });
    }
}