package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specialization in the medical field.
 */
public class Specialization implements Serializable {
    /**
     * A list that holds all instances of Specialization.
     */
    public static List<Specialization> extent = new ArrayList<Specialization>();

    private String Name;
    private LocalDate DateOfAcquisition;

    private Doctor Doctor;

    /**
     * Constructs a Specialization object with the specified parameters.
     *
     * @param name             The name of the specialization.
     * @param dateOfAcquisition The date of acquisition of the specialization.
     * @param doctor           The doctor specializing in the given specialization.
     */
    private Specialization(String name, LocalDate dateOfAcquisition, Model.Doctor doctor) {
        Name = name;
        DateOfAcquisition = dateOfAcquisition;
        Doctor = doctor;
        extent.add(this);
    }

    /**
     * Creates a new specialization for the given doctor with the specified name and date of acquisition.
     *
     * @param doctor           The doctor to associate the specialization with.
     * @param name             The name of the specialization.
     * @param dateOfAcquisition The date of acquisition of the specialization.
     * @return The created specialization.
     * @throws Exception If the given doctor is null.
     */
    public static Specialization CreateSpecialization(Doctor doctor, String name, LocalDate dateOfAcquisition) throws Exception {
        if(doctor == null)
            throw new Exception("The given Doctor does not exist!");

        Specialization specialization = new Specialization(name, dateOfAcquisition, doctor);
        doctor.AddSpecialization(specialization);

        return specialization;
    }

    /**
     * Adds a doctor to the specialization.
     *
     * @param doctor The doctor to add to the specialization.
     */
    public void AddDoctor(Doctor doctor) {
        this.Doctor = doctor;
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
        extent = (ArrayList<Specialization>) stream.readObject();
    }

    /**
     * Displays the extent of the Specialization class by printing each specialization.
     */
    public static void showExtent() {

        System.out.println("Extent of the class: " + Specialization.class.getName());

        for (Specialization object : extent) {
            System.out.println(object);
        }
    }
}
