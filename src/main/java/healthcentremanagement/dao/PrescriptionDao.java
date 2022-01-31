package healthcentremanagement.dao;

import healthcentremanagement.entities.Medicine;
import healthcentremanagement.entities.Patient;
import healthcentremanagement.entities.Prescription;
import healthcentremanagement.entities.Treatment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PrescriptionDao {
    private EntityManager entityManager;

    public PrescriptionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Prescription> retrievePrescriptionList() {
        entityManager.getTransaction().begin();
        String jpql = "select p from Prescription p";
        TypedQuery<Prescription> query = entityManager.createQuery(jpql, Prescription.class);
        List<Prescription> prescriptionList = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptionList;
    }

    public Prescription insertOneRecord(Prescription prescription){
        entityManager.getTransaction().begin();
        entityManager.persist(prescription);
        entityManager.getTransaction().commit();
        return prescription;
    }

    public List<Prescription> findPrescriptionByIdentificationAndTreatment(String identification) {
        entityManager.getTransaction().begin();
        String jpql = "select p from Prescription p  where p.treatment.id in" +
                " (select max(t.id) from Treatment t where t.patient.identification.number = :identification)";
        TypedQuery<Prescription> query = entityManager.createQuery(jpql, Prescription.class);
        query.setParameter("identification", identification);
        List<Prescription> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public List<Prescription> findPrescriptionByIdentificationAndTreatmentdate(String identification, LocalDate treatmentDate) {
        entityManager.getTransaction().begin();
        String jpql = "select p from Prescription p  where p.treatment.treatmentdate = :treatmentdate and " +
                "p.treatment.patient.identification.number = :identification";
        TypedQuery<Prescription> query = entityManager.createQuery(jpql, Prescription.class);
        query.setParameter("identification", identification);
        query.setParameter("treatmentdate", treatmentDate);
        List<Prescription> prescriptions = query.getResultList();
        entityManager.getTransaction().commit();
        return prescriptions;
    }

    public int deleteOneRecord(Treatment treatment, Medicine medicine) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Prescription p" +
                " where  p.treatment = :treatment and p.medicine = :medicine");
        query.setParameter("treatment", treatment);
        query.setParameter("medicine", medicine);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public int updatePrescriptionDose(Treatment treatment, Medicine medicine, int dose, boolean provided) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Prescription p SET p.dose = :dose, p.medicine_provided = :provided " +
                "where p.treatment = :treatment and p.medicine = :medicine");
        query.setParameter("treatment", treatment);
        query.setParameter("dose", dose);
        query.setParameter("provided", provided);
        query.setParameter("medicine", medicine);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

}
