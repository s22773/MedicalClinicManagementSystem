package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Facility class represents a medical facility.
 * It contains information about the facility's name, room number, accessibility, address, and medical appointments.
 */
public class Facility implements Serializable {
    /**
     * A list containing all instances of Facility created.
     */
    public static List<Facility> extent = new ArrayList<Facility>();

    private String Name;
    private int RoomNumber;
    private boolean DisabilitiesFriendly;

    private Address Address;
    private MedicalAppointment MedicalAppointment;

    /**
     * Constructs a Facility object with the specified name, room number, accessibility, and address.
     * Adds the newly created instance to the extent list.
     *
     * @param name                the name of the facility
     * @param roomNumber          the room number of the facility
     * @param disabilitiesFriendly indicates if the facility is disabilities friendly
     * @param address             the address of the facility
     */
    public Facility(String name, int roomNumber, boolean disabilitiesFriendly, Model.Address address) {
        Name = name;
        RoomNumber = roomNumber;
        DisabilitiesFriendly = disabilitiesFriendly;
        Address = address;
        MedicalAppointment = null;
        extent.add(this);
    }

    /**
     * Constructs a Facility object with the specified name, room number, and accessibility.
     * Adds the newly created instance to the extent list.
     *
     * @param name                the name of the facility
     * @param roomNumber          the room number of the facility
     * @param disabilitiesFriendly indicates if the facility is disabilities friendly
     */
    public Facility(String name, int roomNumber, boolean disabilitiesFriendly) {
        Name = name;
        RoomNumber = roomNumber;
        DisabilitiesFriendly = disabilitiesFriendly;
        Address = null;
        MedicalAppointment = null;
        extent.add(this);
    }

    /**
     * Adds an address to the facility if the current address is null.
     * Also adds this facility to the address's facility list if not already present.
     *
     * @param address the address to add
     */
    public void AddAddress(Address address) {
        if(Address == null) {
            Address = address;

            address.AddFacility(this);
        }
    }

    /**
     * Adds a medical appointment to the facility if the current medical appointment is null.
     * Also adds this facility to the medical appointment's facility list if not already present.
     *
     * @param medicalAppointment the medical appointment to add
     */
    public void AddMedicalAppointment(MedicalAppointment medicalAppointment) {
        if(MedicalAppointment == null) {
            MedicalAppointment = medicalAppointment;

            medicalAppointment.AddFacility(this);
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
        extent = (ArrayList<Facility>) stream.readObject();
    }
}
