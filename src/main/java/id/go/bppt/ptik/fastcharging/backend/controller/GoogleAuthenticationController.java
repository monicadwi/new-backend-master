package id.go.bppt.ptik.fastcharging.backend.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.go.bppt.ptik.fastcharging.backend.config.JwtTokenUtil;
import id.go.bppt.ptik.fastcharging.backend.dto.GoogleProfileDto;
import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.repository.UserRepository;
import id.go.bppt.ptik.fastcharging.backend.util.GoogleHelper;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class GoogleAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
    
	@Autowired
    private UserRepository userRepository;
	
	@RequestMapping(value = "/api/authenticate/google", method = RequestMethod.POST)
	public ResponseEntity<?> createGoogleAuthentication(@RequestBody GoogleProfileDto googleAccessToken){

        Boolean status = false;
        String msg = "";
        Map<String, Object> data = new HashMap<String, Object>();

        try {
            //validate google access token and get google profile
            String googleProfileJson = GoogleHelper.getUserInfo(googleAccessToken.getGoogleAccessTOken());

            if(googleProfileJson == null || googleProfileJson == "") {
                throw new Exception("Invalid access token");
            } 

            ObjectMapper objectMapper = new ObjectMapper();
            GoogleProfileDto googleProfile = objectMapper.readValue(googleProfileJson, GoogleProfileDto.class);

            String email = googleProfile.getEmail();

            // check whether user exists
            User user = userRepository.findByEmail(email);

            if (user == null) {
                data.put("user", new User());
                status = true;
                msg = "Go to registration page.";
            }
            else {
                // generate token
                String token = jwtTokenUtil.generateToken(user);

                data.put("token", token);
                data.put("user", user);
                status = true;
                msg = "Login success.";
            }

            HashMap<String, Object> mapSuccess = new HashMap<>();
		    mapSuccess.put("status", status);
		    mapSuccess.put("data", data);
		    mapSuccess.put("message", msg);
            
		    return new ResponseEntity<Object>(mapSuccess, HttpStatus.OK);
            
        } catch (Exception ex) {
            log.error("enter exception");
			
			HashMap<String, String> mapError = new HashMap<>();
			mapError.put("status", "error");
			mapError.put("message", ex.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
        }
    }
}
