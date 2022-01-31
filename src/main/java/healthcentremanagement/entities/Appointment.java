package healthcentremanagement.entities;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "appointment", schema = "healthcentremanagement")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId")
    private int id;
    @Column(name = "appointmentdate", unique = true,updatable = false)
    private LocalDate appointmentDate;
    @Column(name = "appointmentTime")
    private LocalTime appointmentTime;
    @Column(name = "section",updatable = false)
    private String section;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId",updatable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "healthcentre_id", referencedColumnName = "healthcentreId")
    private Healthcentre healthcentre;

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", section='" + section + '\'' +
                ", patient=" + patient +
                '}';
    }
}
