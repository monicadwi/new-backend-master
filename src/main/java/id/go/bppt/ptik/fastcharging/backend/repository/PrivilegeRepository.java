package id.go.bppt.ptik.fastcharging.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.go.bppt.ptik.fastcharging.backend.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
	Privilege findById(long id);
	
	Privilege findByName(String name);

}
