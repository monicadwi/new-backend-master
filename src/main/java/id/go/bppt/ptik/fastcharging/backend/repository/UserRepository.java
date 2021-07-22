package id.go.bppt.ptik.fastcharging.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.go.bppt.ptik.fastcharging.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	User findByEmail(String email);
}
