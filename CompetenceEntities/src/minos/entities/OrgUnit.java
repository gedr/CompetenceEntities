package minos.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

@Entity
@Table( name="tOrgAssignCur" )
@NamedQuery( name="OrgUnit.findAll", query="SELECT ou FROM OrgUnit ou" )
public class OrgUnit implements Serializable {
	// =================================================================================================================
	// Constants
	// =================================================================================================================
	private static final long serialVersionUID = 1L;
	
	public static final int STATE_ACTIVE 	= 3;
	public static final int STATE_FIRED 	= 1;
	
	public static final int TYPE_TEMPORARY 	= 1;
	public static final int TYPE_PERMANENTLY= 0;

	// =================================================================================================================
	// Fields
	// =================================================================================================================
	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="tOrgAssignCurID")
	private int id;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="State")
	private int state;  // 1-fired, 3-active

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="TypeTD")
	private int typeTD; // 0-permanently, 1-temporary

	//bi-directional many-to-one association to Person
	@ReadOnly(value=UpdateAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tPersonaId", referencedColumnName="tPersonaId")
	private Person person;

	//bi-directional many-to-one association to Division
	@ReadOnly(value=UpdateAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tOrgStruId", referencedColumnName="tOrgStruID")
	private Division division;

	//bi-directional many-to-one association to Position
	@ReadOnly(value=UpdateAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tStatDolSpId", referencedColumnName="tStatDolSPId")
	private Post post;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public OrgUnit() { }

	// =================================================================================================================
	// Getter & Setter
	// =================================================================================================================
	public int getId() {
		return id;
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

	public int getTypeTD() {
		return this.typeTD;
	}

	public Person getPerson() {
		return this.person;
	}

	public Division getDivision() {
		return this.division;
	}

	public Post getPost() {
		return this.post;
	}
	
	// =================================================================================================================
	// Methods for/from SuperClass/Interfaces
	// =================================================================================================================
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( obj == null ) return false;        
		if ( this == obj ) return true;
		if ( !( obj instanceof Catalog ) ) return false;        
		if( this.id != ( ( OrgUnit ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrgUnit: [" + String.valueOf( id ) + "] ";
	}
}