package id.go.bppt.ptik.fastcharging.backend.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRegistrationDto {
	@NotEmpty
	private String username;
	private String fullname;
	private String nik;
	private String address;
	private String password;
	private String confirmPassword;
	private String email;
	private String confirmEmail;
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date birth_date;
}
