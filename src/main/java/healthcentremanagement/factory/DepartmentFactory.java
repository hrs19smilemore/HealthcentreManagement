package healthcentremanagement.factory;

import healthcentremanagement.departments.ChildhealthDepartment;
import healthcentremanagement.departments.ParamedicalDepartment;
import healthcentremanagement.departments.ReceptionDepartment;
import healthcentremanagement.departments.PharmacyDepartment;

public class DepartmentFactory {

    public static Department aquireDepartment(String department) {
        if (department.equals("Childhealth")) {
            return new ChildhealthDepartment();
        } else if (department.equals("Paramedical")) {
            return new ParamedicalDepartment();
        } else if (department.equals("Reception")) {
            return new ReceptionDepartment();
        } else if (department.equals("Pharmacy")) {
            return new PharmacyDepartment();
        } else return null;
    }
}
