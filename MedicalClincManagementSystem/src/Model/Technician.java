package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a technician.
 */
public class Technician implements Serializable {
    /**
     * A list that holds all instances of Technician.
     */
    public static List<Technician> extent = new ArrayList<Technician>();

    private int IdNumber;
    private List<String> RealizedServices;

    private List<MedicalExamination> MedicalExaminations;

    /**
     * Constructs a Technician object with the specified parameters.
     *
     * @param idNumber        The identification number of the technician.
     * @param realizedServices The list of services realized by the technician.
     */
    public Technician(int idNumber, List<String> realizedServices) {
        IdNumber = idNumber;
        RealizedServices = realizedServices;
        MedicalExaminations = new ArrayList<>();
        extent.add(this);
    }

    /**
     * Adds a medical examination to the technician.
     *
     * @param medicalExamination The medical examination to add.
     */
    public void AddMedicalExamination(MedicalExamination medicalExamination) {
        if(!MedicalExaminations.contains(medicalExamination)) {
            MedicalExaminations.add(medicalExamination);

            medicalExamination.AddTechnician(this);
        }
    }

    /**
     * Adds a realized service to the technician.
     *
     * @param service The realized service to add.
     */
    public void AddRealizedService(String service) {
        RealizedServices.add(service);
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
        extent = (ArrayList<Technician>) stream.readObject();
    }
}
