package healthcentremanagement.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "identification", schema = "healthcentremanagement")
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "sex", nullable = false)
    private String sex;

    @OneToOne(mappedBy = "identification")
    private Doctor doctor;

    @OneToOne(mappedBy = "identification")
    private Patient patient;

    @OneToOne(mappedBy = "identification", cascade = CascadeType.REMOVE)
    private Employee employee;

    public Identification(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Identification{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
