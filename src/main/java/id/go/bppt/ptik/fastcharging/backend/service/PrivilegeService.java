package id.go.bppt.ptik.fastcharging.backend.service;

import java.util.Optional;

import id.go.bppt.ptik.fastcharging.backend.model.Privilege;;

public interface PrivilegeService {
	Iterable<Privilege> getAllPrivileges();
	Optional<Privilege> getPrivilegeById(Long id);
	Privilege save(Privilege privilege); 
}
