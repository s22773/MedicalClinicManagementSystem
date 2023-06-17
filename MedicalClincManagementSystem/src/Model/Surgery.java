package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a surgery appointment.
 */
public class Surgery extends MedicalAppointment implements Serializable {
    /**
     * A list that holds all instances of Surgery.
     */
    public static List<Surgery> extent = new ArrayList<Surgery>();

    private int Duration;
    private String Anesthesia;

    private List<Doctor> Doctors;

    /**
     * Constructs a Surgery object with the specified parameters.
     *
     * @param name             The name of the surgery.
     * @param dateOfAppointment The date of the surgery appointment.
     * @param remotely         Indicates whether the surgery is performed remotely.
     * @param cost             The cost of the surgery.
     * @param reservation      The reservation associated with the surgery.
     * @param duration         The duration of the surgery in minutes.
     * @param anesthesia       The type of anesthesia used for the surgery.
     */
    public Surgery(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, int duration, String anesthesia) {
        super(name, dateOfAppointment, remotely, cost, reservation);
        Duration = duration;
        Anesthesia = anesthesia;
        Doctors = new ArrayList<>();
        extent.add(this);
    }

    /**
     * Retrieves the actual cost of the surgery.
     *
     * @return The actual cost of the surgery.
     */
    @Override
    public double GetActualCost() {
        return 0;
    }

    /**
     * Modifies the duration of the surgery.
     *
     * @param newDuration The new duration of the surgery in minutes.
     */
    public void ModifyDuration(int newDuration) {
        this.Duration = newDuration;
    }

    /**
     * Adds a doctor to the surgery.
     *
     * @param doctor The doctor to add to the surgery.
     */
    public void AddDoctor(Doctor doctor) {
        if(!Doctors.contains(doctor)) {
            Doctors.add(doctor);

            doctor.AddSurgery(this);
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
        extent = (ArrayList<Surgery>) stream.readObject();
    }

    /**
     * Displays the extent of the Surgery class by printing each surgery.
     */
    public static void showExtent() {

        System.out.println("Extent of the class: " + Surgery.class.getName());

        for (Surgery object : extent) {
            System.out.println(object);
        }
    }
}
