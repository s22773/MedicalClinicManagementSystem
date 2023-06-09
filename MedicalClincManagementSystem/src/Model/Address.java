package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Address implements Serializable {
    public static List<Address> extent = new ArrayList<Address>();

    private String City;
    private String Street;
    private String PostalCode;

    private List<Patient> Patients;
    private List<Facility> Facilities;

    public Address(String city, String street, String postalCode) {
        City = city;
        Street = street;
        PostalCode = postalCode;
        Patients = new ArrayList<>();
        Facilities = new ArrayList<>();
        extent.add(this);
    }

    public void AddPatient(Patient patient) {
        if(!Patients.contains(patient)) {
            Patients.add(patient);

            patient.AddAddress(this);
        }
    }

    public void AddFacility(Facility facility) {
        if(!Facilities.contains(facility)) {
            Facilities.add(facility);

            facility.AddAddress(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Address>) stream.readObject();
    }
}
