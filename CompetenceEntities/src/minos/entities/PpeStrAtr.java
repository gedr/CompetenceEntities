package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the PPE_SA database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="PPE_SA", schema="Minos")
@NamedQuery(name="PpeStrAtr.findAll", query="SELECT p FROM PpeStrAtr p")
public class PpeStrAtr implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name="PPE_SA_GEN", table="GenL", schema="Minos",
	pkColumnName="TableName", valueColumnName="KeyValue",
	pkColumnValue="PPE_SA_GEN", allocationSize=1)
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="PPE_SA_GEN") 
	private long id;

	//uni-directional many-to-one association to Level
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="strAttr_id", referencedColumnName="id")
	private StringAttr stringAttr;

	//bi-directional many-to-one association to Level
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ppe_id", referencedColumnName="id")
	private ProfilePatternElement ppe;

	private short item;

	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	public PpeStrAtr() { }
	
	public PpeStrAtr( StringAttr strAttr, ProfilePatternElement ppe, short item, Journal journal ) {
		if ( ( strAttr == null ) || ( ppe == null ) || ( journal == null ) ) throw new IllegalArgumentException();
		this.stringAttr = strAttr;
		this.ppe = ppe;
		this.item = item;
		this.journal = journal;
	}

	public long getId() {
		return this.id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public short getItem() {
		return this.item;
	}

	public void setItem( short item ) {
		this.item = item;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal( Journal journal ) {
		if ( journal == null ) throw new IllegalArgumentException();
		this.journal = journal;
	}

	public StringAttr getStringAttr() {
		return stringAttr;
	}

	public void setStringAttr( StringAttr strAttr ) {
		if ( strAttr == null ) throw new IllegalArgumentException();
		this.stringAttr = strAttr;
	}

	public ProfilePatternElement getProfilePatternElement() {
		return ppe;
	}

	public void setProfilePatternElement( ProfilePatternElement ppe ) {
		if ( ppe == null ) throw new IllegalArgumentException();
		this.ppe = ppe;
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf( id ).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if (!( obj instanceof PpeStrAtr ) ) return false;        
		if ( this.id != ( ( PpeStrAtr ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "PpeStrAtr: [" + String.valueOf( id ) + "] ";
	}
}