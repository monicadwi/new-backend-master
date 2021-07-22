package id.go.bppt.ptik.fastcharging.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.go.bppt.ptik.fastcharging.backend.model.Privilege;
import id.go.bppt.ptik.fastcharging.backend.repository.PrivilegeRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService{

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Override
	public Iterable<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll();
	}

	@Override
	public Optional<Privilege> getPrivilegeById(Long id) {
		return privilegeRepository.findById(id);
	}

	@Override
	public Privilege save(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}
	
	public void getPriv() {
		
	}

}
