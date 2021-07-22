package id.go.bppt.ptik.fastcharging.backend.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/action/")
@Slf4j
public class UserActionController {
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping(value = "detail", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE })   
	public ResponseEntity<?> getDetail(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User userx = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
		
		User user = userRepository.findByUsername(userx.getUsername());
		
		log.info(user.toString());
		
		HashMap<String, String> mapUser = new HashMap<>();
		mapUser.put("nik", user.getNik());
		mapUser.put("email", user.getEmail());
		mapUser.put("username", user.getUsername());
		mapUser.put("fullname", user.getFull_name());
		mapUser.put("address", user.getAddress());
		mapUser.put("birth_date", user.getBirth_date().toString());
		
		return ResponseEntity.ok(mapUser);
	}
}
