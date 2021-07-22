package id.go.bppt.ptik.fastcharging.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.go.bppt.ptik.fastcharging.backend.model.ConfirmationToken;
import id.go.bppt.ptik.fastcharging.backend.model.User;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{
	ConfirmationToken findByConfirmationToken(String token);	 
    ConfirmationToken findByUser(User user);
}
