package healthcentremanagement.dao;

import healthcentremanagement.entities.Employee;
import healthcentremanagement.entities.Identification;
import healthcentremanagement.entities.Task;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class TaskDao {
    EntityManager entityManager;

    public TaskDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Task> retrieveTaskList() {
        entityManager.getTransaction().begin();
        String jpql = "select t from Task t";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        List<Task> taskList = query.getResultList();
        entityManager.getTransaction().commit();
        return taskList;
    }

    public List<Task> retrieveTaskListByDepartment(String department) {
        entityManager.getTransaction().begin();

        String jpql = "select t from Task t where t.department = :department";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        List<Task> taskList = query.setParameter("department", department).getResultList();
        entityManager.getTransaction().commit();
        return taskList;
    }

    public Task insertOneRecord(Task task){
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        return task;
    }

    public int deleteTaskBasedOnStatus(boolean done) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Task t" +
                " where t.done = :done");
        query.setParameter("done", done);
        int rowsDeleted = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsDeleted;
    }

    public List<Task> findTasksByStatus(boolean done) {
        entityManager.getTransaction().begin();
        String jpql = "select t from Task t  where t.done = :done";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        query.setParameter("done", done);
        List <Task> taskList = query.getResultList();
        entityManager.getTransaction().commit();
        return taskList;
    }

    public int updateTaskStatusByIdentificationAndDepartment(Task task, Employee employee, boolean done) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Task t SET t.done = :done, t.description = :description " +
                "where (select e from Employee e) = :employee and t.department = :department");
        query.setParameter("done", done);
        query.setParameter("description", task.getDescription());
        query.setParameter("employee", employee);
        query.setParameter("department", task.getDepartment());
        int rowsUpdated = query.executeUpdate();
        entityManager.getTransaction().commit();
        return rowsUpdated;
    }

    public List<Task> findTasksByIdentificationAndDepartmentAndDescription(String department, Employee employee, String description) {
        entityManager.getTransaction().begin();
        String jpql = "select t from Task t  where (select e from Employee e) = :employee and t.department = :department " +
                "and t.description = :description";
        TypedQuery<Task> query = entityManager.createQuery(jpql, Task.class);
        query.setParameter("description", description);
        query.setParameter("department", department);
        query.setParameter("employee", employee);
        List <Task> taskList = query.getResultList();
        entityManager.getTransaction().commit();
        return taskList;
    }
}
