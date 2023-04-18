package hr.medick.medickapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="lijek")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Lijek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlijek")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "proizvodac")
    private String proizvodac;
}
