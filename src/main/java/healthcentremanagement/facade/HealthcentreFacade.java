package healthcentremanagement.facade;

import healthcentremanagement.chain.of.responsibility.SectionChain;
import healthcentremanagement.services.*;
import healthcentremanagement.services.scanner.ScannerService;
import java.time.LocalDate;

public class HealthcentreFacade {
    private boolean login = false;

    AccountService unameChecker;
    AccountService pwordChecker;
    MedicineStock medChecker;
    AccountService loginChecker;
    SectionChain patientChecker;
    ScannerService scannerService;
    AppointmentService appointmentService;
    DoctorService doctorService;
    EmployeeService employeeService;
    HealthcentreService healthcentreService;
    TreatmentService treatmentService;
    PrescriptionService prescriptionService;
    TaskService taskService;



    public HealthcentreFacade() {
        unameChecker = new AccountService();
        pwordChecker = new AccountService();
        loginChecker = new AccountService();
        medChecker = new MedicineStock();
        patientChecker = new SectionChain();
        scannerService = new ScannerService();
        appointmentService = new AppointmentService();
        doctorService = new DoctorService();
        employeeService = new EmployeeService();
        healthcentreService = new HealthcentreService();
        treatmentService = new TreatmentService();
        prescriptionService = new PrescriptionService();
        taskService = new TaskService();
    }

    public void login(String username, String password) {
        login = scannerService.loginScanner(username, password);
    }

    public void addNewAccount() {
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
        scannerService.registerAccount();
    }

    //Medicine
    public void insertNewMedicine(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertNewMedicineScanner();
    }
    public void deleteMedicine(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deleteMedicineScanner();
    }
    public void withdrawMedicine() {
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.withdrawMedicineScanner();
        }
    public void depositMedicineUpdate() {
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.depositMedicineScanner();
            System.out.println("Transaction complete\n");
        }

    //Department
    public void showDepartmentInfo() {
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.showDeptInfoScanner();
        }

    public void checkPatientStatus(){
        LocalDate dateNow = LocalDate.now();
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else {
            patientChecker.firstSectionHandler.check(scannerService.checkPatientStatusScanner());
        }
    }

    //Appointment
    public void getAllAppointments(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            appointmentService.returnAllAppointments();
    }
    public void makeAppointment(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertAppointmentScanner();
    }
    public void deleteAppointment(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deleteAppointmentScanner();
    }
    public void updateAppointmentDateAndTime(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updateAppointmentDateAndTimeScanner();
    }

    //Doctor
    public void getAllDoctors(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            doctorService.returnAllDoctors();
    }
    public void insertDoctor(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertDoctorScanner();
    }
    public void deleteDoctor(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deleteDoctorScanner();
    }
    public void updateDoctor(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updateDoctorScanner();
    }

    //Employee
    public void getAllEmployee(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            employeeService.returnAllEmployee();
    }
    public void insertEmployee(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertEmployeeScanner();
    }
    public void deleteEmployee(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deleteEmployeeScanner();
    }
    public void updateEmployee(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updateEmployeeScanner();
    }

    //Healthcentre
    public void updateHealthcentreName(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updateHealthcentreNameScanner();
    }

    //Patient
    public void deletePatient(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deletePatientScanner();
    }
    public void updatePatient(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updatePatientScanner();
    }

    //Treatment
    public void getAllTreatments(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            treatmentService.returnAllTreatment();
    }
    public void insertTreatment(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertTreatmentscanner();
    }
    public void deleteTreatment(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deleteTreatmentScanner();
    }
    public void updateTreatmentDescription(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updateTreatmentDescriptionScanner();
    }

    //Prescription
    public void getAllPrescriptions(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            prescriptionService.returnAllPrescription();
    }
    public void insertPrescription(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertPrescriptionScanner();
    }
    public void deletePrescription(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deletePrescriptionScanner();
    }
    public void updatePrescriptionDoseAndProviding(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updatePrescriptionDoseAndProvidingScanner();
    }

    //Task
    public void getAllTasks(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            taskService.returnAllTasks();
    }
    public void insertTask(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.insertTaskScanner();
    }
    public void deleteTaskBasedOnStatus(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.deleteTaskBasedOnStatusScanner();
    }
    public void updateTaskStatus(){
        if (loginChecker.checkIfLoggedIn(login)) {
            System.out.println("You need to login first");
        } else
            scannerService.updateTaskStatusScanner();
    }
}
