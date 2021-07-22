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
@Table( name = "privilege" )
@Getter 
@Setter
public class Privilege {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String name;
    
    private String notes;
    
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
	
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;
    
    public Privilege() {
    	super();
    }
    
    @PreUpdate
    public void setUpdatedAt() {
    	this.update_time = new Date();
    }
}
