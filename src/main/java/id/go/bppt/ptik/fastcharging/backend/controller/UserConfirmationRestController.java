package id.go.bppt.ptik.fastcharging.backend.controller;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.go.bppt.ptik.fastcharging.backend.model.ConfirmationToken;
import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.repository.ConfirmationTokenRepository;
import id.go.bppt.ptik.fastcharging.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/confirmation")
public class UserConfirmationRestController {

	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> confirmUser(@RequestParam String token){
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
	
		if (token != null)
		{
			String email = confirmationToken.getUser().getEmail();
			User user = userRepository.findByEmail(email);
			
			log.info(user.getNik());
			log.info(user.getEmail());
			
			Calendar cal = Calendar.getInstance();
			long diff = confirmationToken.getExpiryDate().getTime() - cal.getTime().getTime();
			if (diff<=0)
			{
				log.error("Token already expired");

				HashMap<String, String> mapError = new HashMap<>();
				mapError.put("status", "success");
				mapError.put("message", "Token sudah kadaluwarsa");
				
				return new ResponseEntity<Object>(mapError, HttpStatus.BAD_REQUEST);
			}
			
			user.setEnabled(true);
			userRepository.save(user);
			
			HashMap<String, String> mapSuccess = new HashMap<>();
			mapSuccess.put("status", "success");
			mapSuccess.put("message", "Aktivasi pengguna sukses!");
			
			return new ResponseEntity<Object>(mapSuccess, HttpStatus.OK);
		}
		else
		{
			log.error("token not found!");
			
			HashMap<String, String> mapError = new HashMap<>();
			mapError.put("status", "success");
			mapError.put("message", "Token tidak ditemukan");
			
			return new ResponseEntity<Object>(mapError, HttpStatus.BAD_REQUEST);
		}
	}
}
