package View;

import Controller.Launcher;
import Model.Medicine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents a view for adding a new medicament.
 */
public class MedicineView extends JFrame {
    private JTextField nameField;
    private JTextField producentField;
    private JTextField doseField;
    private JTextField instructionField;
    private JPanel outerPanel;
    private JPanel innerPanel;

    /**
     * Constructs a new instance of the MedicineView.
     * Sets up the user interface components and event listeners.
     */
    public MedicineView() {
        setTitle("Medical Clinic Management System - Adding new medicament");
        setLayout(new GridLayout(2, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(700, 400));
        setResizable(true);
        setLocationRelativeTo(null);

        outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        outerPanel.add(createPaddedComponent(nameLabel));
        outerPanel.add(createPaddedComponent(nameField));

        JLabel producentLabel = new JLabel("Producent:");
        producentField = new JTextField(10);
        outerPanel.add(createPaddedComponent(producentLabel));
        outerPanel.add(createPaddedComponent(producentField));

        JLabel doseLabel = new JLabel("Dose:");
        doseField = new JTextField(10);
        outerPanel.add(createPaddedComponent(doseLabel));
        outerPanel.add(createPaddedComponent(doseField));

        JLabel instructionLabel = new JLabel("Instructions:");
        instructionField = new JTextField(20);
        outerPanel.add(createPaddedComponent(instructionLabel));
        outerPanel.add(createPaddedComponent(instructionField));

        outerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        add(outerPanel);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));
        innerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JButton saveMedicament = new JButton("Save");
        saveMedicament.setMaximumSize(new Dimension(350, 50));
        innerPanel.add(Box.createHorizontalGlue());
        innerPanel.add(saveMedicament);

        JButton cancelAddingMedicament = new JButton("Cancel");
        cancelAddingMedicament.setMaximumSize(new Dimension(150, 50));
        innerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        innerPanel.add(cancelAddingMedicament);

        saveMedicament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medicine medicine = new Medicine(
                        nameField.getText(),
                        producentField.getText(),
                        doseField.getText(),
                        instructionField.getText()
                );
                Launcher.saveExtends();
                MedicalAppointmentView medicalAppointmentView = new MedicalAppointmentView();
                medicalAppointmentView.setVisible(true);
                dispose();
            }
        });

        cancelAddingMedicament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalAppointmentView medicalAppointmentView = new MedicalAppointmentView();
                medicalAppointmentView.setVisible(true);
                dispose();
            }
        });
        add(innerPanel);
    }

    /**
     * Creates a padded panel to contain the provided component.
     *
     * @param component the component to be contained in the panel.
     * @return the padded panel containing the component.
     */
    private JPanel createPaddedComponent(JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(component);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        return panel;
    }
}
