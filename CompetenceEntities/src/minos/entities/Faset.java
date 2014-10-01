package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;


/**
 * The persistent class for the tStatFASETSp database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tStatFASETSp", schema="dbo")
@NamedQuery(name="Faset.findAll", query="SELECT f FROM Faset f")
public class Faset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name = "tStatFASETSpId")
	private int id;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Code")
	private int code;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset")
	private int type;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Name")
	private String name;

	public Faset() { }

	public int getId() {
		return this.id;
	}

	public int getCode() {
		return this.code;
	}

	public int getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof Faset ) ) return false;        
		if ( this.id != ( ( Faset ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faset: [" + String.valueOf( id ) + "] " + name;
	}
}