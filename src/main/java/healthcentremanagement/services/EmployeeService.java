package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.EmployeeDao;
import healthcentremanagement.entities.Employee;
import healthcentremanagement.entities.Identification;

import javax.persistence.NoResultException;
import java.util.List;

public class EmployeeService {
    EmployeeDao employeeDao = new EmployeeDao(JPAConfiguration.getEntityManager());

    public void returnAllEmployee(){
        List<Employee> employees = employeeDao.retrieveEmployeeList();
        employees.forEach(System.out::println);
    }

    public Employee findEmployeeByIdentification(String identification){
        try{
            return employeeDao.findByIdentification(identification);
        } catch (NoResultException noResultException){
            System.out.println("Employee doesn't exist");
            return null;
        }
    }

    public void insertEmployee(Employee employee){
        System.out.println("Inserted employees: " + employeeDao.insertOneRecord(employee));
    }

    public void deleteEmployee(Identification Identification){
        System.out.println("Employees deleted: " + employeeDao.deleteOneRecord(Identification));
    }

    public void updateEmployee(Identification identification, String adress, String firstname, String lastname, String salary){
        System.out.println("Employees updated: " + employeeDao.updateEmployee(identification, adress, firstname,
                lastname, salary));
    }
}
