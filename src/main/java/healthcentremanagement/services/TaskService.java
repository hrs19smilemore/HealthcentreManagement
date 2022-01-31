package healthcentremanagement.services;

import healthcentremanagement.config.JPAConfiguration;
import healthcentremanagement.dao.TaskDao;
import healthcentremanagement.entities.Employee;
import healthcentremanagement.entities.Task;
import java.util.List;
import java.util.Set;

public class TaskService {
    TaskDao taskDao = new TaskDao(JPAConfiguration.getEntityManager());

    public void returnAllTasks(){
        List<Task> tasks = taskDao.retrieveTaskList();
        tasks.forEach(System.out::println);
    }

    public List<Task> findTaskByStatus(boolean done){
        return taskDao.findTasksByStatus(done);
    }

    public List<Task> findTaskByIdentificationAndDepartmentAndDescription(String department, Employee employee, String description){
        return taskDao.findTasksByIdentificationAndDepartmentAndDescription(department, employee, description);
    }

    public void insertTask(Task task){
        System.out.println("Inserted tasks: " + taskDao.insertOneRecord(task));
    }

    public void deleteTaskBasedOnStatus(boolean done){
        System.out.println("Tasks deleted: " + taskDao.deleteTaskBasedOnStatus(done));
    }

    public void updateTask(Task task, Employee employee, boolean done){
        System.out.println("Task updated: " + taskDao.updateTaskStatusByIdentificationAndDepartment(task, employee, done));
    }
}
