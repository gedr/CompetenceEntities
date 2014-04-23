package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tOrgStru database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tOrgStru", schema="dbo")
@NamedQuery(name="Division.findAll", query="SELECT d FROM Division d")
public class Division implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="tOrgStruID")
	private int id;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="FullName")
	private String fullName;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="SortOrder")
	private int sortOrder;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Isdelete")
	private int isdelete;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="OtizOk")
	private int otizOk;

	// division's tree
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Parent", referencedColumnName="tOrgStruID")    
	private Division parentDivision; 

	@OneToMany(mappedBy="parentDivision", fetch=FetchType.LAZY)
	@OrderBy(value="name")
	private List<Division> subDivisions;

	@OneToMany(mappedBy="division", fetch=FetchType.LAZY)
	@OrderBy(value="kpers")
	private List<EstablishedPost> establishedPosts;

	public Division() { }

	public int getId() {
		return id;
	}

	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public String getFullName() {
		return this.fullName;
	}

	public int getIsdelete() {
		return this.isdelete;
	}

	public int getOtizOk() {
		return this.otizOk;
	}

	public int getSortOrder() {
		return this.sortOrder;
	}

	public Division getParentDivision() {
		return this.parentDivision;
	}

	public List<Division> getSubDivision() {
		return this.subDivisions;
	}
	
	public List<EstablishedPost> getEstablishedPosts() {
		return establishedPosts;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if (!(obj instanceof Division)) return false;
		if(this.id != ((Division) obj).id) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Division: [" + String.valueOf(id) + "] " + fullName;
	}
}