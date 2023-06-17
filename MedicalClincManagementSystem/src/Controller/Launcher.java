package Controller;

import Model.*;
import View.StartView;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The Launcher class is responsible for saving and reading the extents of various classes and launching the application.
 */
public class Launcher {

    /**
     * Saves the extents of various classes to a file.
     */
    public static void saveExtends() {
        try {
            var out = new ObjectOutputStream(new FileOutputStream("src/Data/data.ser"));
            MedicalAppointment.writeExtent(out);
            Surgery.writeExtent(out);
            PreventiveVisit.writeExtent(out);
            Doctor.writeExtent(out);
            Specialization.writeExtent(out);
            MedicalExamination.writeExtent(out);
            Technician.writeExtent(out);
            Patient.writeExtent(out);
            Insurance.writeExtent(out);
            Address.writeExtent(out);
            Reservation.writeExtent(out);
            Facility.writeExtent(out);
            Medicine.writeExtent(out);
            Prescription.writeExtent(out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the extents of various classes from a file.
     */
    public static void readExtends() {
        try {
            var in = new ObjectInputStream(new FileInputStream("src/Data/data.ser"));
            MedicalAppointment.readExtent(in);
            Surgery.readExtent(in);
            PreventiveVisit.readExtent(in);
            Doctor.readExtent(in);
            Specialization.readExtent(in);
            MedicalExamination.readExtent(in);
            Technician.readExtent(in);
            Patient.readExtent(in);
            Insurance.readExtent(in);
            Address.readExtent(in);
            Reservation.readExtent(in);
            Facility.readExtent(in);
            Medicine.readExtent(in);
            Prescription.readExtent(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
//        Patient patient01 = new Patient("Adam", "Kowalski", Gender.MALE, LocalDate.of(1990, 5, 15), "1234567890");
//        Patient patient02 = new Patient("Ewa", "Nowak", Gender.FEMALE, LocalDate.of(1985, 10, 20), "9876543210");
//        Patient patient03 = new Patient("Michał", "Wójcik", Gender.MALE, LocalDate.of(1978, 12, 3), "5555555555");
//        Patient patient04 = new Patient("David", "Johnson", Gender.MALE, LocalDate.of(1978, 9, 25), "5555555555");
//        Patient patient05 = new Patient("Michael", "Brown", Gender.MALE, LocalDate.of(1982, 7, 14), "7777777777");
//        Patient patient06 = new Patient("Sophia", "Taylor", Gender.FEMALE, LocalDate.of(1988, 6, 9), "4444444444");
//        Patient patient07 = new Patient("Emma", "Thomas", Gender.FEMALE, LocalDate.of(1997, 2, 1), "1111111111");
//
//
//        Reservation reservation07 = new Reservation(LocalDate.of(2020, 6, 20), false, patient01);
//        Reservation reservation08 = new Reservation(LocalDate.of(2023, 10, 10), true, patient02);
//        Reservation reservation09 = new Reservation(LocalDate.of(2023, 11, 19), true, patient03);
//
//        Surgery surgery01 = new Surgery("Appendectomy", LocalDate.of(2023, 6, 15), false, 5000.0, reservation07,120, "General");
//        Surgery surgery02 = new Surgery("Knee Replacement", LocalDate.of(2023, 6, 20), false, 10000.0, reservation08,180, "Epidural");
//        Surgery surgery03 = new Surgery("Cataract Surgery", LocalDate.of(2023, 7, 5), true, 3000.0, reservation09,60, "Local");
//
//        Doctor doctor01 = new Doctor("John", "Smith", 5);
//        Doctor doctor02 = new Doctor("Emily", "Johnson", 10);
//        Doctor doctor03 = new Doctor("Michael", "Williams", 3);
//
//        doctor01.AddSurgery(surgery01);
//        doctor01.AddSurgery(surgery02);
//        surgery03.AddDoctor(doctor03);
//
//        Technician technician01 = new Technician(12345, Arrays.asList("Blood Test", "MRI Scan", "X-ray"));
//
//        Reservation reservation04 = new Reservation(LocalDate.of(2022, 8, 12), true, patient05);
//        Reservation reservation05 = new Reservation(LocalDate.of(2022, 3, 1), true, patient06);
//        Reservation reservation06 = new Reservation(LocalDate.of(2023, 1, 13), true, patient07);
//
//        MedicalExamination medicalExamination01 = new MedicalExamination("Blood Test", LocalDate.of(2023, 6, 15), 150.0, reservation04);
//        MedicalExamination medicalExamination02 = new MedicalExamination("MRI Scan", LocalDate.of(2023, 6, 15), 150.0, reservation05);
//        MedicalExamination medicalExamination03 = new MedicalExamination("Physical Examination", LocalDate.of(2023, 6, 15), 50.0, reservation06);
//
//        medicalExamination01.AddTechnician(technician01);
//        medicalExamination02.AddDoctor(doctor02);
//        medicalExamination02.AddDoctor(doctor03);
//        medicalExamination03.AddTechnician(technician01);
//
//        Insurance insurance01 = new Insurance(InsuranceType.EMPLOYMENT, 123456, "ABC Insurance", patient01);
//        Insurance insurance02 = new Insurance(InsuranceType.STUDENT, 789012, "XYZ Insurance");
//        Insurance insurance03 = new Insurance(InsuranceType.PRIVATE, 345678, "PQR Insurance");
//        Insurance insurance04 = new Insurance(InsuranceType.PUBLIC, 987654, "LMN Insurance");
//
//        patient01.AddInsurance(insurance02);
//        patient02.AddInsurance(insurance03);
//        patient03.AddInsurance(insurance04);
//
//        Address address01 = new Address("New York", "Broadway", "10001");
//        Address address02 = new Address("London", "Oxford Street", "W1D 1BS");
//        Address address03 = new Address("Paris", "Champs-Élysées", "75008");
//
//        patient02.AddAddress(address01);
//        address02.AddPatient(patient01);
//        patient03.AddAddress(address03);
//
//        Reservation reservation01 = new Reservation(LocalDate.of(2023, 6, 15), true, patient01);
//        Reservation reservation02 = new Reservation(LocalDate.of(2023, 7, 2), false, patient02);
//        Reservation reservation03 = new Reservation(LocalDate.of(2023, 8, 10), true, patient04);
//
//        patient03.AddReservation(reservation03);
//
//        Address address04 = new Address("Cityville", "Longslide", "12345");
//
//        Facility facility01 = new Facility("Medical Center", 101, true, address04);
//        facility01.AddMedicalAppointment(medicalExamination01);
//
//        Prescription prescription01 = new Prescription("1 pill twice daily", 75.0f, medicalExamination01);
//        Prescription prescription02 = new Prescription("Three times per day", 20.0f);
//        Prescription prescription03 = new Prescription("Until pain exists.", 0.0f);
//
//        Medicine medicine01 = new Medicine("Aspirin", "ABC Pharmaceuticals", "500mg", "Take one tablet with water every 4-6 hours.");
//        Medicine medicine02 = new Medicine("Amoxicillin", "XYZ Pharmaceuticals", "250mg", "Take one capsule three times a day for 7 days.");
//        Medicine medicine03 = new Medicine("Amoxicillin", "Pfizer", "875 mg", "Take with food");
//        Medicine medicine04 = new Medicine("Ibuprofen", "Generic Pharmacy", "400 mg", "Take after meals for pain relief");
//        Medicine medicine05 = new Medicine("Metformin", "Bristol Myers Squibb", "500 mg", "Take with meals");
//        Medicine medicine06 = new Medicine("Tramadol", "Generic Pharmacy", "50 mg", "Take for moderate to severe pain");
//        Medicine medicine07 = new Medicine("Albuterol", "GlaxoSmithKline", "2 mg", "Inhale as needed");
//
//        prescription01.AddMedicine(medicine01);
//        prescription01.AddMedicine(medicine02);
//
//        prescription02.AddMedicine(medicine03);
//        prescription02.AddMedicine(medicine04);
//        prescription02.AddMedicine(medicine05);
//
//        prescription03.AddMedicine(medicine06);
//        prescription03.AddMedicine(medicine07);
//
//        try {
//            PreventiveVisit.CreatePreventiveVisit("Dental Checkup", LocalDate.of(2023, 6, 25), false, 100.0, reservation02, "No cavities found.", "Brush twice daily.", doctor03);
//            PreventiveVisit.CreatePreventiveVisit("Annual Physical Exam", LocalDate.of(2023, 7, 10), true, 150.0, reservation07, "Overall good health.", "Exercise regularly and eat a balanced diet.", doctor01);
//            PreventiveVisit.CreatePreventiveVisit("Eye Examination", LocalDate.of(2023, 8, 5), false, 80.0, reservation04, "No vision problems detected.", "Wear protective eyewear in bright sunlight.", doctor03);
//
//            PreventiveVisit.extent.get(0).setPrescription(prescription02);
//            medicalExamination01.setPrescription(prescription03);
//
//            Specialization.CreateSpecialization(doctor01, "Orthopedics", LocalDate.of(2021, 2, 28));
//            Specialization.CreateSpecialization(doctor02, "Cardiology", LocalDate.of(2020, 5, 15));
//            Specialization.CreateSpecialization(doctor02, "Dermatology", LocalDate.of(2019, 8, 10));
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        Launcher.readExtends();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartView startView = new StartView();
                startView.setVisible(true);
            }
        });
    }
}
