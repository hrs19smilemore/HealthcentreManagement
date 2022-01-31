package healthcentremanagement.departments;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.EmployeeDao;
import healthcentremanagement.dao.TaskDao;
import healthcentremanagement.entities.Employee;
import healthcentremanagement.entities.Task;
import healthcentremanagement.factory.Department;

import java.util.List;

public class PharmacyDepartment extends Department {
    EmployeeDao employeeDao = new EmployeeDao(JPAConfiguration.getEntityManager());
    TaskDao taskDao = new TaskDao(JPAConfiguration.getEntityManager());

    List<Employee> employees = employeeDao.retrieveEmployeeListByDepartment("Pharmacy");
    List<Task> tasks = taskDao.retrieveTaskListByDepartment("Pharmacy");

    public PharmacyDepartment(){
        setName("Pharmacy");
        setEmployees(employees);
        setTasks(tasks);
    }
}
