package minos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the ProfilePatternElement database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="ProfilePatternElement", schema="Minos")
@NamedQuery(name="ProfilePatternElement.findAll", query="SELECT p FROM ProfilePatternElement p")
public class ProfilePatternElement implements Serializable, StatusConst {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name="PPE_Gen", table="GenI", schema="Minos",  
			pkColumnName="TableName", valueColumnName="KeyValue",
			pkColumnValue="PPE_GEN", allocationSize=1)
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="PPE_Gen") 
	private int id;

	@Column(name = "item")
	private short item;
	
	@Column(name="status")
	private short status;

	//bi-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="competence_id", referencedColumnName="id")
	private Competence competence;

	//uni-directional many-to-one association to Level
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="minLevel_id", referencedColumnName="id")
	private Level minLevel;

	//uni-directional many-to-one association to Level
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="profilePattern_id", referencedColumnName="id")
	private ProfilePattern profilePattern;	

	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	@OneToMany(mappedBy="ppe", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="item")
	private List<PpeStrAtr> attributes;

	public ProfilePatternElement() { }

	public ProfilePatternElement(short item, short status, Competence competence, Level minLevel,
			ProfilePattern profilePattern, Journal journal) {  
		this.item = item;
		this.status = status;
		this.competence = competence;
		this.minLevel = minLevel;
		this.profilePattern = profilePattern;
		this.journal = journal;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Competence getCompetence() {
		return this.competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public short getItem() {
		return this.item;
	}

	public void setItem(short item) {
		this.item = item;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public Level getMinLevel() {
		return this.minLevel;
	}

	public void setMinLevel(Level minLevel) {
		this.minLevel = minLevel;
	}
	
	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public ProfilePattern getProfilePattern() {
		return this.profilePattern;
	}

	public void setProfilePattern(ProfilePattern profilePattern) {
		this.profilePattern = profilePattern;
	}
		
	public List<PpeStrAtr> getAttributes() {
		return this.attributes;
	}

	public void setAttributes( List<PpeStrAtr> attrs ) {
		this.attributes = attrs;
	}

	public PpeStrAtr addAttributes( PpeStrAtr attrs ) {
		if ( attributes == null) attributes = new ArrayList<PpeStrAtr>();
		attributes.add( attrs);
		attrs.setProfilePatternElement( this );
		return attrs;
	}

	public PpeStrAtr removeAttributes( PpeStrAtr attrs ) {
		if ( ( attributes == null ) || ( attrs == null ) ) return attrs;
		if ( attributes.remove( attrs ) ) attrs.setProfilePatternElement(  null );
		return attrs;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( !( obj instanceof ProfilePatternElement ) ) return false;
		if ( this.id != ( ( ProfilePatternElement ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProfilePatternElement: [" + String.valueOf( id ) + "] ";
	}}