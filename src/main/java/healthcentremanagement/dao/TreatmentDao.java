package healthcentremanagement.dao;

import healthcentremanagement.entities.Medicine;
import healthcentremanagement.entities.Patient;
import healthcentremanagement.entities.Treatment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class TreatmentDao {
    private EntityManager entityManager;

    public TreatmentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Treatment> retrieveTreatmentList() {
        entityManager.getTransaction().begin();

        String jpql = "select t from Treatment t";
        TypedQuery<Treatment> query = entityManager.createQuery(jpql, Treatment.class);
        List<Treatment> treatmentList = query.getResultList();
        entityManager.getTransaction().commit();
        return treatmentList;
    }

    public Treatment insertOneRecord(Treatment treatment){
        entityManager.getTransaction().begin();
        entityManager.persist(treatment);
        entityManager.getTransaction().commit();
        return treatment;
    }

    public List<Treatment> findTreatmentByIdentificationAndTreatmentdate(String identification, LocalDate appointment) {
        entityManager.getTransaction().begin();
        String jpql = "select t from Treatment t  where t.patient.identification.number = :identification" +
                " and t.treatmentdate = :appointment";
        TypedQuery<Treatment> query = entityManager.createQuery(jpql, Treatment.class);
        query.setParameter("identification", identification);
        query.setParameter("appointment", appointment);
        List<Treatment> treatments = query.getResultList();
        entityManager.getTransaction().commit();
        return treatments;
    }

    public int deleteOneRecord(Patient patient, LocalDate treatmentDate) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Treatment t" +
                " where t.patient = :patient and " +
                "t.treatmentdate = :treatmentdate");
        query.setParameter("patient", patient);
        query.setParameter("treatmentdate", treatmentDate);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public int updateTreatmentDescription(Patient patient, String description, LocalDate treatmentDate) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Treatment t SET t.description = :description" +
                " where t.patient = :patient and" +
                " t.treatmentdate = :treatmentdate");
        query.setParameter("patient", patient);
        query.setParameter("treatmentdate", treatmentDate);
        query.setParameter("description", description);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }
}
