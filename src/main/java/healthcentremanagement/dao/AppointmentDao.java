package healthcentremanagement.dao;

import healthcentremanagement.entities.Appointment;
import healthcentremanagement.entities.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentDao {
    private EntityManager entityManager;

    public AppointmentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Appointment> retrieveAppointmentList() {
        entityManager.getTransaction().begin();

        String jpql = "select a from Appointment a";
        TypedQuery<Appointment> query = entityManager.createQuery(jpql, Appointment.class);
        List<Appointment> appointmentList = query.getResultList();
        entityManager.getTransaction().commit();
        return appointmentList;
    }

    public Appointment insertOneRecord(Appointment appointment){
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
        return appointment;
    }

    public int deleteOneRecord(Patient patient, LocalDate appointmentDate) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Appointment a" +
                " where a.patient = :patient and a.appointmentDate = :appointmentdate");
        query.setParameter("patient", patient);
        query.setParameter("appointmentdate", appointmentDate);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public int updateAppointmentDateAndTime(Patient patient, LocalDate previousAppointmentDate,
                                            LocalDate newAppointmentDate, LocalTime appointmentTime) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Appointment a SET a.appointmentDate = :newAppointmentDate," +
                "a.appointmentTime = :appointmenttime where a.patient = :patient and" +
                " a.appointmentDate = :previousAppointmentDate");
        query.setParameter("patient", patient);
        query.setParameter("newAppointmentDate", newAppointmentDate);
        query.setParameter("previousAppointmentDate", previousAppointmentDate);
        query.setParameter("appointmenttime", appointmentTime);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }


    public List<Appointment> findByIdentificationAndAppointmentdate(Patient patient, LocalDate appointmentDate) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Appointment a join Patient p on p.id = a.patient.id where " +
                "p.identification.number = :identification and a.appointmentDate = :appointmentdate";
        TypedQuery<Appointment> query = entityManager.createQuery(jpql, Appointment.class);
        query.setParameter("identification", patient.getIdentification().getNumber());
        query.setParameter("appointmentdate", appointmentDate);
        List<Appointment> appointments = query.getResultList();
        entityManager.getTransaction().commit();
        return appointments;
    }

    public List<Appointment> findSectionByIdentificationAndAppointmentdate(String identification, LocalDate appointment,
                                                                           String section) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Appointment a join Patient p on a.patient.id = p.id " +
                "join Identification i on p.identification.id = i.id where " +
                "a.section = :section and a.appointmentDate = :appointment and " +
                "i.number = :identification";
        TypedQuery<Appointment> query = entityManager.createQuery(jpql, Appointment.class);
        query.setParameter("identification", identification);
        query.setParameter("appointment", appointment);
        query.setParameter("section", section);
        List<Appointment> records = query.getResultList();
        entityManager.getTransaction().commit();
        return records;
    }

}
