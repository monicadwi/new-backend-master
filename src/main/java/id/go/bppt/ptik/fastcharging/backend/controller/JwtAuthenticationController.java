package id.go.bppt.ptik.fastcharging.backend.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.go.bppt.ptik.fastcharging.backend.config.JwtTokenUtil;
import id.go.bppt.ptik.fastcharging.backend.model.JwtRequest;
import id.go.bppt.ptik.fastcharging.backend.model.JwtResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest){
		try
		{
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			
			log.info(userDetails.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			
			return ResponseEntity.ok(new JwtResponse(token));
		}
		catch(UsernameNotFoundException | BadCredentialsException ex)
		{
			log.error("enter exception");
			
			HashMap<String, String> mapError = new HashMap<>();
			mapError.put("status", "error");
			mapError.put("message", ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
	
	}
	
	private Authentication authenticate(String username, String password) {
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));		
	}
	
	
}
