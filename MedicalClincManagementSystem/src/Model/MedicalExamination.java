package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medical examination, which is a type of medical appointment.
 */
public class MedicalExamination extends MedicalAppointment implements Serializable {
    /**
     * A list that holds all instances of MedicalExamination.
     */
    public static List<MedicalExamination> extent = new ArrayList<MedicalExamination>();

    private String ExaminationName;
    private LocalDate DateOfExamination;
    private String ExaminationResult;

    private List<Doctor> Doctors;
    private List<Technician> Technicians;

    /**
     * Constructs a MedicalExamination object with the specified parameters.
     *
     * @param examinationName  The name of the examination.
     * @param dateOfAppointment The date of the examination.
     * @param cost             The cost of the examination.
     * @param reservation      The reservation for the examination.
     */
    public MedicalExamination(String examinationName, LocalDate dateOfAppointment, double cost, Model.Reservation reservation) {
        super(examinationName, dateOfAppointment, false, cost, reservation);
        ExaminationName = examinationName;
        DateOfExamination = dateOfAppointment;
        ExaminationResult = null;
        Doctors = new ArrayList<>();
        Technicians = new ArrayList<>();
        extent.add(this);
    }

    /**
     * Sets the examination result.
     *
     * @param examinationResult The examination result.
     */
    public void AddExaminationResult(String examinationResult) {
        ExaminationResult = examinationResult;
    }

    /**
     * Adds a doctor to the list of doctors associated with the examination.
     *
     * @param doctor The doctor to add.
     */
    public void AddDoctor(Doctor doctor) {
        if(!Doctors.contains(doctor)) {
            Doctors.add(doctor);

            doctor.AddMedicalExamination(this);
        }
    }

    /**
     * Adds a technician to the list of technicians associated with the examination.
     *
     * @param technician The technician to add.
     */
    public void AddTechnician(Technician technician) {
        if(!Technicians.contains(technician)) {
            Technicians.add(technician);

            technician.AddMedicalExamination(this);
        }
    }

    /**
     * Retrieves the cost of the medical examination.
     *
     * @return the cost of the medical examination
     */
    @Override
    public double GetActualCost() {
        return 0;
    }

    /**
     * Writes the extent of MedicalExamination objects to the specified File.
     *
     * @param stream The ObjectOutputStream to write to.
     * @throws IOException            If an I/O error occurs while writing the extent.
     */
    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    /**
     * Reads the extent of MedicalExamination objects from the specified ObjectInputStream.
     *
     * @param stream The ObjectInputStream to read from.
     * @throws IOException            If an I/O error occurs while reading the extent.
     * @throws ClassNotFoundException If the class of the read object cannot be found.
     */
    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<MedicalExamination>) stream.readObject();
    }
}
