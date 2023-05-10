package hr.medick.medickapp.dto;

import hr.medick.medickapp.model.Osoba;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto {
    private Osoba osoba;
    private Boolean changedEmail;
}
