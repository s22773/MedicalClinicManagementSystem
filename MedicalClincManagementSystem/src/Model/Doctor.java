package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Doctor class represents a doctor.
 * It contains information about the doctor's personal details, specializations, and medical activities.
 */
public class Doctor implements Serializable {
    /**
     * A list containing all instances of Doctor created.
     */
    public static List<Doctor> extent = new ArrayList<Doctor>();

    private String FirstName;
    private String LastName;
    private int WorkInternship;

    private List<Specialization> Specializations;
    private List<PreventiveVisit> PreventiveVisits;
    private List<Surgery> Surgeries;
    private List<MedicalExamination> MedicalExaminations;

    /**
     * Constructs a Doctor object with the specified first name, last name, and work internship.
     * Initializes the Specializations, PreventiveVisits, Surgeries, and MedicalExaminations lists as empty ArrayLists.
     * Adds the newly created instance to the extent list.
     *
     * @param firstName      the first name of the doctor
     * @param lastName       the last name of the doctor
     * @param workInternship the number of years of work internship of the doctor
     */
    public Doctor(String firstName, String lastName, int workInternship) {
        FirstName = firstName;
        LastName = lastName;
        WorkInternship = workInternship;
        Specializations = new ArrayList<>();
        PreventiveVisits = new ArrayList<>();
        Surgeries = new ArrayList<>();
        MedicalExaminations = new ArrayList<>();
        extent.add(this);
    }

    /**
     * Adds a specialization to the Specializations list associated with this doctor.
     *
     * @param specialization the specialization to add
     */
    public void AddSpecialization(Specialization specialization) {
        if(!Specializations.contains(specialization)) {
            Specializations.add(specialization);
        }
    }

    /**
     * Adds a preventive visit to the PreventiveVisits list associated with this doctor.
     *
     * @param preventiveVisit the preventive visit to add
     */
    public void AddPreventiveVisit(PreventiveVisit preventiveVisit) {
        if(!PreventiveVisits.contains(preventiveVisit)) {
            PreventiveVisits.add(preventiveVisit);
        }
    }

    /**
     * Adds a surgery to the Surgeries list associated with this doctor.
     * Also adds this doctor to the surgery's doctor list if not already present.
     *
     * @param surgery the surgery to add
     */
    public void AddSurgery(Surgery surgery) {
        if(!Surgeries.contains(surgery)) {
            Surgeries.add(surgery);

            surgery.AddDoctor(this);
        }
    }

    /**
     * Adds a medical examination to the MedicalExaminations list associated with this doctor.
     * Also adds this doctor to the medical examination's doctor list if not already present.
     *
     * @param medicalExamination the medical examination to add
     */
    public void AddMedicalExamination(MedicalExamination medicalExamination) {
        if(!MedicalExaminations.contains(medicalExamination)) {
            MedicalExaminations.add(medicalExamination);

            medicalExamination.AddDoctor(this);
        }
    }

    /**
     * Writes the extent list to the specified File.
     *
     * @param stream the ObjectOutputStream to write to
     * @throws IOException if an I/O error occurs during writing
     */
    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    /**
     * Reads the extent list from the specified ObjectInputStream.
     *
     * @param stream the ObjectInputStream to read from
     * @throws IOException            if an I/O error occurs during reading
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Doctor>) stream.readObject();
    }

    /**
     * Prints the extent of the Doctor class to the console.
     */
    public static void showExtent() {

        System.out.println("Extent of the class: " + Doctor.class.getName());

        for (Doctor object : extent) {
            System.out.println(object);
        }
    }
}
