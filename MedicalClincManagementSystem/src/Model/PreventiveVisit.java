package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PreventiveVisit extends MedicalAppointment implements Serializable {
    public static List<PreventiveVisit> extent = new ArrayList<PreventiveVisit>();

    private String Observation;
    private String Recommendation;

    private Doctor Doctor;

    private PreventiveVisit(String name, LocalDate dateOfAppointment, boolean remotely, double cost, String observation, String recommendation, Model.Doctor doctor) {
        super(name, dateOfAppointment, remotely, cost);
        Observation = observation;
        Recommendation = recommendation;
        Doctor = doctor;
        extent.add(this);
    }

    public static PreventiveVisit CreatePreventiveVisit(String name, LocalDate dateOfAppointment, boolean remotely, double cost, String observation, String recommendation, Model.Doctor doctor) throws Exception {
        if(doctor == null)
            throw new Exception("The given Doctor does not exist!");

        PreventiveVisit preventiveVisit = new PreventiveVisit(name, dateOfAppointment, remotely, cost, observation, recommendation, doctor);
        doctor.AddPreventiveVisit(preventiveVisit);

        return preventiveVisit;
    }

    @Override
    public double GetActualCost() {
        return 0;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public void setRecommendation(String recommendation) {
        Recommendation = recommendation;
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<PreventiveVisit>) stream.readObject();
    }
}
