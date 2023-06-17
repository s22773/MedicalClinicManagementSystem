package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a patient.
 */
public class Patient implements Serializable {
    /**
     * A list that holds all instances of Patient.
     */
    public static List<Patient> extent = new ArrayList<Patient>();

    private String FirstName;
    private String LastName;
    private Gender Gender;
    private LocalDate DateOfBirth;
    private String PhoneNumber;

    private Address Address;
    private List<Insurance> Insurances;
    private List<Reservation> Reservations;

    /**
     * Constructs a Patient object with the specified parameters.
     *
     * @param firstName    The first name of the patient.
     * @param lastName     The last name of the patient.
     * @param gender       The gender of the patient.
     * @param dateOfBirth  The date of birth of the patient.
     * @param phoneNumber  The phone number of the patient.
     * @param address      The address of the patient.
     * @param insurances   The insurances associated with the patient.
     */
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

    /**
     * Constructs a Patient object with the specified parameters.
     *
     * @param firstName    The first name of the patient.
     * @param lastName     The last name of the patient.
     * @param gender       The gender of the patient.
     * @param dateOfBirth  The date of birth of the patient.
     * @param phoneNumber  The phone number of the patient.
     */
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

    /**
     * Calculates and returns the age of the patient.
     *
     * @return The age of the patient.
     */
    public int GetAge() {
        Period age = Period.between(DateOfBirth, LocalDate.now());
        return age.getYears();
    }

    /**
     * Adds an address to the patient.
     *
     * @param address The address to add.
     */
    public void AddAddress(Model.Address address) {
        if(Address == null) {
            Address = address;

            address.AddPatient(this);
        }
    }

    /**
     * Adds an insurance to the patient.
     *
     * @param insurance The insurance to add.
     */
    public void AddInsurance(Insurance insurance) {
        if(!Insurances.contains(insurance)) {
            Insurances.add(insurance);

            insurance.AddPatient(this);
        }
    }

    /**
     * Adds a reservation to the patient.
     *
     * @param reservation The reservation to add.
     */
    public void AddReservation(Reservation reservation) {
        if(!Reservations.contains(reservation)) {
            Reservations.add(reservation);

            reservation.AddPatient(this);
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
        extent = (ArrayList<Patient>) stream.readObject();
    }

    /**
     * Returns the string representation of the patient.
     *
     * @return The string representation of the patient.
     */
    @Override
    public String toString() {
        return FirstName + " " + LastName;
    }

    /**
     * Returns the first name of the patient.
     *
     * @return The first name of the patient.
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Returns the last name of the patient.
     *
     * @return The last name of the patient.
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Returns the gender of the patient.
     *
     * @return The gender of the patient.
     */
    public Model.Gender getGender() {
        return Gender;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return The date of birth of the patient.
     */
    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * Returns the phone number of the patient.
     *
     * @return The phone number of the patient.
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    /**
     * Returns the address of the patient.
     *
     * @return The address of the patient.
     */
    public Model.Address getAddress() {
        return Address;
    }

    /**
     * Displays the extent of the Patient class.
     */
    public static void showExtent() {

        System.out.println("Extent of the class: " + Patient.class.getName());

        for (Patient patient : extent) {
            System.out.println(patient);
        }
    }
}
