package healthcentremanagement.adapter;

import healthcentremanagement.entities.Identification;
import healthcentremanagement.entities.Patient;

import java.time.LocalDate;

public class PatientAdapterImplementation implements PatientAdapter{
    private Patient patientEntity;
    private healthcentremanagement.chain.of.responsibility.Patient patientCor;

    public PatientAdapterImplementation(Patient patientEntity) {
        this.patientEntity = patientEntity;
    }

    public PatientAdapterImplementation(healthcentremanagement.chain.of.responsibility.Patient patientCor) {
        this.patientCor = patientCor;
    }

    @Override
    public Patient getPatientEntity() {
        Identification id = new Identification(patientCor.getIdentification());
        Patient patient = new Patient(id);
        return patient;
    }

    @Override
    public healthcentremanagement.chain.of.responsibility.Patient getPatientCOR() {
        healthcentremanagement.chain.of.responsibility.Patient patient = new healthcentremanagement.chain.of.responsibility.Patient(patientEntity.getIdentification().getNumber(), LocalDate.now());
        return patient;
    }
}
