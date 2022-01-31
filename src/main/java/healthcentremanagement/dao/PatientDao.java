package healthcentremanagement.dao;

import healthcentremanagement.entities.Identification;
import healthcentremanagement.entities.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientDao {
    private EntityManager entityManager;

    public PatientDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Patient> retrievePatientList() {
        entityManager.getTransaction().begin();

        String jpql = "select p from Patient p";
        TypedQuery<Patient> query = entityManager.createQuery(jpql, Patient.class);
        List<Patient> patientList = query.getResultList();
        entityManager.getTransaction().commit();
        return patientList;
    }

    public Patient insertOneRecord(Patient patient){
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        return patient;
    }

    public int deleteOneRecord(Identification identification) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Patient p" +
                " where p.identification = :identification");
        query.setParameter("identification", identification);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public List<Patient> findByIdentification(String identification) {
        entityManager.getTransaction().begin();
        String jpql = "select p from Patient p  where p.identification.number = :identification";
        TypedQuery<Patient> query = entityManager.createQuery(jpql, Patient.class);
        List<Patient> patients = query.setParameter("identification" +
                "", identification).getResultList();
        entityManager.getTransaction().commit();
        return patients;
    }

    public int updatePatient(Identification identification, String adress, String contactNumber, String firstname, String lastname) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Patient p SET p.adress = :adress, p.contactnumber = :contactnumber," +
                " p.firstname = :firstname, p.lastname = :lastname " +
                "where p.identification = :identification");
        query.setParameter("identification", identification);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        query.setParameter("adress", adress);
        query.setParameter("contactnumber", contactNumber);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }
}
