package healthcentremanagement.dao;

import healthcentremanagement.entities.Doctor;
import healthcentremanagement.entities.Identification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class DoctorDao {
    private EntityManager entityManager;

    public DoctorDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Doctor> retrieveDoctorList() {
        entityManager.getTransaction().begin();

        String jpql = "select d from Doctor d";
        TypedQuery<Doctor> query = entityManager.createQuery(jpql, Doctor.class);
        List<Doctor> doctorList = query.getResultList();
        entityManager.getTransaction().commit();
        return doctorList;
    }

    public Doctor insertOneRecord(Doctor doctor){
        entityManager.getTransaction().begin();
        entityManager.persist(doctor);
        entityManager.getTransaction().commit();
        return doctor;
    }

    public int deleteOneRecord(Identification identification) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Doctor d" +
                " where d.identification = :identification");
        query.setParameter("identification", identification);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public List<Doctor> findByIdentification(String identification) {
        entityManager.getTransaction().begin();
        String jpql = "select d from Doctor d  where d.identification.number = :identification";
        TypedQuery<Doctor> query = entityManager.createQuery(jpql, Doctor.class);
        List<Doctor> doctors = query.setParameter("identification", identification).getResultList();
        entityManager.getTransaction().commit();
        return doctors;
    }

    public int updateDoctor(Identification identification, String firstname, String lastname, String phone, String specialty) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Doctor d SET d.firstname = :firstname," +
                "d.lastname = :lastname, d.phone = :phone, d.specialty = :specialty" +
                " where d.identification = :identification");
        query.setParameter("identification", identification);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        query.setParameter("phone", phone);
        query.setParameter("specialty", specialty);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }
}
