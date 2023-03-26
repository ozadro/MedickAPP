package hr.medick.medickapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Osoba")
@Data
public class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOsoba")
    private Long id;

    @Column(name = "Ime")
    private String ime;

    @Column(name = "Prezime")
    private String prezime;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telefon")
    private String telefon;

    @Column(name = "AdresaStanovanja")
    private String adresaStanovanja;

    @Column(name = "Lozinka")
    private String lozinka;
}
