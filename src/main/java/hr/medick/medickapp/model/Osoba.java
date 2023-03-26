package hr.medick.medickapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="osoba")
@Data
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
