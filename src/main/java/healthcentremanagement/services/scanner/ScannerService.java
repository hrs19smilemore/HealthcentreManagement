package healthcentremanagement.services.scanner;

import healthcentremanagement.entities.*;
import healthcentremanagement.factory.Department;
import healthcentremanagement.factory.DepartmentFactory;
import healthcentremanagement.services.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScannerService {
    Scanner scanner = new Scanner(System.in); //put in main
    boolean login;
    LocalDate date;
    LocalTime time;
    String username;

    AccountService accountService = new AccountService();
    AppointmentService appointmentService = new AppointmentService();
    DoctorService doctorService = new DoctorService();
    EmployeeService employeeService = new EmployeeService();
    HealthcentreService healthcentreService = new HealthcentreService();
    TreatmentService treatmentService = new TreatmentService();
    PrescriptionService prescriptionService = new PrescriptionService();
    MedicineStock medicineStock = new MedicineStock();
    PatientService patientService = new PatientService();
    TaskService taskService = new TaskService();
    IdentificationService identificationService = new IdentificationService();
    ExceptionHandlerService exceptionHandlerService = new ExceptionHandlerService();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<Healthcentre> healthcentres;

    //Login
    public boolean loginScanner(){
        System.out.println("Enter username:");
        username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        List<Account> accounts = accountService.findAccountByUsernameAndPassword(username, password);
        if (accounts.isEmpty()){
            System.out.println("login failed");
            login = false;
        } else {
            healthcentres = healthcentreService.getHealthcentre();
            if ((healthcentres.isEmpty())){
                insertHealthcentreScanner();
            } else {
                System.out.println("login successful");
                login = true;
            }
        } return login;
    }

    public void registerAccount(){
        String type = "admin";
        List<Account> findAdminAccount = accountService.findAccountByUsernameAndType(username,type);
        if (findAdminAccount.isEmpty()){
            System.out.println("You need to log into admin account first");
        } else {
            System.out.println("Enter username:");
            String newUsername = scanner.nextLine();
            System.out.println("Enter password:");
            String newPassword = scanner.nextLine();
            Account account = Account.builder().username(newUsername).password(newPassword).build();
            accountService.insertNewAccount(account);
        }
    }

    //Department
    public void showDeptInfoScanner() {
        System.out.println("What department? (Childhealth/Paramedical/Reception/Pharmacy");
        Department department = null;
        if (scanner.hasNextLine()) {
            String typeOfDepartment = scanner.nextLine();
            department = DepartmentFactory.aquireDepartment(typeOfDepartment);
        }
        if (department != null) {
            department.showEmployees();
            department.showTasks();
        } else System.out.println("Enter a department name" +
                " (Childhealth/Paramedical/Reception or Pharmacy)");
    }

    //Medicine
    public void insertNewMedicineScanner() {
        System.out.println("Enter medicine brand");
        String brand = scanner.nextLine();
        System.out.println("Enter medicine description");
        String description = scanner.nextLine();
        System.out.println("Enter medicine name");
        String name = scanner.nextLine();
        System.out.println("Enter medicine current stock");
        int stock = Integer.parseInt(scanner.nextLine());
        Medicine medicine = Medicine.builder().brand(brand).name(name).description(description).stock(stock).build();
        medicineStock.insertNewMedicine(medicine);
    }

    public void deleteMedicineScanner() {
        System.out.println("Enter medicine brand you want to delete");
        String brand = scanner.nextLine();
        System.out.println("Enter medicine name you want to delete");
        String name = scanner.nextLine();
        List<Medicine> medicines = medicineStock.findStockByNameAndBrand(name, brand);
        if (medicines.isEmpty()) {
            System.out.println("Medicine doesn't exist");
        } else {
            medicineStock.deleteMedicine(name, brand);
        }
    }

    public void depositMedicineScanner() {
        System.out.println("Enter medicine name you want to deposit");
        String medicineName = scanner.nextLine();
        System.out.println("Enter medicine brand");
        String brand = scanner.nextLine();
        List<Medicine> medicines = medicineStock.findStockByNameAndBrand(medicineName, brand);
        if (medicines.isEmpty()) {
            System.out.println("Medicine doesn't exist");
        } else {
            System.out.println("Enter the amount of medicine you want to deposit");
            int amount = Integer.parseInt(scanner.nextLine());
            medicineStock.makeDeposit(amount, medicines.get(0));
        }
    }

    public void withdrawMedicineScanner() {
        System.out.println("Enter medicine name you want to withdraw");
        String medicineName = scanner.nextLine();
        System.out.println("Enter medicine brand");
        String brand = scanner.nextLine();
        List<Medicine> medicines = medicineStock.findStockByNameAndBrand(medicineName, brand);
        if (medicines.isEmpty()) {
            System.out.println("Medicine doesn't exist");
        } else {
            System.out.println("Enter the amount of medicine you want to withdraw");
            int amount = Integer.parseInt(scanner.nextLine());
            medicineStock.haveEnoughMedicine(amount, medicines.get(0));
        }
    }

    //Patient
    public healthcentremanagement.chain.of.responsibility.Patient checkPatientStatusScanner() {
        System.out.println("Enter patient id number");
        String id = scanner.nextLine();
        LocalDate dateNow = LocalDate.now();
        return new healthcentremanagement.chain.of.responsibility.Patient(id, dateNow);
    }

    public void deletePatientScanner() {
        System.out.println("Enter Patient id number you want to delete");
        String id = scanner.nextLine();
        List<Identification> identifications = identificationService.findIdentification(id);
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()) {
            System.out.println("Patient doesn't exist");
        } else {
            patientService.deletePatient(identifications.get(0));
        }
    }

    public void updatePatientScanner() {
        System.out.println("Enter Patient id number you want to delete");
        String id = scanner.nextLine();
        List<Identification> identifications = identificationService.findIdentification(id);
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()) {
            System.out.println("Patient doesn't exist");
        } else {
            System.out.println("Enter firstname");
            String firstname = scanner.nextLine();
            System.out.println("Enter lastname");
            String lastname = scanner.nextLine();
            System.out.println("Enter adress");
            String adress = scanner.nextLine();
            System.out.println("Enter contactnumber");
            String contactNumber = scanner.nextLine();
            patientService.updatePatient(identifications.get(0), adress, contactNumber, firstname, lastname);
        }
    }

    //Appointment
    public void insertAppointmentScanner() {
        boolean checkIfCorrect = false;
        System.out.println("Enter patient id number");
        String id = scanner.nextLine();
        System.out.println("Enter patient sex (M/V)");
        String sex = scanner.nextLine();
        System.out.println("Enter firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter lastname");
        String lastname = scanner.nextLine();
        System.out.println("Enter contactnumber");
        String contactnumber = scanner.nextLine();
        System.out.println("Enter patient adress");
        String adress = scanner.nextLine();
        do {
            System.out.println("Enter the appointmentdate (dd/MM/yyyy)");
            date = exceptionHandlerService.checkIfDateEnteredCorrect();
        } while (date == null);
        do {
            System.out.println("Enter the appointmentTime (hh:mm:ss)");
            time = exceptionHandlerService.checkIfTimeEnteredCorrect();
        } while (time == null);
        do {
            System.out.println("Enter section need to visit (Childhealth or Familymedicine)");
            String section = scanner.nextLine();
            if (section.equals("Childhealth") || section.equals("Familymedicine")) {
                checkIfCorrect = true;
                List<Patient> patients = patientService.findPatientByIdentification(id);
                if (patients.isEmpty()) {
                    Identification ic = Identification.builder().number(id).sex(sex).build();
                    identificationService.insertIdentification(ic);
                    Patient p = Patient.builder().adress(adress).contactnumber(contactnumber).firstname(firstname).
                            lastname(lastname).identification(ic).build();
                    patientService.insertPatient(p);
                    Appointment appointment = Appointment.builder().appointmentDate(date).appointmentTime(time).section(section).healthcentre(healthcentreService.getHealthcentre().get(0)).patient(p).build();
                    appointmentService.insertAppointment(appointment);
                } else {
                    Appointment appointment = Appointment.builder().appointmentDate(date).appointmentTime(time).section(section).healthcentre(healthcentreService.getHealthcentre().get(0)).patient(patients.get(0)).build();
                    appointmentService.insertAppointment(appointment);
                }
            } else
                System.out.println("Invalid section (Childhealth or Familymedicine)");
        } while (!checkIfCorrect);
    }

    public void deleteAppointmentScanner() {
        System.out.println("Enter patient id number you want to delete");
        String id = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()){
            System.out.println("Patient doesn't exist");
        } else {
            do {
                System.out.println("Enter the appointmentdate (dd/MM/yyyy)");
                date = exceptionHandlerService.checkIfDateEnteredCorrect();
            } while (date == null);
            List<Appointment> appointment = appointmentService.findAppointmentByIdentificationAndAppointmentDate(id, date);
            if (appointment.isEmpty()) {
                System.out.println("Appointment doesn't exist");
            } else {
                appointmentService.deleteAppointment(patients.get(0), date);
            }
        }
    }

    public void updateAppointmentDateAndTimeScanner() {
        LocalDate oldDate;
        LocalDate newDate;
        System.out.println("Enter patient id number you want to update");
        String id = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()){
            System.out.println("Patient doesn't exist");
        } else {
            do {
                System.out.println("Enter previous appointmentdate (dd/MM/yyyy)");
                oldDate = exceptionHandlerService.checkIfDateEnteredCorrect();
            } while (oldDate == null);
            List<Appointment> appointment = appointmentService.findAppointmentByIdentificationAndAppointmentDate(id, oldDate);
            if (appointment.isEmpty()) {
                System.out.println("Appointment doesn't exist");
            } else {
                do {
                    System.out.println("Enter the new appointmentdate (dd/MM/yyyy)");
                    newDate = exceptionHandlerService.checkIfDateEnteredCorrect();
                } while (newDate == null);
                do {
                    System.out.println("Enter the appointmentTime (hh:mm:ss)");
                    time = exceptionHandlerService.checkIfTimeEnteredCorrect();
                } while (time == null);
                appointmentService.updateAppointment(patients.get(0), oldDate, newDate, time);
            }
        }
    }

    //Doctor
    public void insertDoctorScanner() {
        System.out.println("Enter Doctor id number");
        String id = scanner.nextLine();
        System.out.println("Enter Doctor sex (M/V)");
        String sex = scanner.nextLine();
        System.out.println("Enter firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter lastname");
        String lastname = scanner.nextLine();
        System.out.println("Enter phone number");
        String phone = scanner.nextLine();
        System.out.println("Enter Doctor specialty");
        String specialty = scanner.nextLine();
        Identification ic = Identification.builder().number(id).sex(sex).build();
        identificationService.insertIdentification(ic);
        Doctor doctor = Doctor.builder().firstname(firstname).lastname(lastname).phone(phone).
                specialty(specialty).healthcentre(healthcentres.get(0)).identification(ic).build();
        doctorService.insertDoctor(doctor);
    }

    public void deleteDoctorScanner() {
        System.out.println("Enter Doctor id number you want to delete");
        String id = scanner.nextLine();
        List<Identification> identifications = identificationService.findIdentification(id);
        List<Doctor> doctors = doctorService.findDoctorByIdentification(id);
        if (doctors.isEmpty()) {
            System.out.println("Doctor doesn't exist");
        } else {
            doctorService.deleteDoctor(identifications.get(0));
        }
    }

    public void updateDoctorScanner() {
        System.out.println("Enter Doctor id number you want to delete");
        String id = scanner.nextLine();
        List<Identification> identifications = identificationService.findIdentification(id);
        List<Doctor> doctors = doctorService.findDoctorByIdentification(id);
        if (doctors.isEmpty()) {
            System.out.println("Doctor doesn't exist");
        } else {
            System.out.println("Enter firstname");
            String firstname = scanner.nextLine();
            System.out.println("Enter lastname");
            String lastname = scanner.nextLine();
            System.out.println("Enter phone number");
            String phone = scanner.nextLine();
            System.out.println("Enter Doctor specialty");
            String specialty = scanner.nextLine();
            doctorService.updateDoctor(identifications.get(0), firstname, lastname, phone, specialty);
        }
    }

    //Employee
    public void insertEmployeeScanner() {
        System.out.println("Enter Employee id number");
        String id = scanner.nextLine();
        System.out.println("Enter Employee sex (M/V)");
        String sex = scanner.nextLine();
        System.out.println("Enter Employee adress");
        String adress = scanner.nextLine();
        System.out.println("Enter firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter lastname");
        String lastname = scanner.nextLine();
        System.out.println("Enter salary");
        String salary = scanner.nextLine();
        Identification ic = Identification.builder().number(id).sex(sex).build();
        identificationService.insertIdentification(ic);
        Employee employee = Employee.builder().adress(adress).firstname(firstname).lastname(lastname).salary(salary).
                healthcentre(healthcentreService.getHealthcentre().get(0)).identification(ic).build();
        employeeService.insertEmployee(employee);
    }

    public void deleteEmployeeScanner() {
        System.out.println("Enter Employee id number you want to delete");
        String id = scanner.nextLine();
        List<Identification> identifications = identificationService.findIdentification(id);
        Employee employee = employeeService.findEmployeeByIdentification(id);
        if (employee == null) {
            System.out.println("Incorrect id number entered");
        } else {
            employeeService.deleteEmployee(identifications.get(0));
        }
    }

    public void updateEmployeeScanner() {
        System.out.println("Enter Employee id number you want to delete");
        String id = scanner.nextLine();
        List<Identification> identifications = identificationService.findIdentification(id);
        Employee employee = employeeService.findEmployeeByIdentification(id);
        if (employee == null) {
            System.out.println("Incorrect id number entered");
        } else {
            System.out.println("Enter adress");
            String adress = scanner.nextLine();
            System.out.println("Enter firstname");
            String firstname = scanner.nextLine();
            System.out.println("Enter lastname");
            String lastname = scanner.nextLine();
            System.out.println("Enter salary");
            String salary = scanner.nextLine();
            employeeService.updateEmployee(identifications.get(0), adress, firstname, lastname, salary);
        }
    }

    //Healthcentre
    public void insertHealthcentreScanner() {
        System.out.println("Enter company's name");
        String name = scanner.nextLine();
        System.out.println("Enter company kkfNumber");
        String kkfNumber = scanner.nextLine();
        Healthcentre healthcentre = Healthcentre.builder().kkfNumber(kkfNumber).name(name).build();
        healthcentreService.insertHealthcentre(healthcentre);
    }

    public void updateHealthcentreNameScanner() {
        System.out.println("Enter company kkfNumber you want to update");
        String kkfNumber = scanner.nextLine();
        List<Healthcentre> healthcentres = healthcentreService.findHealthcentreByKkfNumber(kkfNumber);
        if (healthcentres.isEmpty()) {
            System.out.println("incorrect kkfNumber entered");
        } else {
            System.out.println("Enter new company's name");
            String newName = scanner.nextLine();
            healthcentreService.updateHealthcentreName(kkfNumber, newName);
        }
    }

    //Treatment
    public void insertTreatmentscanner() {
        System.out.println("Enter Patient id number");
        String patientId = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(patientId);
        if (patients.isEmpty()) {
            System.out.println("Patient doesn't exist/Make an appointment first");
        } else {
            System.out.println("Enter Doctor id number");
            String doctorId = scanner.nextLine();
            List<Doctor> doctors = doctorService.findDoctorByIdentification(doctorId);
            if (doctors.isEmpty()) {
                System.out.println("Doctor doesn't exist");
            } else {
                System.out.println("Enter treatment description");
                String description = scanner.nextLine();
                do {
                    System.out.println("Enter the treatmentdate (dd/MM/yyyy)");
                    date = exceptionHandlerService.checkIfDateEnteredCorrect();
                } while (date == null);
                Treatment treatment = Treatment.builder().description(description).treatmentdate(date).doctor(doctors.get(0)).
                        patient(patients.get(0)).build();
                treatmentService.insertTreatment(treatment);
            }
        }
    }

    public void deleteTreatmentScanner() {
        System.out.println("Enter patient id number");
        String id = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()){
            System.out.println("Patient doesn't exist");
        } else {
            do {
                System.out.println("Enter the treatmentdate (dd/MM/yyyy)");
                date = exceptionHandlerService.checkIfDateEnteredCorrect();
            } while (date == null);
            List<Treatment> treatments = treatmentService.findTreatmentByPatientIdentificationAndTreatmentDate(id, date);
            if (treatments.isEmpty()) {
                System.out.println("Treatment doesn't exist");
            } else {
                treatmentService.deleteTreatment(patients.get(0), date);
            }
        }
    }

    public void updateTreatmentDescriptionScanner() {
        System.out.println("Enter patient id");
        String id = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()){
            System.out.println("Patient doesn't exist");
        } else{
            do {
                System.out.println("Enter treatmentdate (dd//MM/yyyy)");
                date = exceptionHandlerService.checkIfDateEnteredCorrect();
            } while (date == null);
            List<Treatment> treatments = treatmentService.findTreatmentByPatientIdentificationAndTreatmentDate(id, date);
            if (treatments.isEmpty()){
                System.out.println("Treatment doesn't exist");
            } else {
                System.out.println("Enter new treatment description");
                String description = scanner.nextLine();
                treatmentService.updateTreatmentDescription(patients.get(0), description, date);
            }
        }
    }

    //Prescription
    public void insertPrescriptionScanner() {
        System.out.println("Enter Patient id number");
        String id = scanner.nextLine();
        do {
            System.out.println("Enter patient treatmentdate (dd/MM/yyyy)");
            date = exceptionHandlerService.checkIfDateEnteredCorrect();
        }while (date == null);
        List<Treatment> treatments = treatmentService.findTreatmentByPatientIdentificationAndTreatmentDate(id, date);
        if (treatments.isEmpty()) {
            System.out.println("Treatment doesn't exist");
        } else
            System.out.println("Enter medicine name prescribed");
        String name = scanner.nextLine();
        System.out.println("Enter medicine brand");
        String brand = scanner.nextLine();
        List<Medicine> medicines = medicineStock.findStockByNameAndBrand(name, brand);
        if (medicines.isEmpty()) {
            System.out.println("Medicine doesn't exist");
        } else {
            System.out.println("Enter dose given for medicine");
            int dose = Integer.parseInt(scanner.nextLine());
            System.out.println("Medicine provided to the patient?? (true/false)");
            boolean provided = Boolean.parseBoolean(scanner.nextLine());
            Prescription prescription = Prescription.builder().dose(dose).medicine_provided(provided).medicine(medicines.get(0)).
                    treatment(treatments.get(0)).build();
            prescriptionService.insertPrescription(prescription);
        }
    }

    public void deletePrescriptionScanner() {
        System.out.println("Enter patient id number");
        String id = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()){
            System.out.println("Patient doesn't exist");
        } else {
            do {
                System.out.println("Enter patient treatmentdate (dd/MM/yyyy)");
                date = exceptionHandlerService.checkIfDateEnteredCorrect();
            } while (date == null);
            List<Treatment> treatments = treatmentService.findTreatmentByPatientIdentificationAndTreatmentDate(id, date);
            if (treatments.isEmpty()) {
                System.out.println("Treatment doesn't exist");
            } else
                System.out.println("Enter medicine name prescribed");
            String name = scanner.nextLine();
            System.out.println("Enter medicine brand");
            String brand = scanner.nextLine();
            List<Medicine> medicines = medicineStock.findStockByNameAndBrand(name, brand);
            if (medicines.isEmpty()) {
                System.out.println("Medicine doesn't exist");
            } else {
                prescriptionService.deletePrescription(treatments.get(0), medicines.get(0));
            }
        }
    }

    public void updatePrescriptionDoseAndProvidingScanner() {
        System.out.println("Enter patient id number");
        String id = scanner.nextLine();
        List<Patient> patients = patientService.findPatientByIdentification(id);
        if (patients.isEmpty()){
            System.out.println("Patient doesn't exist");
        } else {
            do {
                System.out.println("Enter the patient treatmentdate (dd/MM/yyyy)");
                date = exceptionHandlerService.checkIfDateEnteredCorrect();
            } while (date == null);
            List<Treatment> treatments = treatmentService.findTreatmentByPatientIdentificationAndTreatmentDate(id, date);
            if (treatments.isEmpty()) {
                System.out.println("Treatment doesn't exist");
            } else
                System.out.println("Enter medicine name prescribed");
            String name = scanner.nextLine();
            System.out.println("Enter medicine brand");
            String brand = scanner.nextLine();
            List<Medicine> medicines = medicineStock.findStockByNameAndBrand(name, brand);
            if (medicines.isEmpty()) {
                System.out.println("Medicine doesn't exist");
            } else {
                System.out.println("Enter new dose given for medicine");
                int dose = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter if medicine is provided to the patient (true/false)");
                boolean provided = Boolean.parseBoolean(scanner.nextLine());
                prescriptionService.updatePrescription(treatments.get(0), medicines.get(0), dose, provided);
            }
        }
    }

    //Task
    public void insertTaskScanner() {
        Set<Employee> employees = new HashSet<>();
        String decision;
        boolean checkIfCorrect = false;
        do {
            System.out.println("Enter employee id number that the task is assigned to");
            String id = scanner.nextLine();
            Employee employee = employeeService.findEmployeeByIdentification(id);
            employees.add(employee);
            if (employee == null) {
                System.out.println("Incorrect id number entered\n" +
                        "Enter employee id number that the task is assigned to");
            } else
                System.out.println("Add another employee to the task? (yes/no)");
                decision = scanner.nextLine();
        } while (decision.equalsIgnoreCase("yes"));
        do {
            System.out.println("Enter department for the task (Childhealth/Paramemedical/Pharmacy or Reception)");
            String department = scanner.nextLine();
            if (department.equals("Childhealth") || department.equals("Paramemedical") || department.equals("Pharmacy") || department.equals("Reception")) {
                checkIfCorrect = true;
                System.out.println("Enter task description");
                String description = scanner.nextLine();
                System.out.println("Is task finished? (true/false)");
                boolean done = Boolean.parseBoolean(scanner.nextLine());
                Task task = Task.builder().department(department).description(description).done(done).employees(employees).build();
                taskService.insertTask(task);
            } else
                System.out.println("Invalid department (Childhealth/Paramemedical/Pharmacy or Reception)");
        } while (!checkIfCorrect);
    }

    public void deleteTaskBasedOnStatusScanner() {
        System.out.println("Delete task that are finished? (true/false)");
        boolean done = Boolean.parseBoolean(scanner.nextLine());
        taskService.deleteTaskBasedOnStatus(done);
    }
    public void updateTaskStatusScanner(){
        boolean checkIfcorrect = false;
        System.out.println("Enter employee id that is assigned to the task");
        String id = scanner.nextLine();
        Employee employee = employeeService.findEmployeeByIdentification(id);
        if (employee == null){
            System.out.println("Employee doesn't exist");
        } else {
            do {
                System.out.println("Enter department that the task is for (Childhealth/Paramedical/Reception or Pharmacy)");
                String department = scanner.nextLine();
                if (department.equals("Childhealth") || department.equals("Paramedical") || department.equals("Reception") || department.equals("Pharmacy")) {
                    checkIfcorrect = true;
                    System.out.println("Enter task description");
                    String description = scanner.nextLine();
                    List<Task> tasks = taskService.findTaskByIdentificationAndDepartmentAndDescription(department,
                            employee, description);
                    if (tasks.isEmpty()) {
                        System.out.println("Task doesn't exist");
                    } else {
                        System.out.println("Is the chosen task finished? (true/false)");
                        boolean done = Boolean.parseBoolean(scanner.nextLine());
                        taskService.updateTask(tasks.get(0), employee, done);
                    }
                } else
                    System.out.println("Invalid department (Childhealth/Paramedical/Reception or Pharmacy)");
            } while (!checkIfcorrect);
        }
    }
}
