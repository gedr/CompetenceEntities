package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

import java.sql.Timestamp;


/**
 * The persistent class for the tOrgAss database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tOrgAss", schema="dbo")
@NamedQuery(name="PersonPostRelation.findAll", query="SELECT ppr FROM PersonPostRelation ppr")
public class PersonPostRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name = "tOrgAssID")
	private int id;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="State")
	private int state;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="TypeTD")
	private int type;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tOrgStatDolId", referencedColumnName="tOrgStatDolId")
	private EstablishedPost epost ;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tPersonaId", referencedColumnName="tPersonaId")
	private Person person;
	
	public static final int TYPE_PERMANENT 	= 0;
	public static final int TYPE_TRANSIENT 	= 1;
	
	public static final int STATE_DISMISS 	= 1;
	public static final int STATE_ACTIVE 	= 3;
	
	public PersonPostRelation() { }

	public int getId() {
		return this.id;
	}

	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public int getState() {
		return this.state;
	}
	
	public int getType() {
		return this.type;
	}
	
	public EstablishedPost getEstablishedPost() {
		return epost;
	}
	
	public Person getPerson() {
		return person;
	}

	@Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
    	if ( this == obj ) return true;
        if ( obj == null ) return false;        
        if ( !( obj instanceof PersonPostRelation ) ) return false;
        if ( this.id != ( ( PersonPostRelation ) obj ).id ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "DivisionPostAssign: [" + String.valueOf(id) + " ] ";
    }

}