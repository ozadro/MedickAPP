package hr.medick.medickapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "vitali")
@Data
@NoArgsConstructor
public class Vitali {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvitali")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacijentid", referencedColumnName = "idpacijent", nullable = false)
    private Pacijent pacijent;
    @Column(name = "glukozaukrvi")
    private String glukozaukrvi;

    @Column(name = "krvnitlak")
    private String krvnitlak;

    @Column(name = "datummjerenja")
    private Date datummjerenja;

    public Vitali(Pacijent pacijent, String glukozaukrvi, String krvnitlak, Date datummjerenja) {
        this.pacijent = pacijent;
        this.glukozaukrvi = glukozaukrvi;
        this.krvnitlak = krvnitlak;
        this.datummjerenja = datummjerenja;
    }
}
