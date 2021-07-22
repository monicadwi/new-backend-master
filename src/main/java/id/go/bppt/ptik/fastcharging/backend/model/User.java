package id.go.bppt.ptik.fastcharging.backend.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreUpdate;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "user")
@Getter @Setter
public class User {
	@Id
	@Column(name = "nik", nullable = false, columnDefinition="VARCHAR(16)")
	private String nik;
	private String username;
	private String full_name;
	private String email;
	private String password;
	private boolean enabled;
	
	@Transient
    private String passwordConfirm;
	
	@ManyToMany
    @JoinTable(name = "users_roles",
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
	
	private String address;
	private Date birth_date;
	
	// Mandatory Fields
	@Basic(optional = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date create_time = new Date();
	private String create_by;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time = new Date();
	private String update_by;
	
	public User(){
		super();
		this.enabled = false;
	}
	
	@PreUpdate
    public void setUpdatedAt() {
    	this.update_time = new Date();
    }
	
}
