package minos.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PersonLogin database table.
 * 
 */
@Entity
@NamedQuery(name="PersonLogin.findAll", query="SELECT p FROM PersonLogin p")
public class PersonLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="person_id")
	private int personId;

	private String personLogin;

	public PersonLogin() {
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonLogin() {
		return this.personLogin;
	}

	public void setPersonLogin(String personLogin) {
		this.personLogin = personLogin;
	}

}