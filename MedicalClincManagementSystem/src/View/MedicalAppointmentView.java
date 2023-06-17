package View;

import Model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Represents a view for displaying a list of medical appointments.
 */
public class MedicalAppointmentView extends JFrame {
    JButton returnToStartButton;
    JButton editAppointmentButton;
    JButton addAppointmentButton;
    JButton editPrescriptionButton;
    int selectedRow;

    /**
     * Constructs a new instance of the MedicalAppointmentView.
     * Sets up the user interface components and event listeners.
     */
    public MedicalAppointmentView() {
        setTitle("Medical Clinic Management System - Appointment List");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(700, 400));
        setResizable(true);
        setLocationRelativeTo(null);

        String[] columnNames = {"Appointment name", "Date", "Remotely", "Cost", "Patient"};
        AbstractTableModel model = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return MedicalAppointment.extent.size();
            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public Object getValueAt(int row, int column) {
                MedicalAppointment medicalAppointment = MedicalAppointment.extent.get(row);
                switch (column) {
                    case 0:
                        return medicalAppointment.getName();
                    case 1:
                        return medicalAppointment.getDateOfAppointment();
                    case 2:
                        return medicalAppointment.isRemotely();
                    case 3:
                        return medicalAppointment.getCost();
                    case 4:
                        return medicalAppointment.getReservation().getPatient();
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
        JTable appointmentTable = new JTable(model);

        appointmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectedRow = -1;
        appointmentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow = appointmentTable.getSelectedRow();
                    editPrescriptionButton.setVisible(true);
                    if(MedicalAppointment.extent.get(selectedRow).getPrescription() == null) {
                        editPrescriptionButton.setText("Add prescription");
                    } else {
                        editPrescriptionButton.setText("Show prescription");
                    }
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addAppointmentButton = new JButton("Add new appointment");
        addAppointmentButton.setEnabled(false);
        buttonPanel.add(addAppointmentButton);

        editAppointmentButton = new JButton("Edit selected appointment");
        editAppointmentButton.setEnabled(false);
        buttonPanel.add(editAppointmentButton);

        editPrescriptionButton = new JButton("Prescription");
        editPrescriptionButton.setVisible(false);
        buttonPanel.add(editPrescriptionButton);

        returnToStartButton = new JButton("Back");
        buttonPanel.add(returnToStartButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        ActionListener editPrescriptionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(editPrescriptionButton.getText().equals("Show prescription")) {
                    PrescriptionView prescriptionView = new PrescriptionView(MedicalAppointment.extent.get(selectedRow).getPrescription().getMedicines());
                    prescriptionView.nameField.setText(MedicalAppointment.extent.get(selectedRow).getName());
                    prescriptionView.dosageField.setText(MedicalAppointment.extent.get(selectedRow).getPrescription().getDosage());
                    prescriptionView.reimbursedField.setText(String.valueOf(MedicalAppointment.extent.get(selectedRow).getPrescription().getReimbursed()));
                    prescriptionView.patientSurnameField.setText(MedicalAppointment.extent.get(selectedRow).getReservation().getPatient().toString());
                    prescriptionView.setVisible(true);
                }
                else {
                    PrescriptionViewEmpty prescriptionView = new PrescriptionViewEmpty(MedicalAppointment.extent.get(selectedRow));
                    prescriptionView.nameField.setText(MedicalAppointment.extent.get(selectedRow).getName());
                    prescriptionView.patientSurnameField.setText(MedicalAppointment.extent.get(selectedRow).getReservation().getPatient().toString());
                    prescriptionView.setVisible(true);
                    dispose();
                }

            }
        };
        editPrescriptionButton.addActionListener(editPrescriptionListener);

        returnToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartView startView = new StartView();
                startView.setVisible(true);
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);
    }
}