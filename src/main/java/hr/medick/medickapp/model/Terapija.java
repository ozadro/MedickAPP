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
@Table(name="terapija")
@Data
public class Terapija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idterapija")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lijekid", referencedColumnName = "idlijek", nullable = false)
    private Lijek lijek;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacijentid", referencedColumnName = "idpacijent", nullable = false)
    private Pacijent pacijent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vitaliid", referencedColumnName = "idvitali", nullable = false)
    private Vitali vitali;
    @Column(name = "kolicina")
    private int kolicina;

    @Column(name = "ponavljanja")
    private float ponavljanja;

}
