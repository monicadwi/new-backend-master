package id.go.bppt.ptik.fastcharging.backend.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class JwtResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1761119043868598178L;

	private String jwttoken;
	
	public JwtResponse()
	{
		
	}
	
	public JwtResponse(String jwttoken) 
	{
		this.setJwttoken(jwttoken);
	}
}
