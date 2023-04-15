package hr.medick.medickapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pacijent")
@Data
public class Pacijent {

    public Pacijent(Osoba osoba) {
        this.osoba = osoba;
    }

    public Pacijent() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpacijent")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "osobaid", referencedColumnName = "idosoba", nullable = false)
    private Osoba osoba;

}
