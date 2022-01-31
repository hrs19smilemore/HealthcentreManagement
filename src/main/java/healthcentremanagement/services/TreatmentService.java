package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.PrescriptionDao;
import healthcentremanagement.dao.TreatmentDao;
import healthcentremanagement.entities.Patient;
import healthcentremanagement.entities.Prescription;
import healthcentremanagement.entities.Treatment;

import java.time.LocalDate;
import java.util.List;

public class TreatmentService {
    TreatmentDao treatmentDao = new TreatmentDao(JPAConfiguration.getEntityManager());

    public void returnAllTreatment(){
        List<Treatment> treatments = treatmentDao.retrieveTreatmentList();
        treatments.forEach(System.out::println);
    }

    public void insertTreatment(Treatment treatment){
        System.out.println("Inserted treatments: " + treatmentDao.insertOneRecord(treatment));
    }

    public void deleteTreatment(Patient patient, LocalDate treatmentdate){
        System.out.println("Treatments deleted: " + treatmentDao.deleteOneRecord(patient, treatmentdate));
    }

    public void updateTreatmentDescription(Patient patient, String description, LocalDate treatmentdate){
        System.out.println("Treatments updated: " + treatmentDao.updateTreatmentDescription(patient,
                description, treatmentdate));
    }

    public List<Treatment> findTreatmentByPatientIdentificationAndTreatmentDate(String identification, LocalDate treatmentdate){
        return treatmentDao.findTreatmentByIdentificationAndTreatmentdate(identification, treatmentdate);
    }
}
