package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Specialization implements Serializable {
    public static List<Specialization> extent = new ArrayList<Specialization>();

    private String Name;
    private LocalDate DateOfAcquisition;

    private Doctor Doctor;

    private Specialization(String name, LocalDate dateOfAcquisition, Model.Doctor doctor) {
        Name = name;
        DateOfAcquisition = dateOfAcquisition;
        Doctor = doctor;
        extent.add(this);
    }

    public static Specialization CreateSpecialization(Doctor doctor, String name, LocalDate dateOfAcquisition) throws Exception {
        if(doctor == null)
            throw new Exception("The given Doctor does not exist!");

        Specialization specialization = new Specialization(name, dateOfAcquisition, doctor);
        doctor.AddSpecialization(specialization);

        return specialization;
    }

    public void AddDoctor(Doctor doctor) {
        this.Doctor = doctor;
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Specialization>) stream.readObject();
    }
}
