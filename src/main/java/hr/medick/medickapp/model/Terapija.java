package hr.medick.medickapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "terapija")
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
    @JoinColumn(name = "vitaliid", referencedColumnName = "idvitali")
    private Vitali vitali;
    @Column(name = "dozalijeka")
    private String dozalijeka;
    @Column(name = "ponavljanja")
    private float ponavljanja;
    @Column(name = "prvadoza")
    private Date prvadoza;
    @Column(name = "kolicinatableta")
    private int kolicinatableta;
    @Column(name = "kolicinadnevno")
    private int kolicinadnevno;

}
