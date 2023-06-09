package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Facility implements Serializable {
    public static List<Facility> extent = new ArrayList<Facility>();

    private String Name;
    private int RoomNumber;
    private boolean DisabilitiesFriendly;

    private Address Address;
    private MedicalAppointment MedicalAppointment;

    public Facility(String name, int roomNumber, boolean disabilitiesFriendly, Model.Address address) {
        Name = name;
        RoomNumber = roomNumber;
        DisabilitiesFriendly = disabilitiesFriendly;
        Address = address;
        MedicalAppointment = null;
        extent.add(this);
    }

    public Facility(String name, int roomNumber, boolean disabilitiesFriendly) {
        Name = name;
        RoomNumber = roomNumber;
        DisabilitiesFriendly = disabilitiesFriendly;
        Address = null;
        MedicalAppointment = null;
        extent.add(this);
    }

    public void AddAddress(Address address) {
        if(Address == null) {
            Address = address;

            address.AddFacility(this);
        }
    }

    public void AddMedicalAppointment(MedicalAppointment medicalAppointment) {
        if(MedicalAppointment == null) {
            MedicalAppointment = medicalAppointment;

            medicalAppointment.AddFacility(this);
        }
    }


    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Facility>) stream.readObject();
    }
}
