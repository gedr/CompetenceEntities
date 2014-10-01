package minos.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

@Entity
@Table(name="tStatDolSP")
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	// =================================================================================================================
	// Constants
	// =================================================================================================================
	private static final long serialVersionUID = 1L;
	
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="tStatDolSPId")
	private int id;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="FullTXT")
	private String name;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="IsDeleted")
	private int isDeleted;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="KPERS")
	private int kpers;
	
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

	//bi-directional many-to-one association to OrganizationUnit
	@OneToMany(mappedBy="post")
	private List<OrgUnit> orgUnits;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public Post() { }

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

	public String getName() {
		return this.name;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}
	
	public int getKpers() {
		return this.kpers;
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

	public List<OrgUnit> getOrganizationUnits() {
		return this.orgUnits;
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
		if ( !( obj instanceof Post ) ) return false;        
		if( this.id != ( ( Post ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post: [" + String.valueOf( id ) + "] " + name;
	}
}