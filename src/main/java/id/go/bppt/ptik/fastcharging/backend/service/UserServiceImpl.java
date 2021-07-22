package id.go.bppt.ptik.fastcharging.backend.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import id.go.bppt.ptik.fastcharging.backend.repository.ConfirmationTokenRepository;
import id.go.bppt.ptik.fastcharging.backend.repository.RoleRepository;
import id.go.bppt.ptik.fastcharging.backend.repository.UserRepository;
import id.go.bppt.ptik.fastcharging.backend.dto.UserRegistrationDto;
import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.model.ConfirmationToken;
import id.go.bppt.ptik.fastcharging.backend.model.Role;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    
	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
//		HashSet<Role> roles = new HashSet<Role>(roleRepository.findAll());
//		roles.remove(roleRepository.findById(1));
//		
//        user.setRoles(roles);
//        userRepository.save(user);
        
        HashSet<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName("Member"));
		
        user.setRoles(roles);
        userRepository.save(user);	
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveByDto(UserRegistrationDto registration) {
		User user = new User();
        user.setUsername(registration.getUsername());
        user.setFull_name(registration.getFullname());
        user.setEmail(registration.getEmail());
        user.setAddress(registration.getAddress());
        user.setBirth_date(registration.getBirth_date());
        user.setNik(registration.getNik());
        user.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        
        HashSet<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName("Member"));
		
        user.setRoles(roles);
        userRepository.save(user);		
	}
	
	@Override
	public void createVerificationToken(User user, String token) {
		ConfirmationToken myToken = new ConfirmationToken(token, user);
        confirmationTokenRepository.save(myToken);
	}

}
