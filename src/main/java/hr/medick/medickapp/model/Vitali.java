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
import java.util.Date;

@Entity
@Table(name="vitali")
@Data
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


}
