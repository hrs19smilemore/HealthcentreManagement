package healthcentremanagement.adapter;

import healthcentremanagement.entities.Patient;

public interface PatientAdapter {
    Patient getPatientEntity();
    healthcentremanagement.chain.of.responsibility.Patient getPatientCOR();
}
