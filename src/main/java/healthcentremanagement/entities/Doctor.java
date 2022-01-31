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
@Table(name = "doctor", schema = "healthcentremanagement")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId")
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "specialty")
    private String specialty;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "identification_id", unique = true, nullable = false)
    private Identification identification;

    @OneToMany(mappedBy = "doctor")
    private Set<Treatment> treatments;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "healthcentre_id", referencedColumnName = "healthcentreId")
    private Healthcentre healthcentre;

    @Override
    public String toString() {
        return "\nDoctor{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", specialty='" + specialty + '\'' +
                ", identification=" + identification +
                '}';
    }
}
