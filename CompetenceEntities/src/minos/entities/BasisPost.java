package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

import java.sql.Timestamp;


/**
 * The persistent class for the tStatBDolSP database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tStatBDolSP", schema="dbo")
@NamedQuery(name="BasisPost.findAll", query="SELECT b FROM BasisPost b")
public class BasisPost implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name = "tStatBDolSPId")
	private int id;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="IsDeleted")
	private int isdelete;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="KPERS")
	private int kpers;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="V0001")
	private String name;

	public BasisPost() { }
	
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

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof BasisPost ) ) return false;        
		if ( this.id != ( ( BasisPost ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasisPost: [" + String.valueOf( id ) + "] " + name;
	}
}