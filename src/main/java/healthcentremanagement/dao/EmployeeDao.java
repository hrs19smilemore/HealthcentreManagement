package healthcentremanagement.dao;

import healthcentremanagement.entities.Employee;
import healthcentremanagement.entities.Identification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class EmployeeDao {
    EntityManager entityManager;

    public EmployeeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> retrieveEmployeeList() {
        entityManager.getTransaction().begin();

        String jpql = "select e from Employee e";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        List<Employee> employeeList = query.getResultList();
        entityManager.getTransaction().commit();
        return employeeList;
    }

    public List<Employee> retrieveEmployeeListByDepartment(String department) {
        entityManager.getTransaction().begin();

        String jpql = "select e from Employee e join e.tasks ts where ts.department = :department";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        List<Employee> employeeList = query.setParameter("department", department).getResultList();
        entityManager.getTransaction().commit();
        return employeeList;
    }

    public Employee insertOneRecord(Employee employee){
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    public int deleteOneRecord(Identification identification) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Employee e" +
                " where e.identification = :identification");
        query.setParameter("identification", identification);
        int rowsDeleted = query.executeUpdate();
        System.out.println("entities deleted: " + rowsDeleted);
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public Employee findByIdentification(String identification) {
        entityManager.getTransaction().begin();
        String jpql = "select e from Employee e  where e.identification.number = :identification";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        Employee employee = query.setParameter("identification" +
                "", identification).getSingleResult();
        entityManager.getTransaction().commit();
        return employee;
    }

    public int updateEmployee(Identification identification, String adress, String firstname, String lastname, String salary) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Employee e SET e.adress = :adress, e.firstname = :firstname," +
                "e.lastname = :lastname, e.salary = :salary" +
                " where e.identification = :identification");
        query.setParameter("identification", identification);
        query.setParameter("adress", adress);
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        query.setParameter("salary", salary);
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }
}
