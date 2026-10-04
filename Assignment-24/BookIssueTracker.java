import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BookIssueTracker extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String DB_USER = "root"; // Update username
    private static final String DB_PASS = "Pass"; // Update password

    private JTextField txtIssueId, txtBookId, txtStudentName, txtIssueDate, txtReturnDate;
    private JTable issueTable;
    private DefaultTableModel tableModel;

    public BookIssueTracker() {
        setTitle("Book Issue Tracking System");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Issue Record Details"));

        formPanel.add(new JLabel("Issue ID (Select/Read-only):"));
        txtIssueId = new JTextField();
        txtIssueId.setEditable(false);
        formPanel.add(txtIssueId);

        formPanel.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        formPanel.add(txtBookId);

        formPanel.add(new JLabel("Student Name:"));
        txtStudentName = new JTextField();
        formPanel.add(txtStudentName);

        formPanel.add(new JLabel("Issue Date (YYYY-MM-DD):"));
        txtIssueDate = new JTextField();
        formPanel.add(txtIssueDate);

        formPanel.add(new JLabel("Return Date (YYYY-MM-DD):"));
        txtReturnDate = new JTextField();
        formPanel.add(txtReturnDate);

        JButton btnAddRecord = new JButton("Add Record");
        JButton btnUpdateRecord = new JButton("Update Record");
        formPanel.add(btnAddRecord);
        formPanel.add(btnUpdateRecord);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"Issue ID", "Book ID", "Student Name", "Issue Date", "Return Date"}, 0);
        issueTable = new JTable(tableModel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnDeleteRecord = new JButton("Delete Record");
        JButton btnClear = new JButton("Clear Selection");
        buttonPanel.add(btnDeleteRecord);
        buttonPanel.add(btnClear);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(issueTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        issueTable.getSelectionModel().addListSelectionListener(e -> populateFields());
        btnAddRecord.addActionListener(e -> addRecord());
        btnUpdateRecord.addActionListener(e -> updateRecord());
        btnDeleteRecord.addActionListener(e -> deleteRecord());
        btnClear.addActionListener(e -> clearForm());

        loadRecords();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private void loadRecords() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM book_issues";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Date retDate = rs.getDate("return_date");
                tableModel.addRow(new Object[]{
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getString("student_name"),
                        rs.getDate("issue_date"),
                        retDate != null ? retDate : "Pending Return"
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addRecord() {
        String bookIdText = txtBookId.getText().trim();
        String studentName = txtStudentName.getText().trim();
        String issueDate = txtIssueDate.getText().trim();
        String returnDate = txtReturnDate.getText().trim();

        if (bookIdText.isEmpty() || studentName.isEmpty() || issueDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Book ID, Student Name, and Issue Date are required.");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdText);
            String sql = "INSERT INTO book_issues (book_id, student_name, issue_date, return_date) VALUES (?, ?, ?, ?)";

            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, bookId);
                pstmt.setString(2, studentName);
                pstmt.setDate(3, Date.valueOf(issueDate));

                if (returnDate.isEmpty()) {
                    pstmt.setNull(4, Types.DATE);
                } else {
                    pstmt.setDate(4, Date.valueOf(returnDate));
                }

                pstmt.executeUpdate();
                clearForm();
                loadRecords();
                JOptionPane.showMessageDialog(this, "Issue record added successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID must be a numeric value.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Dates must follow the YYYY-MM-DD format.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    private void updateRecord() {
        if (txtIssueId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a record from the table to update.");
            return;
        }

        int issueId = Integer.parseInt(txtIssueId.getText());
        int bookId = Integer.parseInt(txtBookId.getText().trim());
        String studentName = txtStudentName.getText().trim();
        String issueDate = txtIssueDate.getText().trim();
        String returnDate = txtReturnDate.getText().trim();

        String sql = "UPDATE book_issues SET book_id = ?, student_name = ?, issue_date = ?, return_date = ? WHERE issue_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookId);
            pstmt.setString(2, studentName);
            pstmt.setDate(3, Date.valueOf(issueDate));

            if (returnDate.isEmpty() || "Pending Return".equalsIgnoreCase(returnDate)) {
                pstmt.setNull(4, Types.DATE);
            } else {
                pstmt.setDate(4, Date.valueOf(returnDate));
            }

            pstmt.setInt(5, issueId);
            pstmt.executeUpdate();

            clearForm();
            loadRecords();
            JOptionPane.showMessageDialog(this, "Record updated successfully!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Dates must follow the YYYY-MM-DD format.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    private void deleteRecord() {
        int selectedRow = issueTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a record to delete.");
            return;
        }

        int issueId = (int) tableModel.getValueAt(selectedRow, 0);
        String sql = "DELETE FROM book_issues WHERE issue_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, issueId);
            pstmt.executeUpdate();

            clearForm();
            loadRecords();
            JOptionPane.showMessageDialog(this, "Record deleted successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    private void populateFields() {
        int selectedRow = issueTable.getSelectedRow();
        if (selectedRow != -1) {
            txtIssueId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtBookId.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtStudentName.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtIssueDate.setText(tableModel.getValueAt(selectedRow, 3).toString());

            Object ret = tableModel.getValueAt(selectedRow, 4);
            txtReturnDate.setText("Pending Return".equals(ret) ? "" : ret.toString());
        }
    }

    private void clearForm() {
        txtIssueId.setText("");
        txtBookId.setText("");
        txtStudentName.setText("");
        txtIssueDate.setText("");
        txtReturnDate.setText("");
        issueTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookIssueTracker().setVisible(true));
    }
}