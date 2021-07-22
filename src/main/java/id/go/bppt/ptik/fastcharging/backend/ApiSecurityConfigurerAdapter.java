package id.go.bppt.ptik.fastcharging.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import id.go.bppt.ptik.fastcharging.backend.config.JwtAuthenticationEntryPoint;
import id.go.bppt.ptik.fastcharging.backend.config.JwtRequestFilterAll;
import id.go.bppt.ptik.fastcharging.backend.model.Privilege;
import id.go.bppt.ptik.fastcharging.backend.model.Role;
import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.repository.PrivilegeRepository;
import id.go.bppt.ptik.fastcharging.backend.repository.RoleRepository;
import id.go.bppt.ptik.fastcharging.backend.repository.UserRepository;
import id.go.bppt.ptik.fastcharging.backend.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class ApiSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
	    return new UserDetailsServiceImpl();
	}

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilterAll jwtRequestFilterAll;
	
	@Bean
	@Primary
    public AuthenticationManager apiAuthenticationManager() throws Exception {
        return authenticationManager();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.antMatcher("/api/action/**")
        	.authorizeRequests()
        	.antMatchers("/api/action/**")
        	.authenticated()
        	.antMatchers("/api/authenticate")
        	.permitAll()
			.antMatchers("/api/authenticate/google")
        	.permitAll()
        	.antMatchers("/api/registration")
        	.permitAll()
        	.antMatchers("/api/privilege/**")
        	.authenticated()
//        .anyRequest()
//        	.authenticated()
        	.and()            	
        .exceptionHandling()
        	.authenticationEntryPoint(jwtAuthenticationEntryPoint)
        	.and()
        .sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(jwtRequestFilterAll, UsernamePasswordAuthenticationFilter.class);
    } 
	
	@PostConstruct
	private void postConstruct()
	{	
		// starting privileges not yet created
		if (privilegeRepository.count() == 0)
		{
			List<Privilege> privileges = new ArrayList<Privilege>();
			
			for (String privilegeName : new String[] {"Create User", "Edit User", "Delete User"}) {
				Privilege privilege = new Privilege();
				privilege.setName(privilegeName);
				privilege.setCreate_by("GOD");
				
				privileges.add(privilege);
			} 
			
			privilegeRepository.saveAll(privileges);
		}
		
		// starting roles not yet created
		if (roleRepository.count() == 0)
		{
			List<Role> roles = new ArrayList<Role>();
			
			for (String roleName : new String[] {"Administrator", "Vendor", "Owner", "Member"})
			{
				Role role = new Role(roleName);
				role.setCreate_by("GOD");
				
				roles.add(role);
			}
			
			roleRepository.saveAll(roles);
			
			// Assign privileges to role administrator
			HashSet<Privilege> privileges = new HashSet<Privilege>();
			privileges.add(privilegeRepository.findByName("Create User"));
			privileges.add(privilegeRepository.findByName("Edit User"));
			privileges.add(privilegeRepository.findByName("Delete User"));
			
			Role adminRole = roleRepository.findByName("Administrator");
			adminRole.setPrivileges(privileges);
			
			roleRepository.save(adminRole);
		}
		
		User user = new User();
		user.setNik("7371142711900001");
		user.setUsername("admin");
		user.setPassword(passwordEncoder().encode("theAdmin123!@#"));
		user.setEmail("themusketeer17@gmail.com");
		
		Calendar myCal = Calendar.getInstance();
		myCal.set(1990, 10, 27, 22, 10);
		user.setBirth_date(myCal.getTime());
		
		user.setAddress("PSC Blok I2A no. 51");
		user.setEnabled(true);
		user.setCreate_by("GOD");
		
		HashSet<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName("Administrator"));
		user.setRoles(roles);
		
		userRepository.save(user);		
	}
	
}
