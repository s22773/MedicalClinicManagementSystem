package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalExamination extends MedicalAppointment implements Serializable {
    public static List<MedicalExamination> extent = new ArrayList<MedicalExamination>();

    private String ExaminationName;
    private LocalDate DateOfExamination;
    private String ExaminationResult;

    private List<Doctor> Doctors;
    private List<Technician> Technicians;

    public MedicalExamination(String name, LocalDate dateOfAppointment, boolean remotely, double cost, String examinationName, LocalDate dateOfExamination) {
        super(name, dateOfAppointment, remotely, cost);
        ExaminationName = examinationName;
        DateOfExamination = dateOfExamination;
        ExaminationResult = null;
        Doctors = new ArrayList<>();
        Technicians = new ArrayList<>();
        extent.add(this);
    }

    public MedicalExamination(String name, LocalDate dateOfAppointment, boolean remotely, double cost, String examinationName, LocalDate dateOfExamination, String examinationResult) {
        super(name, dateOfAppointment, remotely, cost);
        ExaminationName = examinationName;
        DateOfExamination = dateOfExamination;
        ExaminationResult = examinationResult;
    }

    public MedicalExamination(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, Model.Facility facility, String examinationName, LocalDate dateOfExamination, String examinationResult) {
        super(name, dateOfAppointment, remotely, cost, reservation, facility);
        ExaminationName = examinationName;
        DateOfExamination = dateOfExamination;
        ExaminationResult = examinationResult;
    }

    public MedicalExamination(String name, LocalDate dateOfAppointment, boolean remotely, double cost, Model.Reservation reservation, String examinationName, LocalDate dateOfExamination, String examinationResult) {
        super(name, dateOfAppointment, remotely, cost, reservation);
        ExaminationName = examinationName;
        DateOfExamination = dateOfExamination;
        ExaminationResult = examinationResult;
    }

    public void AddExaminationResult(String examinationResult) {
        ExaminationResult = examinationResult;
    }

    public void AddDoctor(Doctor doctor) {
        if(!Doctors.contains(doctor)) {
            Doctors.add(doctor);

            doctor.AddMedicalExamination(this);
        }
    }

    public void AddTechnician(Technician technician) {
        if(!Technicians.contains(technician)) {
            Technicians.add(technician);

            technician.AddMedicalExamination(this);
        }
    }

    @Override
    public double GetActualCost() {
        return 0;
    }

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<MedicalExamination>) stream.readObject();
    }
}
