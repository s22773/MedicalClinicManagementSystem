import Model.*;

import javax.print.Doc;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final String fileSrc = "src/Data/data.ser";

        Surgery surgery01 = new Surgery("Appendectomy", LocalDate.of(2023, 6, 15), false, 5000.0, 120, "General");
        Surgery surgery02 = new Surgery("Knee Replacement", LocalDate.of(2023, 6, 20), false, 10000.0, 180, "Epidural");
        Surgery surgery03 = new Surgery("Cataract Surgery", LocalDate.of(2023, 7, 5), true, 3000.0, 60, "Local");

        Doctor doctor01 = new Doctor("John", "Smith", 5);
        Doctor doctor02 = new Doctor("Emily", "Johnson", 10);
        Doctor doctor03 = new Doctor("Michael", "Williams", 3);

        doctor01.AddSurgery(surgery01);
        doctor01.AddSurgery(surgery02);
        surgery03.AddDoctor(doctor03);

        Technician technician01 = new Technician(12345, Arrays.asList("Blood Test", "MRI Scan", "X-ray"));

        MedicalExamination medicalExamination01 = new MedicalExamination("Blood Test", LocalDate.of(2023, 6, 15), true, 150.00, "Blood Test", LocalDate.of(2023, 6, 16));
        MedicalExamination medicalExamination02 = new MedicalExamination("MRI Scan", LocalDate.of(2023, 7, 2), false, 250.00, "MRI Scan", LocalDate.of(2023, 7, 5));
        MedicalExamination medicalExamination03 = new MedicalExamination("Physical Examination", LocalDate.of(2023, 8, 10), true, 100.00, "Physical Examination", LocalDate.of(2023, 8, 11));

        medicalExamination01.AddTechnician(technician01);
        medicalExamination02.AddDoctor(doctor02);
        medicalExamination02.AddDoctor(doctor03);
        medicalExamination03.AddTechnician(technician01);

        Patient patient01 = new Patient("Adam", "Kowalski", Gender.MALE, LocalDate.of(1990, 5, 15), "1234567890");
        Patient patient02 = new Patient("Ewa", "Nowak", Gender.FEMALE, LocalDate.of(1985, 10, 20), "9876543210");
        Patient patient03 = new Patient("Michał", "Wójcik", Gender.MALE, LocalDate.of(1978, 12, 3), "5555555555");

        Insurance insurance01 = new Insurance(InsuranceType.EMPLOYMENT, 123456, "ABC Insurance", patient01);
        Insurance insurance02 = new Insurance(InsuranceType.STUDENT, 789012, "XYZ Insurance");
        Insurance insurance03 = new Insurance(InsuranceType.PRIVATE, 345678, "PQR Insurance");
        Insurance insurance04 = new Insurance(InsuranceType.PUBLIC, 987654, "LMN Insurance");

        patient01.AddInsurance(insurance02);
        patient02.AddInsurance(insurance03);
        patient03.AddInsurance(insurance04);

        Address address01 = new Address("New York", "Broadway", "10001");
        Address address02 = new Address("London", "Oxford Street", "W1D 1BS");
        Address address03 = new Address("Paris", "Champs-Élysées", "75008");

        patient02.AddAddress(address01);
        address02.AddPatient(patient01);
        patient03.AddAddress(address03);

        Reservation reservation01 = new Reservation(LocalDate.of(2023, 6, 15), true, patient01);
        Reservation reservation02 = new Reservation(LocalDate.of(2023, 7, 2), false, patient02);
        Reservation reservation03 = new Reservation(LocalDate.of(2023, 8, 10), true);

        patient03.AddReservation(reservation03);

        Address address04 = new Address("Cityville", "Longslide", "12345");

        Facility facility01 = new Facility("Medical Center", 101, true, address04);
        facility01.AddMedicalAppointment(medicalExamination01);

        Prescription prescription01 = new Prescription("1 pill twice daily", 75.0f, medicalExamination01);

        Medicine medicine01 = new Medicine("Aspirin", "ABC Pharmaceuticals", "500mg", "Take one tablet with water every 4-6 hours.");
        Medicine medicine02 = new Medicine("Amoxicillin", "XYZ Pharmaceuticals", "250mg", "Take one capsule three times a day for 7 days.");

        prescription01.AddMedicine(medicine01);
        prescription01.AddMedicine(medicine02);

        try {
            PreventiveVisit.CreatePreventiveVisit("Dental Checkup", LocalDate.of(2023, 6, 25), false, 100.0, "No cavities found.", "Brush twice daily.", doctor03);
            PreventiveVisit.CreatePreventiveVisit("Annual Physical Exam", LocalDate.of(2023, 7, 10), true, 150.0, "Overall good health.", "Exercise regularly and eat a balanced diet.", doctor01);
            PreventiveVisit.CreatePreventiveVisit("Eye Examination", LocalDate.of(2023, 8, 5), false, 80.0, "No vision problems detected.", "Wear protective eyewear in bright sunlight.", doctor03);

            Specialization.CreateSpecialization(doctor01, "Orthopedics", LocalDate.of(2021, 2, 28));
            Specialization.CreateSpecialization(doctor02, "Cardiology", LocalDate.of(2020, 5, 15));
            Specialization.CreateSpecialization(doctor02, "Dermatology", LocalDate.of(2019, 8, 10));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            var out = new ObjectOutputStream(new FileOutputStream(fileSrc));
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
            
            var in = new ObjectInputStream(new FileInputStream(fileSrc));
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




        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        Patient.showExtent();
    }
}
