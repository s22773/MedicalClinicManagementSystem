package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Doctor implements Serializable {
    public static List<Doctor> extent = new ArrayList<Doctor>();

    private String FirstName;
    private String LastName;
    private int WorkInternship;

    private List<Specialization> Specializations;
    private List<PreventiveVisit> PreventiveVisits;
    private List<Surgery> Surgeries;
    private List<MedicalExamination> MedicalExaminations;

    public Doctor(String firstName, String lastName, int workInternship) {
        FirstName = firstName;
        LastName = lastName;
        WorkInternship = workInternship;
        Specializations = new ArrayList<>();
        PreventiveVisits = new ArrayList<>();
        Surgeries = new ArrayList<>();
        MedicalExaminations = new ArrayList<>();
        extent.add(this);
    }

    public void AddSpecialization(Specialization specialization) {
        if(!Specializations.contains(specialization)) {
            Specializations.add(specialization);
        }
    }

    public void AddPreventiveVisit(PreventiveVisit preventiveVisit) {
        if(!PreventiveVisits.contains(preventiveVisit)) {
            PreventiveVisits.add(preventiveVisit);
        }
    }

    public void AddSurgery(Surgery surgery) {
        if(!Surgeries.contains(surgery)) {
            Surgeries.add(surgery);

            surgery.AddDoctor(this);
        }
    }

    public void AddMedicalExamination(MedicalExamination medicalExamination) {
        if(!MedicalExaminations.contains(medicalExamination)) {
            MedicalExaminations.add(medicalExamination);

            medicalExamination.AddDoctor(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Doctor>) stream.readObject();
    }


}
