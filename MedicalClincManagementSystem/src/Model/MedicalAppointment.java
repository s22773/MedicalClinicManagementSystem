package Model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class MedicalAppointment implements Serializable {
    private String Name;
    private LocalDate DateOfAppointment;
    boolean Remotely;
    double Cost;
    private static double MinimalCost = 100;

    private Reservation Reservation;
    private Facility Facility;

    public abstract double GetActualCost();

    public MedicalAppointment(String name, LocalDate dateOfAppointment, boolean remotely, double cost) {
        Name = name;
        DateOfAppointment = dateOfAppointment;
        Remotely = remotely;
        Cost = cost;
        Reservation = null;
        Facility = null;
    }

    public MedicalAppointment(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, Model.Facility facility) {
        Name = name;
        DateOfAppointment = dateOfAppointment;
        Remotely = remotely;
        Cost = cost;
        Reservation = reservation;
        Facility = facility;
    }

    public MedicalAppointment(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation) {
        Name = name;
        DateOfAppointment = dateOfAppointment;
        Remotely = remotely;
        Cost = cost;
        Reservation = reservation;
        Facility = null;
    }

    public void AddReservation(Model.Reservation reservation) {
        if(Reservation == null) {
            Reservation = reservation;

            reservation.AddMedicalAppointment(this);
        }
    }

    public void AddFacility(Facility facility) {
        if(Facility == null) {
            Facility = facility;

            facility.AddMedicalAppointment(this);
        }
    }
}
