package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Technician implements Serializable {
    public static List<Technician> extent = new ArrayList<Technician>();

    private int IdNumber;
    private List<String> RealizedServices;

    private List<MedicalExamination> MedicalExaminations;

    public Technician(int idNumber, List<String> realizedServices) {
        IdNumber = idNumber;
        RealizedServices = realizedServices;
        MedicalExaminations = new ArrayList<>();
        extent.add(this);
    }

    public void AddMedicalExamination(MedicalExamination medicalExamination) {
        if(!MedicalExaminations.contains(medicalExamination)) {
            MedicalExaminations.add(medicalExamination);

            medicalExamination.AddTechnician(this);
        }
    }

    public void AddRealizedService(String service) {
        RealizedServices.add(service);
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Technician>) stream.readObject();
    }
}
