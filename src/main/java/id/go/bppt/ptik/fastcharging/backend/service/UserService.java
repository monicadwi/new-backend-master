package id.go.bppt.ptik.fastcharging.backend.service;

import id.go.bppt.ptik.fastcharging.backend.dto.UserRegistrationDto;
import id.go.bppt.ptik.fastcharging.backend.model.User;

public interface UserService {
	void save(User user);

    User findByUsername(String username);
    User findByEmail(String email);
    
    void saveByDto(UserRegistrationDto registration);
    
    void createVerificationToken(User user, String token);
}
