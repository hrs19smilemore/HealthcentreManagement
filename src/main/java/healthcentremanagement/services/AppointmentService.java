package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.AppointmentDao;
import healthcentremanagement.entities.Appointment;
import healthcentremanagement.entities.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentService {
    AppointmentDao appointmentDao = new AppointmentDao(JPAConfiguration.getEntityManager());

    public void returnAllAppointments(){
        List<Appointment> appointments = appointmentDao.retrieveAppointmentList();
        appointments.forEach(System.out::println);
    }

    public List<Appointment> findAppointmentByIdentificationAndAppointmentDate(Patient patient, LocalDate previousAppointmentDate){
        return appointmentDao.findByIdentificationAndAppointmentdate(patient, previousAppointmentDate);
    }


    public void insertAppointment(Appointment appointment){
        System.out.println("Inserted appointments: " + appointmentDao.insertOneRecord(appointment));
    }

    public void deleteAppointment(Patient patient, LocalDate appointmentDate){
        System.out.println("Appointments deleted: " + appointmentDao.deleteOneRecord(patient, appointmentDate));
    }

    public void updateAppointment(Patient patient, LocalDate previousAppointmentDate,
                                  LocalDate newAppointmentDate, LocalTime appointmentTime){
        System.out.println("Appointment updated: " + appointmentDao.updateAppointmentDateAndTime(patient,
                previousAppointmentDate, newAppointmentDate, appointmentTime));
    }

    public LocalTime returnTimeForChildhealthSection(healthcentremanagement.chain.of.responsibility.Patient patient){
        String section = "Childhealth";
        return appointmentDao.findSectionByIdentificationAndAppointmentdate(patient.getIdentification(),
                patient.getCurrentdate(), section).get(0).getAppointmentTime();
    }

    public LocalTime returnTimeForFamilyMedicineSection(healthcentremanagement.chain.of.responsibility.Patient patient){
        String section = "FamilyMedicine";
        return appointmentDao.findSectionByIdentificationAndAppointmentdate(patient.getIdentification(),
                patient.getCurrentdate(), section).get(0).getAppointmentTime();
    }
}
