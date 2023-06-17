package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a reservation made by a patient for a medical appointment.
 */
public class Reservation implements Serializable {
    /**
     * A list that holds all instances of Reservation.
     */
    public static List<Reservation> extent = new ArrayList<Reservation>();

    private LocalDate ReservationDate;
    private boolean Confirmed;

    private Patient Patient;
    private MedicalAppointment MedicalAppointment;

    /**
     * Constructs a Reservation object with the specified parameters.
     *
     * @param reservationDate The date of the reservation.
     * @param confirmed       Indicates if the reservation is confirmed or not.
     * @param patient         The patient making the reservation.
     */
    public Reservation(LocalDate reservationDate, boolean confirmed, Model.Patient patient) {
        ReservationDate = reservationDate;
        Confirmed = confirmed;
        Patient = patient;
        extent.add(this);
    }

    /**
     * Constructs a Reservation object with the specified parameters.
     *
     * @param reservationDate    The date of the reservation.
     * @param confirmed          Indicates if the reservation is confirmed or not.
     * @param patient            The patient making the reservation.
     * @param medicalAppointment The medical appointment associated with the reservation.
     */
    public Reservation(LocalDate reservationDate, boolean confirmed, Model.Patient patient, MedicalAppointment medicalAppointment) {
        ReservationDate = reservationDate;
        Confirmed = confirmed;
        Patient = patient;
        MedicalAppointment = medicalAppointment;
        extent.add(this);
    }

    /**
     * Adds a patient to the reservation.
     *
     * @param patient The patient to add to the reservation.
     */
    public void AddPatient(Model.Patient patient) {
        if(Patient == null) {
            Patient = patient;

            patient.AddReservation(this);
        }
    }

    /**
     * Adds a medical appointment to the reservation.
     *
     * @param medicalAppointment The medical appointment to add to the reservation.
     */
    public void AddMedicalAppointment(MedicalAppointment medicalAppointment) {
        if(MedicalAppointment == null) {
            MedicalAppointment = medicalAppointment;

            medicalAppointment.AddReservation(this);
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
        extent = (ArrayList<Reservation>) stream.readObject();
    }

    /**
     * Returns a string representation of the Reservation object.
     *
     * @return A string representation of the Reservation object.
     */
    @Override
    public String toString() {
        return "" + Patient;
    }

    /**
     * Gets the patient associated with the reservation.
     *
     * @return The patient associated with the reservation.
     */
    public Model.Patient getPatient() {
        return Patient;
    }
}
