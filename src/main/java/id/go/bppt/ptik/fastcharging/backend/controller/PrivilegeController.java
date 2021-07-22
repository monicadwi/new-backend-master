package id.go.bppt.ptik.fastcharging.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bppt.ptik.fastcharging.backend.dto.PrivilegeDto;
import id.go.bppt.ptik.fastcharging.backend.model.Privilege;
import id.go.bppt.ptik.fastcharging.backend.service.PrivilegeService;

@RestController
@RequestMapping("api/privilege")
public class PrivilegeController {
	
	@Autowired
	private PrivilegeService privilegeService;
	

	
	@GetMapping
	public Iterable<Privilege> findAll()
	{
		return privilegeService.getAllPrivileges();
	}
	
	@PostMapping
	public Privilege savePrivilege(@RequestBody Privilege privilege)
	{
		return privilegeService.save(privilege);
	}

}
