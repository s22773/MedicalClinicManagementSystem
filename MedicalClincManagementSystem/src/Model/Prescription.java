package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a prescription.
 */
public class Prescription implements Serializable {
    /**
     * A list that holds all instances of Prescription.
     */
    public static List<Prescription> extent = new ArrayList<Prescription>();

    private String Dosage;
    private float Reimbursed;

    private List<Medicine> Medicines;
    private MedicalAppointment MedicalAppointment;

    /**
     * Constructs a Prescription object with the specified dosage and reimbursement.
     *
     * @param dosage     The dosage of the prescription.
     * @param reimbursed The amount reimbursed for the prescription.
     */
    public Prescription(String dosage, float reimbursed) {
        Dosage = dosage;
        Reimbursed = reimbursed;
        Medicines = new ArrayList<>();
        MedicalAppointment = null;
        extent.add(this);
    }

    /**
     * Constructs a Prescription object associated with the specified medical appointment.
     *
     * @param medicalAppointment The medical appointment associated with the prescription.
     */
    public Prescription(MedicalAppointment medicalAppointment) {
        Dosage = null;
        Reimbursed = 0f;
        Medicines = new ArrayList<>();
        MedicalAppointment = medicalAppointment;
        extent.add(this);
    }

    /**
     * Constructs a Prescription object with the specified dosage, reimbursement, and medical appointment.
     *
     * @param dosage             The dosage of the prescription.
     * @param reimbursed         The amount reimbursed for the prescription.
     * @param medicalAppointment The medical appointment associated with the prescription.
     */
    public Prescription(String dosage, float reimbursed, Model.MedicalAppointment medicalAppointment) {
        Dosage = dosage;
        Reimbursed = reimbursed;
        Medicines = new ArrayList<>();
        MedicalAppointment = medicalAppointment;
        extent.add(this);
    }

    /**
     * Adds a medicine to the prescription.
     *
     * @param medicine The medicine to add.
     */
    public void AddMedicine(Medicine medicine) {
        if(!Medicines.contains(medicine)) {
            Medicines.add(medicine);
            medicine.AddPrescription(this);
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
        extent = (ArrayList<Prescription>) stream.readObject();
    }

    /**
     * Returns the dosage of the prescription.
     *
     * @return The dosage of the prescription.
     */
    public String getDosage() {
        return Dosage;
    }

    /**
     * Returns the amount reimbursed for the prescription.
     *
     * @return The amount reimbursed for the prescription.
     */
    public float getReimbursed() {
        return Reimbursed;
    }

    /**
     * Returns the list of medicines associated with the prescription.
     *
     * @return The list of medicines associated with the prescription.
     */
    public List<Medicine> getMedicines() {
        return Medicines;
    }

    /**
     * Returns the medical appointment associated with the prescription.
     *
     * @return The medical appointment associated with the prescription.
     */
    public Model.MedicalAppointment getMedicalAppointment() {
        return MedicalAppointment;
    }
}
