package hr.medick.medickapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "osoba")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idosoba")
    private Long id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "adresastanovanja")
    private String adresaStanovanja;

    @Column(name = "lozinka")
    private String lozinka;
}
