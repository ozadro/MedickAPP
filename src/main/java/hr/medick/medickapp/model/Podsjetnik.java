package hr.medick.medickapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "podsjetnik")
@Data
public class Podsjetnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpodsjetnik")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terapijaid", referencedColumnName = "idterapija", nullable = false)
    private Terapija terapija;
    @Column(name = "uzet")
    private Boolean uzet;


}
