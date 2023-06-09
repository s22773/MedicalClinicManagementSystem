package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Surgery extends MedicalAppointment implements Serializable {
    public static List<Surgery> extent = new ArrayList<Surgery>();

    private int Duration;
    private String Anesthesia;

    private List<Doctor> Doctors;

    public Surgery(String name, LocalDate dateOfAppointment, boolean remotely, double cost, int duration, String anesthesia) {
        super(name, dateOfAppointment, remotely, cost);
        Duration = duration;
        Anesthesia = anesthesia;
        Doctors = new ArrayList<>();
        extent.add(this);
    }

    @Override
    public double GetActualCost() {
        return 0;
    }

    public void ModifyDuration(int newDuration) {
        this.Duration = newDuration;
    }

    public void AddDoctor(Doctor doctor) {
        if(!Doctors.contains(doctor)) {
            Doctors.add(doctor);

            doctor.AddSurgery(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Surgery>) stream.readObject();
    }
}
