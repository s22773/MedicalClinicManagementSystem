package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Insurance implements Serializable {
    public static List<Insurance> extent = new ArrayList<Insurance>();

    private InsuranceType InsuranceType;
    private int PolicyNumber;
    private String InsuranceProvider;

    private Patient Patient;

    public Insurance(Model.InsuranceType insuranceType, int policyNumber, String insuranceProvider) {
        InsuranceType = insuranceType;
        PolicyNumber = policyNumber;
        InsuranceProvider = insuranceProvider;
        Patient = null;
        extent.add(this);
    }

    public Insurance(Model.InsuranceType insuranceType, int policyNumber, String insuranceProvider, Model.Patient patient) {
        InsuranceType = insuranceType;
        PolicyNumber = policyNumber;
        InsuranceProvider = insuranceProvider;
        Patient = patient;
        extent.add(this);
    }

    public void AddPatient(Model.Patient patient) {
        if(Patient == null) {
            Patient = patient;

            patient.AddInsurance(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Insurance>) stream.readObject();
    }
}
