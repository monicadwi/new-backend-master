package id.go.bppt.ptik.fastcharging.backend.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class JwtRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3640582579578049917L;
	
	private String username;
	private String password;
	
	public JwtRequest() 
	{
		
	}
	
	public JwtRequest(String username, String password)
	{
		this.setUsername(username);
		this.setPassword(password);
	}
}
