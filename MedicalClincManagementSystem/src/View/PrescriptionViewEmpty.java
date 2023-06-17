package View;

import Controller.Launcher;
import Model.MedicalAppointment;
import Model.Medicine;
import Model.Prescription;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a view for creating a prescription with empty fields.
 */
public class PrescriptionViewEmpty extends JFrame {

    public JTextField nameField;
    public JTextField dosageField;
    public JTextField reimbursedField;
    public JTextField patientSurnameField;
    public List<Medicine> medicinesToAdd;

    /**
     * Constructs a new instance of the PrescriptionViewEmpty.
     * Sets up the user interface components and event listeners.
     *
     * @param medicalAppointment the medical appointment associated with the prescription.
     */
    public PrescriptionViewEmpty(MedicalAppointment medicalAppointment) {
        setTitle("Medical Clinic Management System - Prescription");

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(1000, 350));
        setResizable(true);
        setLocationRelativeTo(null);

        medicinesToAdd = new ArrayList<>();

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Appointment name");
        nameField = new JTextField(15);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel dosageLabel = new JLabel("Dosage");
        dosageField = new JTextField(15);
        formPanel.add(dosageLabel);
        formPanel.add(dosageField);

        JLabel reimbursedLabel = new JLabel("Reimbursement");
        reimbursedField = new JTextField(15);
        formPanel.add(reimbursedLabel);
        formPanel.add(reimbursedField);

        JLabel patientSurnameLabel = new JLabel("Patient");
        patientSurnameField = new JTextField(15);
        formPanel.add(patientSurnameLabel);
        formPanel.add(patientSurnameField);

        getContentPane().add(formPanel, BorderLayout.NORTH);

        String[] columnNames = {"Medicine", "Producer", "Dosage", "Instructions"};
        AbstractTableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return Medicine.extent.size();
            }

            @Override
            public int getColumnCount() {
                return 4; // Number of columns
            }

            @Override
            public Object getValueAt(int row, int column) {
                Medicine medicine = Medicine.extent.get(row);
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

            public String getColumnName(int column) {
                return columnNames[column];
            }
        };

        AbstractTableModel model2 = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return medicinesToAdd.size();
            }

            @Override
            public int getColumnCount() {
                return 4; // Number of columns
            }

            @Override
            public Object getValueAt(int row, int column) {
                Medicine medicine = medicinesToAdd.get(row);
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

            public String getColumnName(int column) {
                return columnNames[column];
            }
        };

        JTable medicamentsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(medicamentsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available Medicaments"));
        scrollPane.setViewportBorder(new EmptyBorder(10, 10, 10, 10)); // Use setViewportBorder for scroll pane
        getContentPane().add(scrollPane, BorderLayout.EAST);

        JTable addedMedicamentsTable = new JTable(model2);
        JScrollPane addedScrollPane = new JScrollPane(addedMedicamentsTable);
        addedScrollPane.setBorder(BorderFactory.createTitledBorder("Selected Medicaments"));
        addedScrollPane.setViewportBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(addedScrollPane, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add misssing medicament");
        buttonPanel.add(addButton);

        JButton saveButton = new JButton("Confirm prescription");
        buttonPanel.add(saveButton);

        JButton backButton = new JButton("Cancel");
        buttonPanel.add(backButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalAppointmentView medicalAppointmentView = new MedicalAppointmentView();
                medicalAppointmentView.setVisible(true);
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineView medicineView = new MedicineView();
                medicineView.setVisible(true);
                dispose();
            }
        });

        medicamentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = medicamentsTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Medicine selectedMedicine = Medicine.extent.get(selectedRow);
                        medicinesToAdd.add(selectedMedicine);
                        model2.fireTableDataChanged();
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dosageTemp;
                float reimbursedTemp;

                if(dosageField.getText() == null) { dosageTemp = "n/a"; }
                else { dosageTemp = dosageField.getText(); }
                if(reimbursedField.getText() == null || reimbursedField.getText().isEmpty()) { reimbursedTemp = 0.0f; }
                else { reimbursedTemp = Float.parseFloat(reimbursedField.getText()); }

                medicalAppointment.setPrescription(
                        new Prescription(
                                dosageTemp,
                                reimbursedTemp,
                                medicalAppointment
                        )
                );
                for(Medicine medicineToAdd : medicinesToAdd) {
                    medicalAppointment.getPrescription().AddMedicine(medicineToAdd);
                }
                Launcher.saveExtends();
                MedicalAppointmentView medicalAppointmentView = new MedicalAppointmentView();
                medicalAppointmentView.setVisible(true);
                dispose();
            }
        });
    }

}
