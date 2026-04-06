package com.crm.ui;

import com.crm.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CRMFrame extends JFrame {
    private List<Customer> customers = new ArrayList<>();
    private int nextId = 1;
    private DefaultTableModel tableModel;
    private JTextField nameField, emailField;

    public CRMFrame() {
        setTitle("CRM Professional Edition");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Set System Look and Feel for a "native" look
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}

        // 1. Table Panel (Center)
        String[] columns = {"ID", "Full Name", "Email Address"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        
        // 2. Input Panel (Bottom)
        JPanel inputPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        inputPanel.setBackground(new Color(245, 245, 245));

        nameField = new JTextField();
        emailField = new JTextField();
        JButton addButton = new JButton("Add Customer");
        JButton deleteButton = new JButton("Delete Selected");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(addButton);

        // 3. Add Listeners
        addButton.addActionListener(e -> addCustomer());
        deleteButton.addActionListener(e -> deleteCustomer(table));

        // Layout Assembly
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(deleteButton, BorderLayout.NORTH);
    }

    private void addCustomer() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (!name.isEmpty() && !email.isEmpty()) {
            Customer c = new Customer(nextId++, name, email);
            customers.add(c);
            tableModel.addRow(c.toArray());
            nameField.setText("");
            emailField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCustomer(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            customers.remove(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Select a customer to delete.");
        }
    }
}