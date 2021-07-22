package id.go.bppt.ptik.fastcharging.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.go.bppt.ptik.fastcharging.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findById(long id);
	Role findByName(String name);	
	
	
}
