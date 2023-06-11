package hr.medick.medickapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Entity
@Table(name = "terapija")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public LocalDate getPrvaDozaDate(){
        return prvadoza.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
