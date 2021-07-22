package id.go.bppt.ptik.fastcharging.backend.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity

@Getter @Setter
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users;
    
    @ManyToMany
    @JoinTable(name = "roles_privileges",
    	joinColumns = @JoinColumn(name = "role_id"), 
    	inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges;
    
    // Mandatory Fields
    @Basic(optional = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time = new Date();
	private String create_by;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date update_time= new Date();
	private String update_by;
    
    public Role(){
		super();
	}
    
    public Role(String roleName)
	{
		this.name = roleName;
	}
    
    @PreUpdate
    public void setUpdatedAt() {
    	this.update_time = new Date();
    }
}
