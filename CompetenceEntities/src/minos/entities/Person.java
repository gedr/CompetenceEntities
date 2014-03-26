package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

import java.sql.Timestamp;


/**
 * The persistent class for the tPersona database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tPersona", schema="dbo")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="tPersonaId")
	private int id;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Drojd")
	private Timestamp birthdate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="F")
	private String surname;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="I")
	private String name;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="O")
	private String patronymic;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Sex")
	private String sex;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="STAT2")
	private int stat2;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Tab")
	private String tab;

	public Person() { }

	public int getId() {
		return id;
	}

	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public Timestamp getBirthdate() {
		return this.birthdate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getName() {
		return this.name;
	}

	public String getPatronymic() {
		return this.patronymic;
	}

	public String getSex() {
		return this.sex;
	}

	public int getStat2() {
		return this.stat2;
	}

	public String getTab() {
		return this.tab;
	}

	/*
	public PersonLogin getPersonLogin() {
		return this.personLogin;
	}
	*/
    
	@Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
        if(obj == null) return false;        
        if (!(obj instanceof Person)) return false;
        if(this.id != ((Person) obj).id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person: [" + String.valueOf(id) + " ] " + surname + " " + name + " " + patronymic;
    }
}