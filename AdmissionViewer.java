package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class AdmissionViewer extends JFrame {
    JTable table;
    DefaultTableModel model;

    public AdmissionViewer() {
        setTitle("Student Admission Records");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table));

        // First check login
        if (authorize()) {
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "Authorization failed! Closing app.");
            System.exit(0);
        }
    }

    /** Authorization function */
    private boolean authorize() {
        JPanel panel = new JPanel();
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        int result = JOptionPane.showConfirmDialog(
                null, panel, "Login Required", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            // ✅ Hardcoded check (you can also check against SQL users table)
            return username.equals("root") && password.equals("root");
        }
        return false;
    }

    /** Load student data */
    private void loadData() {
        String url = "jdbc:mysql://localhost:3306/admission"; // ✅ DB name
        String user = "root";  // ✅ MySQL username
        String password = "root"; // ✅ MySQL password

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            // Add column names
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Add rows
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdmissionViewer().setVisible(true));
    }
}