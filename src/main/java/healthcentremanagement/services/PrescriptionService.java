package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.PrescriptionDao;
import healthcentremanagement.entities.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionService {
    PrescriptionDao prescriptionDao = new PrescriptionDao(JPAConfiguration.getEntityManager());

    public void returnAllPrescription(){
        List<Prescription> prescriptions = prescriptionDao.retrievePrescriptionList();
        prescriptions.forEach(System.out::println);
    }

    public void insertPrescription(Prescription prescription){
        System.out.println("Inserted prescriptions: " + prescriptionDao.insertOneRecord(prescription));
    }

    public void deletePrescription(Treatment treatment, Medicine medicine){
        System.out.println("Prescriptions deleted: " + prescriptionDao.deleteOneRecord(treatment, medicine));
    }

    public void updatePrescription(Treatment treatment, Medicine medicine, int dose, boolean provided){
        System.out.println("Prescriptions updated: " + prescriptionDao.updatePrescriptionDose( treatment, medicine, dose, provided));
    }
}
