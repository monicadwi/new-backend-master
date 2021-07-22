package id.go.bppt.ptik.fastcharging.backend.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PrivilegeDto {
	@NotEmpty
	private String name;
}
