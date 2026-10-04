import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class LibraryManagementSystem extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String DB_USER = "root"; // Update username
    private static final String DB_PASS = "Pass"; // Update password

    private JTextField txtId, txtTitle, txtAuthor, txtPublisher, txtQuantity;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        formPanel.add(new JLabel("Book ID (Auto-generated/Select):"));
        txtId = new JTextField();
        txtId.setEditable(false);
        formPanel.add(txtId);

        formPanel.add(new JLabel("Book Title:"));
        txtTitle = new JTextField();
        formPanel.add(txtTitle);

        formPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        formPanel.add(txtAuthor);

        formPanel.add(new JLabel("Publisher:"));
        txtPublisher = new JTextField();
        formPanel.add(txtPublisher);

        formPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        formPanel.add(txtQuantity);

        JButton btnAdd = new JButton("Add Book");
        JButton btnUpdate = new JButton("Update Book");
        formPanel.add(btnAdd);
        formPanel.add(btnUpdate);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Publisher", "Quantity"}, 0);
        bookTable = new JTable(tableModel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnDelete = new JButton("Delete Book");
        JButton btnClear = new JButton("Clear Form");
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handling
        bookTable.getSelectionModel().addListSelectionListener(e -> populateFields());
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnClear.addActionListener(e -> clearForm());

        loadBooks();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getInt("quantity")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addBook() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String publisher = txtPublisher.getText().trim();
        String qtyText = txtQuantity.getText().trim();

        if (title.isEmpty() || author.isEmpty() || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title, Author, and Quantity are required.");
            return;
        }

        try {
            int quantity = Integer.parseInt(qtyText);
            String sql = "INSERT INTO books (title, author, publisher, quantity) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, title);
                pstmt.setString(2, author);
                pstmt.setString(3, publisher);
                pstmt.setInt(4, quantity);
                pstmt.executeUpdate();
                
                clearForm();
                loadBooks();
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be an integer.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage());
        }
    }

    private void updateBook() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a book from the table to update.");
            return;
        }

        int bookId = Integer.parseInt(txtId.getText());
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String publisher = txtPublisher.getText().trim();
        int quantity = Integer.parseInt(txtQuantity.getText().trim());

        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, quantity = ? WHERE book_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, publisher);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, bookId);
            pstmt.executeUpdate();

            clearForm();
            loadBooks();
            JOptionPane.showMessageDialog(this, "Book updated successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating book: " + ex.getMessage());
        }
    }

    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a book to delete.");
            return;
        }

        int bookId = (int) tableModel.getValueAt(selectedRow, 0);
        String sql = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();

            clearForm();
            loadBooks();
            JOptionPane.showMessageDialog(this, "Book deleted successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting book: " + ex.getMessage());
        }
    }

    private void populateFields() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtTitle.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtAuthor.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtPublisher.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtQuantity.setText(tableModel.getValueAt(selectedRow, 4).toString());
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        txtQuantity.setText("");
        bookTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}