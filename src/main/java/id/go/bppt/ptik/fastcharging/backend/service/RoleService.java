package id.go.bppt.ptik.fastcharging.backend.service;

import java.util.Optional;

import id.go.bppt.ptik.fastcharging.backend.model.Privilege;
import id.go.bppt.ptik.fastcharging.backend.model.Role;
import id.go.bppt.ptik.fastcharging.backend.model.User;

public interface RoleService {
	Iterable<Privilege> getPrivilegesByRoleId(Long id);
	Iterable<User> getUsersByRoleId(Long id);
	Iterable<Role> getAllRoles();
	Optional<Role> getRoleById(Long id);
	Role save(Role role);
}
