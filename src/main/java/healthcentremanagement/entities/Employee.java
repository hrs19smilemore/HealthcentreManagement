package healthcentremanagement.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee", schema = "healthcentremanagement")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "adress")
    private String adress;
    @Column(name = "salary", nullable = false)
    private String salary;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identification_id", unique = true, nullable = false)
    private Identification identification;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "healthcentre_id", referencedColumnName = "healthcentreId")
    private Healthcentre healthcentre;

    @ManyToMany(mappedBy = "employees")
    private Set<Task> tasks;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", adress='" + adress + '\'' +
                ", salary='" + salary + '\'' +
                ", identification=" + identification +
                ", tasks=" + tasks +
                '}';
    }
}
