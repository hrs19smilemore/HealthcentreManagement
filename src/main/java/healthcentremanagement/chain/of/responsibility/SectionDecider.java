package healthcentremanagement.chain.of.responsibility;

import healthcentremanagement.adapter.PatientAdapter;
import healthcentremanagement.adapter.PatientAdapterImplementation;
import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.PrescriptionDao;
import healthcentremanagement.dao.AppointmentDao;
import healthcentremanagement.dao.TreatmentDao;

import java.time.LocalDate;

public class SectionDecider {
    AppointmentDao appointmentDao = new AppointmentDao(JPAConfiguration.getEntityManager());
    PrescriptionDao prescriptionDao = new PrescriptionDao(JPAConfiguration.getEntityManager());
    TreatmentDao treatmentDao = new TreatmentDao(JPAConfiguration.getEntityManager());


    public boolean haveappointment(Patient patient) {
        PatientAdapter adapter = new PatientAdapterImplementation(patient);
        healthcentremanagement.entities.Patient patientEntity = adapter.getPatientEntity();
        if (appointmentDao.findByIdentificationAndAppointmentdate(patientEntity,
                patient.getCurrentdate()).isEmpty()) {
            return false;
        } else
            return true;
    }

    public boolean beAtChildhealthSection(Patient patient) {
        String section = "Childhealth";
        LocalDate ld = LocalDate.now();
        if (!(treatmentDao.findTreatmentByIdentificationAndTreatmentdate(patient.getIdentification(),ld).isEmpty()) ||
                appointmentDao.findSectionByIdentificationAndAppointmentdate(patient.getIdentification(),
                    patient.getCurrentdate(), section).isEmpty()) {
            return false;
        } else return true;
    }

    public boolean beAtFamilyMedicineSection(Patient patient) {
        String section = "Familymedicine";
        if (!(treatmentDao.findTreatmentByIdentificationAndTreatmentdate(patient.getIdentification(),patient.getCurrentdate()).isEmpty()) ||
                appointmentDao.findSectionByIdentificationAndAppointmentdate(patient.getIdentification(),
                patient.getCurrentdate(), section).isEmpty()) {
            return false;
        } else return true;
    }

    public boolean beAtPharmacySection(Patient patient){
        return prescriptionDao.findPrescriptionByIdentificationAndTreatment(patient.getIdentification()).get(0).isMedicine_provided();
    }
}
