package id.go.bppt.ptik.fastcharging.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoogleProfileDto {

    private String id;
    private String email;

    @JsonAlias(value = "verified_email")
    private Boolean verifiedEmail;
    private String name;
    
    @JsonAlias(value = "given_name")
    private String givenName;
    
    @JsonAlias(value = "family_name")
    private String familyName;
    
    private String picture;
    private String locale;

    @JsonAlias(value = "accessToken")
    private String googleAccessTOken;
}
