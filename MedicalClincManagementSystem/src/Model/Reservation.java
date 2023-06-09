package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation implements Serializable {
    public static List<Reservation> extent = new ArrayList<Reservation>();

    private LocalDate ReservationDate;
    private boolean Confirmed;

    private Patient Patient;
    private MedicalAppointment MedicalAppointment;

    public Reservation(LocalDate reservationDate, boolean confirmed) {
        ReservationDate = reservationDate;
        Confirmed = confirmed;
        Patient = null;
        extent.add(this);
    }

    public Reservation(LocalDate reservationDate, boolean confirmed, Model.Patient patient) {
        ReservationDate = reservationDate;
        Confirmed = confirmed;
        Patient = patient;
        extent.add(this);
    }

    public Reservation(LocalDate reservationDate, boolean confirmed, Model.Patient patient, MedicalAppointment medicalAppointment) {
        ReservationDate = reservationDate;
        Confirmed = confirmed;
        Patient = patient;
        MedicalAppointment = medicalAppointment;
        extent.add(this);
    }

    public void AddPatient(Model.Patient patient) {
        if(Patient == null) {
            Patient = patient;

            patient.AddReservation(this);
        }
    }

    public void AddMedicalAppointment(MedicalAppointment medicalAppointment) {
        if(MedicalAppointment == null) {
            MedicalAppointment = medicalAppointment;

            medicalAppointment.AddReservation(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Reservation>) stream.readObject();
    }
}
