package healthcentremanagement.factory;

import healthcentremanagement.entities.Employee;
import healthcentremanagement.entities.Task;

import java.util.List;

public abstract class Department {

    private String name;
    private List<Employee> employees;
    private List<Task> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void showEmployees(){
        System.out.println("Employees:\n" + employees);
    }

    public void showTasks(){
        System.out.println("Tasks:\n" + tasks);
    }
}
