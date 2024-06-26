package hr.medick.medickapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="skrbnikpacijent")
@Data
public class SkrbnikPacijent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idskrbnikpacijent")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skrbnikid", referencedColumnName = "idskrbnik", nullable = false)
    private Skrbnik skrbnik;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacijentid", referencedColumnName = "idpacijent", nullable = false)
    private Pacijent pacijent;


}
