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
	private int personId;

	//uni-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	public PersonAddon() {
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}