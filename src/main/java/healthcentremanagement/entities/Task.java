package healthcentremanagement.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "task", schema = "healthcentremanagement")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private int id;
    @Column(name = "department")
    private String department;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "done", nullable = false)
    private boolean done;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_task",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    private Set<Employee> employees;


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}
