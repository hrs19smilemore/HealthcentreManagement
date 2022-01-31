package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.DoctorDao;
import healthcentremanagement.entities.Doctor;
import healthcentremanagement.entities.Identification;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import javax.print.Doc;
import java.util.List;

public class DoctorService {
    DoctorDao doctorDao = new DoctorDao(JPAConfiguration.getEntityManager());

    public void returnAllDoctors(){
        List<Doctor> doctors = doctorDao.retrieveDoctorList();
        doctors.forEach(System.out::println);
    }

    public List<Doctor> findDoctorByIdentification(String identification){
        return doctorDao.findByIdentification(identification);
    }

    public void insertDoctor(Doctor doctor){
        System.out.println("Inserted doctors: " + doctorDao.insertOneRecord(doctor));
    }

    public void deleteDoctor(Identification identification){
        System.out.println("Doctors deleted: " + doctorDao.deleteOneRecord(identification));
    }

    public void updateDoctor(Identification identification, String firstname, String lastname, String phone, String specialty){
        System.out.println("Doctors updated: " + doctorDao.updateDoctor(identification, firstname, lastname,
                phone, specialty));
    }
}
