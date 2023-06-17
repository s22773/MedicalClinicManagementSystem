package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Address class represents a physical address.
 * It contains information about the city, street, postal code, and related patients and facilities.
 */
public class Address implements Serializable {
    /**
     * A list containing all instances of Address created.
     */
    public static List<Address> extent = new ArrayList<Address>();

    private String City;
    private String Street;
    private String PostalCode;

    private List<Patient> Patients;
    private List<Facility> Facilities;

    /**
     * Constructs an Address object with the specified city, street, and postal code.
     * Initializes the Patients and Facilities lists as empty ArrayLists.
     * Adds the newly created instance to the extent list.
     *
     * @param city       the city of the address
     * @param street     the street of the address
     * @param postalCode the postal code of the address
     */
    public Address(String city, String street, String postalCode) {
        City = city;
        Street = street;
        PostalCode = postalCode;
        Patients = new ArrayList<>();
        Facilities = new ArrayList<>();
        extent.add(this);
    }

    /**
     * Adds a patient to the Patients list associated with this address.
     * Also adds this address to the patient's address list if not already present.
     *
     * @param patient the patient to add
     */
    public void AddPatient(Patient patient) {
        if(!Patients.contains(patient)) {
            Patients.add(patient);

            patient.AddAddress(this);
        }
    }

    /**
     * Adds a facility to the Facilities list associated with this address.
     * Also adds this address to the facility's address list if not already present.
     *
     * @param facility the facility to add
     */
    public void AddFacility(Facility facility) {
        if(!Facilities.contains(facility)) {
            Facilities.add(facility);

            facility.AddAddress(this);
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
     * Reads the extent list from the specified File.
     *
     * @param stream the ObjectInputStream to read from
     * @throws IOException            if an I/O error occurs during reading
     * @throws ClassNotFoundException if the class of a object cannot be found
     */
    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Address>) stream.readObject();
    }

    /**
     * Returns a string representation of the address in the format "City Street PostalCode".
     *
     * @return the string representation of the address
     */
    @Override
    public String toString() {
        return City + " " + Street + " " + PostalCode;
    }
}
