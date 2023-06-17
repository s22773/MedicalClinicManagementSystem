package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MedicalAppointment class represents a medical appointment.
 * It is an abstract class that provides common functionality and attributes for different types of medical appointments.
 */
public abstract class MedicalAppointment implements Serializable {
    /**
     * A list containing all instances of MedicalAppointment created.
     */
    public static List<MedicalAppointment> extent = new ArrayList<MedicalAppointment>();

    private String Name;
    private LocalDate DateOfAppointment;
    boolean Remotely;
    double Cost;
    private static double MinimalCost = 100;

    private Reservation Reservation;
    private Facility Facility;
    private Prescription Prescription;

    /**
     * Calculates and returns the actual cost of the medical appointment.
     * Subclasses must implement this method to provide their specific calculation logic.
     *
     * @return the actual cost of the medical appointment
     */
    public abstract double GetActualCost();

    /**
     * Constructs a MedicalAppointment object with the specified name, date of appointment, remote status, cost, reservation, and facility.
     * If the cost is less than the minimal cost, it is set to the minimal cost.
     * Initializes the associated prescription as null.
     * Adds the newly created instance to the extent list.
     *
     * @param name              the name of the medical appointment
     * @param dateOfAppointment the date of the appointment
     * @param remotely          the remote status of the appointment
     * @param cost              the cost of the appointment
     * @param reservation       the reservation associated with the appointment
     * @param facility          the facility where the appointment takes place
     */
    public MedicalAppointment(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, Model.Facility facility) {
        Name = name;
        DateOfAppointment = dateOfAppointment;
        Remotely = remotely;
        Cost = (cost < MedicalAppointment.MinimalCost)?MedicalAppointment.MinimalCost:cost;
        Reservation = reservation;
        Facility = facility;
        Prescription = null;
        extent.add(this);
    }

    /**
     * Constructs a MedicalAppointment object with the specified name, date of appointment, remote status, cost, reservation, facility, and prescription.
     * If the cost is less than the minimal cost, it is set to the minimal cost.
     * Adds the newly created instance to the extent list.
     *
     * @param name              the name of the medical appointment
     * @param dateOfAppointment the date of the appointment
     * @param remotely          the remote status of the appointment
     * @param cost              the cost of the appointment
     * @param reservation       the reservation associated with the appointment
     * @param facility          the facility where the appointment takes place
     * @param prescription      the prescription associated with the appointment
     */
    public MedicalAppointment(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, Model.Facility facility, Prescription prescription) {
        Name = name;
        DateOfAppointment = dateOfAppointment;
        Remotely = remotely;
        Cost = (cost < MedicalAppointment.MinimalCost)?MedicalAppointment.MinimalCost:cost;
        Reservation = reservation;
        Facility = facility;
        Prescription = prescription;
        extent.add(this);
    }

    /**
     * Constructs a MedicalAppointment object with the specified name, date of appointment, remote status, cost, and reservation.
     * The associated facility is set to null.
     * Adds the newly created instance to the extent list.
     *
     * @param name              the name of the medical appointment
     * @param dateOfAppointment the date of the appointment
     * @param remotely          the remote status of the appointment
     * @param cost              the cost of the appointment
     * @param reservation       the reservation associated with the appointment
     */
    public MedicalAppointment(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation) {
        Name = name;
        DateOfAppointment = dateOfAppointment;
        Remotely = remotely;
        Cost = cost;
        Reservation = reservation;
        Facility = null;
        extent.add(this);
    }

    /**
     * Adds the specified reservation to the medical appointment.
     * If there is no existing reservation associated with the appointment, the reservation is added.
     *
     * @param reservation the reservation to be added
     */
    public void AddReservation(Model.Reservation reservation) {
        if(Reservation == null) {
            Reservation = reservation;

            reservation.AddMedicalAppointment(this);
        }
    }

    /**
     * Adds the specified facility to the medical appointment.
     * If there is no existing facility associated with the appointment, the facility is added.
     *
     * @param facility the facility to be added
     */
    public void AddFacility(Facility facility) {
        if(Facility == null) {
            Facility = facility;

            facility.AddMedicalAppointment(this);
        }
    }

    /**
     * Retrieves the name of the medical appointment.
     *
     * @return the name of the medical appointment
     */
    public String getName() {
        return Name;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return the date of the appointment
     */
    public LocalDate getDateOfAppointment() {
        return DateOfAppointment;
    }

    /**
     * Checks if the appointment is remote or not.
     *
     * @return true if the appointment is remote, false otherwise
     */
    public boolean isRemotely() {
        return Remotely;
    }

    /**
     * Retrieves the cost of the medical appointment.
     *
     * @return the cost of the medical appointment
     */
    public double getCost() {
        return Cost;
    }

    /**
     * Retrieves the reservation associated with the medical appointment.
     *
     * @return the reservation associated with the medical appointment
     */
    public Model.Reservation getReservation() {
        return Reservation;
    }

    /**
     * Retrieves the prescription associated with the medical appointment.
     *
     * @return the prescription associated with the medical appointment
     */
    public Model.Prescription getPrescription() {
            return Prescription;
    }

    /**
     * Sets the prescription associated with the medical appointment.
     *
     * @param prescription the prescription to be set
     */
    public void setPrescription(Model.Prescription prescription) {
        Prescription = prescription;
    }

    /**
     * Writes the extent of the MedicalAppointment class to the specified File.
     *
     * @param stream the ObjectOutputStream to write the extent to
     * @throws IOException            if an I/O error occurs while writing the extent
     */
    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    /**
     * Reads the extent of the MedicalAppointment class from the specified ObjectInputStream.
     *
     * @param stream the ObjectInputStream to read the extent from
     * @throws IOException            if an I/O error occurs while reading the extent
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<MedicalAppointment>) stream.readObject();
    }
}
