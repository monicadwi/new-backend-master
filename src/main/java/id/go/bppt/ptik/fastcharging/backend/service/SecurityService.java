package id.go.bppt.ptik.fastcharging.backend.service;

import java.util.HashSet;

public interface SecurityService {
	String findLoggedInUsername();

	void autoLogin(String username, String password);
	 
	HashSet<String> getUserRoles();
}
