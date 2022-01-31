package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.PatientDao;
import healthcentremanagement.entities.Identification;
import healthcentremanagement.entities.Patient;

import java.util.List;

public class PatientService {
    PatientDao patientDao = new PatientDao(JPAConfiguration.getEntityManager());
    public boolean checkIfPatientExist(healthcentremanagement.chain.of.responsibility.Patient patient){
        if (patientDao.findByIdentification(patient.getIdentification()).isEmpty())
            return false;
        else return true;
    }

    public void insertPatient(Patient patient){
        patientDao.insertOneRecord(patient);
    }

    public void deletePatient(Identification identification){
        System.out.println("Patient deleted: " + patientDao.deleteOneRecord(identification));
    }

    public void updatePatient(Identification identification, String adress, String contactnumber, String firstname,
                              String lastname){
        System.out.println("Patients updated: " + patientDao.updatePatient(identification, adress, contactnumber,
                firstname, lastname));
    }

    public List<Patient> findPatientByIdentification(String identification){
        return patientDao.findByIdentification(identification);
    }
}
