package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

/**
 * Represents the start view of the Medical Clinic Management System.
 * This view provides buttons to manage patients, doctors, and appointments.
 */
public class StartView extends JFrame {
    JButton patientsButton;
    JButton appointmentButton;
    JButton doctorsButton;

    /**
     * Constructs a new instance of the StartView.
     * Initializes the view and sets up the user interface components.
     */
    public StartView() {
        initializeView();
    }

    /**
     * Initializes the start view by setting the title, size, and layout.
     * Creates the main panel, logo label, and button panel.
     * Adds event listeners to the buttons.
     */
    public void initializeView() {
        setTitle("Medical Clinic Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());


        ImageIcon logoIcon = new ImageIcon("src/Data/logo.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(logoImage);
        Image transparentLogoImage = makeImageTransparent(resizedLogoIcon.getImage());
        ImageIcon transparentLogoIcon = new ImageIcon(transparentLogoImage);

        JLabel logoLabel = new JLabel(transparentLogoIcon);
        mainPanel.add(logoLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        patientsButton = new JButton("Manage Patients");
        patientsButton.setEnabled(false);
        patientsButton.setPreferredSize(new Dimension(200, 60));
        buttonPanel.add(patientsButton);

        doctorsButton = new JButton("Manage Doctors");
        doctorsButton.setEnabled(false);
        doctorsButton.setPreferredSize(new Dimension(200, 60));
        buttonPanel.add(doctorsButton);

        appointmentButton = new JButton("Manage Appointments");
        appointmentButton.setPreferredSize(new Dimension(200, 60));
        buttonPanel.add(appointmentButton);

        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalAppointmentView medicalAppointmentView = new MedicalAppointmentView();
                medicalAppointmentView.setVisible(true);
                dispose();
            }
        });

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Makes the provided image transparent by removing the white background color.
     *
     * @param image the image to make transparent.
     * @return the transparent image.
     */
    private Image makeImageTransparent(Image image) {
        ImageFilter filter = new RGBImageFilter() {
            public int markerRGB = Color.white.getRGB() | 0xFF000000;

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}