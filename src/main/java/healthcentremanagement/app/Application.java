package healthcentremanagement.app;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.facade.HealthcentreFacade;

public class Application {
    public static void main(String[] args) {

        HealthcentreFacade access = new HealthcentreFacade();
        //login
        //access.login();
        //access.addNewAccount();

        //Healthcentre
        //access.updateHealthcentreName(); //works

        //FactoryAndCoRDesignPatterns
        //access.showDepartmentInfo(); //works

        //access.checkPatientStatus();

        //Medicine
        //access.insertNewMedicine(); //works
        //access.depositMedicineUpdate(); //works
        //access.withdrawMedicine(); //works
        //access.deleteMedicine(); //works

        //Appointment
        //access.makeAppointment(); //works
        //access.deleteAppointment(); //works
        //access.updateAppointmentDateAndTime(); //works
        //access.getAllAppointments(); //works

        //Patient
        //access.deletePatient(); //works
        //access.updatePatient(); //works

        //Doctor
        access.insertDoctor(); //works
        //access.deleteDoctor(); //works
        //access.updateDoctor(); /works
        //access.getAllDoctors(); //works

        //Employee
        //access.insertEmployee(); //works
        //access.deleteEmployee(); //works
        //access.updateEmployee(); //works
        //access.getAllEmployee(); //works

        //Treatment
        //access.insertTreatment(); //works
        //access.deleteTreatment(); //works
        //access.updateTreatmentDescription(); //works
        //access.getAllTreatments(); //works

        //Prescription
        //access.insertPrescription(); //works
        //access.deletePrescription(); //works
        //access.updatePrescriptionDoseAndProviding(); //works
        //access.getAllPrescriptions(); //works

        //Task
        //access.insertTask(); //works
        //access.deleteTaskBasedOnStatus(); //works
        //access.updateTaskStatus(); //works
        //access.getAllTasks(); //works

        JPAConfiguration.shutdown();
    }
}