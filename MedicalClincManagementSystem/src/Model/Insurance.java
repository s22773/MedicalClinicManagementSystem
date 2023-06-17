package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Insurance class represents an insurance policy.
 * It contains information about the insurance type, policy number, insurance provider, and associated patient.
 */
public class Insurance implements Serializable {
    /**
     * A list containing all instances of Insurance created.
     */
    public static List<Insurance> extent = new ArrayList<Insurance>();

    private InsuranceType InsuranceType;
    private int PolicyNumber;
    private String InsuranceProvider;

    private Patient Patient;

    /**
     * Constructs an Insurance object with the specified insurance type, policy number, and insurance provider.
     * Initializes the associated patient as null.
     * Adds the newly created instance to the extent list.
     *
     * @param insuranceType     the type of insurance
     * @param policyNumber      the policy number
     * @param insuranceProvider the insurance provider
     */
    public Insurance(Model.InsuranceType insuranceType, int policyNumber, String insuranceProvider) {
        InsuranceType = insuranceType;
        PolicyNumber = policyNumber;
        InsuranceProvider = insuranceProvider;
        Patient = null;
        extent.add(this);
    }

    /**
     * Constructs an Insurance object with the specified insurance type, policy number, insurance provider, and associated patient.
     * Adds the newly created instance to the extent list.
     *
     * @param insuranceType     the type of insurance
     * @param policyNumber      the policy number
     * @param insuranceProvider the insurance provider
     * @param patient           the associated patient
     */
    public Insurance(Model.InsuranceType insuranceType, int policyNumber, String insuranceProvider, Model.Patient patient) {
        InsuranceType = insuranceType;
        PolicyNumber = policyNumber;
        InsuranceProvider = insuranceProvider;
        Patient = patient;
        extent.add(this);
    }

    /**
     * Adds a patient to the insurance policy if the current associated patient is null.
     * Also adds this insurance policy to the patient's insurance list if not already present.
     *
     * @param patient the patient to add
     */
    public void AddPatient(Model.Patient patient) {
        if(Patient == null) {
            Patient = patient;

            patient.AddInsurance(this);
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
        extent = (ArrayList<Insurance>) stream.readObject();
    }
}
