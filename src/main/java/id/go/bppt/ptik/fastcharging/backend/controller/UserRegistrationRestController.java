package id.go.bppt.ptik.fastcharging.backend.controller;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bppt.ptik.fastcharging.backend.dto.UserRegistrationDto;
import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.service.EmailSenderService;
import id.go.bppt.ptik.fastcharging.backend.service.UserService;

@RestController
@RequestMapping("api/registration")
public class UserRegistrationRestController {
	@Autowired
    private UserService userService;
	
	@Autowired
    private EmailSenderService emailSenderService;

	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@RequestBody UserRegistrationDto userDto) {
		User existing = userService.findByEmail(userDto.getEmail());
		
		if (existing != null) {
			HashMap<String, String> mapError = new HashMap<>();
			mapError.put("status", "error");
			mapError.put("message", "There is already an account registered with that email");
			
			return new ResponseEntity<Object>(mapError, HttpStatus.BAD_REQUEST);
		}
		
//		String token = UUID.randomUUID().toString();
		String token = String.format("%06d", new Random().nextInt(999999));

        userService.saveByDto(userDto);
        
        // Assign confirmation token to user
        User target = userService.findByEmail(userDto.getEmail());
        userService.createVerificationToken(target, token);
        
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(target.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setFrom("ptikfastchargingdev@gmail.com");
//        mailMessage.setText("To confirm your account, please insert this token : " + token);
//        
//        emailSenderService.sendEmail(mailMessage);
        
        HashMap<String, String> mapSuccess = new HashMap<>();
		mapSuccess.put("status", "success");
		mapSuccess.put("message", "Registrasi berhasil. Untuk aktivasi silahkan cek email Anda");
		
		return new ResponseEntity<Object>(mapSuccess, HttpStatus.OK);
	}
}
