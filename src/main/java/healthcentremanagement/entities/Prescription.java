package healthcentremanagement.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prescription", schema = "healthcentremanagement")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescriptionId")
    private int id;
    @Column(name = "dose", nullable = false)
    private int dose;
    @Column(name = "medicine_provided", nullable = false)
    private boolean medicine_provided;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id", unique = true, nullable = false)
    private Treatment treatment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id", referencedColumnName = "medicineId")
    private Medicine medicine;

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", dose=" + dose +
                ", medicine_provided=" + medicine_provided +
                ", medicine=" + medicine +
                '}';
    }
}
