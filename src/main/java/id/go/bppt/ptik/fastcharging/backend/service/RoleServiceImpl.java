package id.go.bppt.ptik.fastcharging.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import id.go.bppt.ptik.fastcharging.backend.model.Privilege;
import id.go.bppt.ptik.fastcharging.backend.model.Role;
import id.go.bppt.ptik.fastcharging.backend.model.User;
import id.go.bppt.ptik.fastcharging.backend.repository.RoleRepository;

public class RoleServiceImpl implements RoleService{

	
	@Autowired
	private RoleRepository roleRepository;
	

	@Override
	public Iterable<Privilege> getPrivilegesByRoleId(Long id) {
		return roleRepository.findById(id).get().getPrivileges();
	}

	@Override
	public Iterable<User> getUsersByRoleId(Long id) {
		return roleRepository.findById(id).get().getUsers();
	}

	@Override
	public Optional<Role> getRoleById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Iterable<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

}
