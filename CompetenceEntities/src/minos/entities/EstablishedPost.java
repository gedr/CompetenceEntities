package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the tOrgStatDol database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tOrgStatDol", schema="dbo")
@NamedQuery(name="EstablishedPost.findAll", query="SELECT e FROM EstablishedPost e")
public class EstablishedPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name = "tOrgStatDolId")
	private int id;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="IsDelete")
	private int isdelete;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="KPERS")
	private int kpers;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="V0001")
	private String name;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="OtizOk")
	private int otizOk;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset_02")
	private int faset2; 

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset_03")
	private int faset3;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset_07")
	private int faset7;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset_11")
	private int faset11;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset_12")
	private int faset12;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Faset_99")
	private int faset99;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tOrgStruId", referencedColumnName="tOrgStruID")
	private Division division;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tStatBDolSPId", referencedColumnName="tStatBDolSPId")
	private BasisPost basisPost;
	
	@OneToMany(mappedBy="epost", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="item")
	private List<PersonPostRelation> personPostRelations;

	@OneToMany(mappedBy="establishedPost", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})	
	private List<Profile> profiles;


	public EstablishedPost() { }

	public int getId() {
		return id;
	}
	
	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public int getIsdelete() {
		return this.isdelete;
	}

	public int getKpers() {
		return this.kpers;
	}

	public String getName() {
		return this.name;
	}

	public int getOtizOK() {
		return this.otizOk;
	}

	public int getFaset2() {
		return this.faset2;
	}

	public int getFaset3() {
		return this.faset3;
	}

	public int getFaset7() {
		return this.faset7;
	}

	public int getFaset11() {
		return this.faset11;
	}

	public int getFaset12() {
		return this.faset12;
	}

	public int getFaset99() {
		return this.faset99;
	}

	public Division getDivision() {
		return this.division;
	}

	public BasisPost getBasisPost() {
		return this.basisPost;
	}
	
	public List<PersonPostRelation> getPersonPostRelations() {
		return this.personPostRelations;
	}
	
	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles( List<Profile> profiles ) {
		this.profiles = profiles;
	}
	
	public Profile addProfile( Profile profile ) {
		if ( profile == null ) return profile;
		if ( profiles == null ) profiles = new ArrayList<Profile>();
		profiles.add( profile );
		profile.setEstablishedPost( this );
		return profile;
	}

	public Profile removeProfile( Profile profile ) {
		if ( ( profiles == null ) || ( profile == null ) ) return profile;
		if ( profiles.remove( profile ) ) profile.setEstablishedPost( null );
		return profile;
	}


	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof EstablishedPost ) ) return false;        
		if ( this.id != ( ( EstablishedPost ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstablishedPost: [" + String.valueOf( id ) + "] " + name;
	}
}