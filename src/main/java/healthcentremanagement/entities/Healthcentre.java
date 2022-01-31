package healthcentremanagement.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "healthcentre", schema = "healthcentremanagement")
public class Healthcentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "healthcentreId")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "kkfNumber")
    private String kkfNumber;
    @OneToMany(mappedBy = "healthcentre", cascade = CascadeType.ALL)
    private Set<Doctor> doctors;
    @OneToMany(mappedBy = "healthcentre", cascade = CascadeType.ALL)
    private Set<Employee> employees;
    @OneToMany(mappedBy = "healthcentre", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;
}
