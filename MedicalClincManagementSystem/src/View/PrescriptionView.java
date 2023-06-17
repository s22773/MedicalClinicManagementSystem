package View;

import Model.Medicine;
import Model.Prescription;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a view for managing prescriptions.
 */
public class PrescriptionView extends JFrame {
    public JTextField nameField;
    public JTextField dosageField;
    public JTextField reimbursedField;
    public JTextField patientSurnameField;

    /**
     * Constructs a new instance of the PrescriptionView.
     * Sets up the user interface components and event listeners.
     *
     * @param medicines the list of medicines to display in the prescription.
     */
    public PrescriptionView(List<Medicine> medicines) {
        setTitle("Medical Clinic Management System - Prescription");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(700, 400));
        setResizable(true);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Appointment name:");
        nameField = new JTextField(20);
        nameField.setEnabled(false);
        formPanel.add(nameLabel, gbc);
        gbc.gridx++;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel dosageLabel = new JLabel("Dosage:");
        dosageField = new JTextField(20);
        dosageField.setEnabled(false);
        formPanel.add(dosageLabel, gbc);
        gbc.gridx++;
        formPanel.add(dosageField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel reimbursedLabel = new JLabel("Reimbursement:");
        reimbursedField = new JTextField(20);
        reimbursedField.setEnabled(false);
        formPanel.add(reimbursedLabel, gbc);
        gbc.gridx++;
        formPanel.add(reimbursedField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel patientSurnameLabel = new JLabel("Patient:");
        patientSurnameField = new JTextField(20);
        patientSurnameField.setEnabled(false);
        formPanel.add(patientSurnameLabel, gbc);
        gbc.gridx++;
        formPanel.add(patientSurnameField, gbc);

        String[] columnNames = {"Medicine", "Manufacturer", "Dosage", "Instructions"};
        AbstractTableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                if (medicines != null)
                    return medicines.size();
                else
                    return 0;
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int row, int column) {
                Medicine medicine;
                if (medicines != null) {
                    medicine = medicines.get(row);
                } else {
                    medicine = Medicine.extent.get(row);
                }

                switch (column) {
                    case 0:
                        return medicine.getName();
                    case 1:
                        return medicine.getProducent();
                    case 2:
                        return medicine.getDose();
                    case 3:
                        return medicine.getInstruction();
                    default:
                        return null;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public String getColumnName(int column) {
                return columnNames[column];
            }
        };

        JTable medicamentsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(medicamentsTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton printButton = new JButton("Print");
        printButton.setEnabled(false);
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(printButton);
        buttonPanel.add(backButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}