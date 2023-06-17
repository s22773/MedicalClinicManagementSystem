package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a preventive visit, which is a type of medical appointment.
 */
public class PreventiveVisit extends MedicalAppointment implements Serializable {
    /**
     * A list that holds all instances of PreventiveVisit.
     */
    public static List<PreventiveVisit> extent = new ArrayList<PreventiveVisit>();

    private String Observation;
    private String Recommendation;

    private Doctor Doctor;

    /**
     * Constructs a PreventiveVisit object with the specified parameters.
     *
     * @param name             The name of the preventive visit.
     * @param dateOfAppointment The date of the preventive visit.
     * @param remotely         Indicates if the preventive visit is conducted remotely or not.
     * @param cost             The cost of the preventive visit.
     * @param reservation      The reservation associated with the preventive visit.
     * @param observation      The observation made during the preventive visit.
     * @param recommendation   The recommendation provided during the preventive visit.
     * @param doctor           The doctor conducting the preventive visit.
     */
    public PreventiveVisit(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, String observation, String recommendation, Model.Doctor doctor) {
        super(name, dateOfAppointment, remotely, cost, reservation);
        Observation = observation;
        Recommendation = recommendation;
        Doctor = doctor;
        extent.add(this);
    }

    /**
     * Creates a new preventive visit with the specified parameters and associates it with the given doctor.
     *
     * @param name             The name of the preventive visit.
     * @param dateOfAppointment The date of the preventive visit.
     * @param remotely         Indicates if the preventive visit is conducted remotely or not.
     * @param cost             The cost of the preventive visit.
     * @param reservation      The reservation associated with the preventive visit.
     * @param observation      The observation made during the preventive visit.
     * @param recommendation   The recommendation provided during the preventive visit.
     * @param doctor           The doctor conducting the preventive visit.
     * @return The created PreventiveVisit object.
     * @throws Exception If the given doctor does not exist.
     */
    public static PreventiveVisit CreatePreventiveVisit(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Reservation reservation, String observation, String recommendation, Model.Doctor doctor) throws Exception {
        if(doctor == null)
            throw new Exception("The given Doctor does not exist!");

        PreventiveVisit preventiveVisit = new PreventiveVisit(name, dateOfAppointment, remotely, cost, reservation, observation, recommendation, doctor);
        doctor.AddPreventiveVisit(preventiveVisit);

        return preventiveVisit;
    }

    /**
     * Returns the actual cost of the preventive visit.
     *
     * @return The actual cost of the preventive visit.
     */
    @Override
    public double GetActualCost() {
        return 0;
    }

    /**
     * Sets the observation made during the preventive visit.
     *
     * @param observation The observation made during the preventive visit.
     */
    public void setObservation(String observation) {
        Observation = observation;
    }

    /**
     * Sets the recommendation provided during the preventive visit.
     *
     * @param recommendation The recommendation provided during the preventive visit.
     */
    public void setRecommendation(String recommendation) {
        Recommendation = recommendation;
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
        extent = (ArrayList<PreventiveVisit>) stream.readObject();
    }

    /**
     * Displays the extent of the PreventiveVisit class.
     */
    public static void showExtent() {

        System.out.println("Extent of the class: " + PreventiveVisit.class.getName());

        for (PreventiveVisit object : extent) {
            System.out.println(object);
        }
    }
}
