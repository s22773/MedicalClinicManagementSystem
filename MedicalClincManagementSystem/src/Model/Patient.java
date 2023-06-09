package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable {
    public static List<Patient> extent = new ArrayList<Patient>();

    private String FirstName;
    private String LastName;
    private Gender Gender;
    private LocalDate DateOfBirth;
    private String PhoneNumber;

    private Address Address;
    private List<Insurance> Insurances;
    private List<Reservation> Reservations;

    public Patient(String firstName, String lastName, Model.Gender gender, LocalDate dateOfBirth, String phoneNumber, Model.Address address, List<Insurance> insurances) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        DateOfBirth = dateOfBirth;
        PhoneNumber = phoneNumber;
        Address = address;
        Insurances = insurances;
        Reservations = new ArrayList<>();
        extent.add(this);
    }

    public Patient(String firstName, String lastName, Model.Gender gender, LocalDate dateOfBirth, String phoneNumber) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        DateOfBirth = dateOfBirth;
        PhoneNumber = phoneNumber;
        Address = null;
        Insurances = new ArrayList<>();
        Reservations = new ArrayList<>();
        extent.add(this);
    }

    public int GetAge() {
        Period age = Period.between(DateOfBirth, LocalDate.now());
        return age.getYears();
    }

    public void AddAddress(Model.Address address) {
        if(Address == null) {
            Address = address;

            address.AddPatient(this);
        }
    }

    public void AddInsurance(Insurance insurance) {
        if(!Insurances.contains(insurance)) {
            Insurances.add(insurance);

            insurance.AddPatient(this);
        }
    }

    public void AddReservation(Reservation reservation) {
        if(!Reservations.contains(reservation)) {
            Reservations.add(reservation);

            reservation.AddPatient(this);
        }

    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Patient>) stream.readObject();
    }

    @Override
    public String toString() {
        return "Patient{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Gender=" + Gender +
                ", DateOfBirth=" + DateOfBirth +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }

    public static void showExtent() {
        for (Patient object : extent) {
            System.out.println(object);
        }
    }
}
