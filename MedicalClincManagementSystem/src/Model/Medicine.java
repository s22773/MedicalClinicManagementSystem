package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Medicine implements Serializable {
    public static List<Medicine> extent = new ArrayList<Medicine>();

    private String Name;
    private String Producent;
    private String Dose;
    private String Instruction;

    private List<Prescription> Prescriptions;

    public Medicine(String name, String producent, String dose, String instruction) {
        Name = name;
        Producent = producent;
        Dose = dose;
        Instruction = instruction;
        Prescriptions = new ArrayList<>();
        extent.add(this);
    }

    public void AddPrescription(Prescription prescription) {
        if(!Prescriptions.contains(prescription)) {
            Prescriptions.add(prescription);

            prescription.AddMedicine(this);
        }
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Medicine>) stream.readObject();
    }
}
