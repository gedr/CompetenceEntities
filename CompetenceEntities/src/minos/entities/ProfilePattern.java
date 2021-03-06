package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the ProfilePattern database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="ProfilePattern", schema="Minos")
@NamedQuery(name="ProfilePattern.findAll", query="SELECT p FROM ProfilePattern p")
public class ProfilePattern implements Serializable, StatusConst {
	private static final long serialVersionUID = 1L;
	public static final int MAX_FILIAL_MASK_SIZE = 10;

	@TableGenerator(name="PP_Gen", table="GenI", schema="Minos",  
			pkColumnName="TableName", valueColumnName="KeyValue",
			pkColumnValue="PP_GEN", allocationSize=1)
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="PP_Gen") 
	private int id;

	@Column(name="name", length=250)
	private String name;

	@Basic(fetch=FetchType.LAZY)
	@Column(name="descr", length=8_000)
	private String description;

	@Basic(fetch=FetchType.LAZY)
	@Column(name="summary", length=1_000_000)
	private String summary;

	@Column(name="filialMask")
	private byte[] filialMask;

	@Column(name="item")
	private int item;

	@Column(name="status")
	private short status;
	
	@Column(name="timePoint")
	private Timestamp timePoint;
	
	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	//bi-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="catalog_id", referencedColumnName="id")
	private Catalog catalog;
	
	//bi-directional many-to-one association to MinosIndicator
	@OneToMany(mappedBy="profilePattern", fetch=FetchType.LAZY, 
			cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="item")
	private List<ProfilePatternElement> profilePatternElements;
	
	
	public ProfilePattern() { }
	
	public ProfilePattern( String name, String descr, byte[] filialMask, int item, short status, String summary,
			Timestamp timePoint, Catalog catalog, Journal journal ) {
		this.name = name;
		this.description = descr;
		this.filialMask = filialMask;
		this.item = item;
		this.status = status;
		this.summary = summary;
		this.timePoint = timePoint;		
		this.catalog = catalog;
		this.journal = journal;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription( String descr ) {
		this.description = descr;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary( String summary ) {
		this.summary = summary;
	}

	public byte[] getFilialMask() {
		return this.filialMask;
	}

	public void setFilialMask( byte[] filialMask ) {
		this.filialMask = filialMask;
	}

	public int getItem() {
		return this.item;
	}

	public void setItem( int item ) {
		this.item = item;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal( Journal journal ) {
		this.journal = journal;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus( short status ) {
		this.status = status;
	}

	public Timestamp getTimePoint() {
		return this.timePoint;
	}

	public void setTimePoint( Timestamp timePoint ) {
		this.timePoint = timePoint;
	}
	
	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog( Catalog catalog ) {
		this.catalog = catalog;
	}
	
	public List<ProfilePatternElement> getProfilePatternElements() {
		return this.profilePatternElements;
	}

	public void setProfilePatternElements( List<ProfilePatternElement> profilePatternElements ) {
		this.profilePatternElements = profilePatternElements;
	}
	
	public ProfilePatternElement addProfilePatternElement( ProfilePatternElement element ) {
		if ( profilePatternElements == null ) profilePatternElements = new ArrayList<>();			
		profilePatternElements.add( element );
		element.setProfilePattern( this );
		return element;
	}

	public ProfilePatternElement removeProfilePatternElement( ProfilePatternElement element ) {
		if ( ( profilePatternElements == null ) || ( element == null ) ) return element;		
		if ( profilePatternElements.remove( element ) ) element.setProfilePattern( null );
		return element;
	}
	
	@Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if ( this == obj ) return true;
        if ( obj == null ) return false;        
        if ( !( obj instanceof ProfilePattern ) ) return false;
        if ( this.id != ( ( ProfilePattern ) obj ).id ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProfilePattern: [" + String.valueOf(id) + " ] " + name;
    }
}