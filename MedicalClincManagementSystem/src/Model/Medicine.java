package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medicine.
 */
public class Medicine implements Serializable {
    /**
     * A list that holds all instances of Medicine.
     */
    public static List<Medicine> extent = new ArrayList<Medicine>();

    private String Name;
    private String Producent;
    private String Dose;
    private String Instruction;

    private List<Prescription> Prescriptions;

    /**
     * Constructs a Medicine object with the specified parameters.
     *
     * @param name        The name of the medicine.
     * @param producent   The producer of the medicine.
     * @param dose        The dose of the medicine.
     * @param instruction The instruction for using the medicine.
     */
    public Medicine(String name, String producent, String dose, String instruction) {
        Name = name;
        Producent = producent;
        Dose = dose;
        Instruction = instruction;
        Prescriptions = new ArrayList<>();
        extent.add(this);
    }

    /**
     * Adds a prescription to the list of prescriptions associated with the medicine.
     *
     * @param prescription The prescription to add.
     */
    public void AddPrescription(Prescription prescription) {
        if(!Prescriptions.contains(prescription)) {
            Prescriptions.add(prescription);

            prescription.AddMedicine(this);
        }
    }

    /**
     * Writes the extent of Medicine objects to the specified File.
     *
     * @param stream The ObjectOutputStream to write to.
     * @throws IOException            If an I/O error occurs while writing the extent.
     */
    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    /**
     * Reads the extent of Medicine objects from the specified ObjectInputStream.
     *
     * @param stream The ObjectInputStream to read from.
     * @throws IOException            If an I/O error occurs while reading the extent.
     * @throws ClassNotFoundException If the class of the read object cannot be found.
     */
    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Medicine>) stream.readObject();
    }

    /**
     * Returns the name of the medicine.
     *
     * @return The name of the medicine.
     */
    public String getName() {
        return Name;
    }

    /**
     * Returns the producer of the medicine.
     *
     * @return The producer of the medicine.
     */
    public String getProducent() {
        return Producent;
    }

    /**
     * Returns the dose of the medicine.
     *
     * @return The dose of the medicine.
     */
    public String getDose() {
        return Dose;
    }

    /**
     * Returns the instruction for using the medicine.
     *
     * @return The instruction for using the medicine.
     */
    public String getInstruction() {
        return Instruction;
    }
}
