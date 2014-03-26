package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PersonAddon database table.
 * 
 */
@Entity
@NamedQuery(name="PersonAddon.findAll", query="SELECT p FROM PersonAddon p")
public class PersonAddon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="person_id")
	private int id;
	
	//uni-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name="role_id", referencedColumnName="id")
	private Role role;

	@Column(name="logins", length=10000000)
	private String logins;

	public PersonAddon() { }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogins() {
		return logins;
	}

	public void setLogins(String logins) {
		this.logins = logins;
	}

	@Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
        if(obj == null) return false;        
        if (!(obj instanceof PersonAddon)) return false;
        if(this.id != ((PersonAddon) obj).id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "PersonAddon: [" + String.valueOf(id) + " ] ";
    }

}