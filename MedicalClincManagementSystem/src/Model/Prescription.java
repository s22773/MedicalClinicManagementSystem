package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prescription implements Serializable {
    public static List<Prescription> extent = new ArrayList<Prescription>();

    private String Dosage;
    private float Reimbursed;

    private List<Medicine> Medicines;
    private MedicalAppointment MedicalAppointment;

    public Prescription(String dosage, float reimbursed, Model.MedicalAppointment medicalAppointment) {
        Dosage = dosage;
        Reimbursed = reimbursed;
        Medicines = new ArrayList<>();
        MedicalAppointment = medicalAppointment;
        extent.add(this);
    }

    public void AddMedicine(Medicine medicine) {
        if(!Medicines.contains(medicine)) {
            Medicines.add(medicine);
            medicine.AddPrescription(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Prescription>) stream.readObject();
    }
}
