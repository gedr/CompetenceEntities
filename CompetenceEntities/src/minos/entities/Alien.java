package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Alien database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Alien", schema="Minos")
@NamedQuery(name="Alien.findAll", query="SELECT a FROM Alien a")
public class Alien implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="Alien_Gen", table="GenI", schema="Minos", 
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="ALIEN_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Alien_Gen") 
    private int id;    

	@Column(name="name", length=500)
	private String name;
	
	@Column(name="summary", length=1000000)
	private String summary;

	@Column(name="status")
	private short status;	

	@Column(name="ver")
	private short version;
	
	// history tree
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ancestor_id", referencedColumnName="id")
	private Alien ancestor;

	@OneToMany(mappedBy="ancestor", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="version")
	private List<Alien> historyList;
	
	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	public Alien() { }
	
	public Alien( String name, String summary, short status, short version, Alien ancestor, Journal journal ) {
		this.name = name;
		this.summary = summary;
		this.status = status;
		this.version = version;
		this.ancestor = ancestor;
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

	public String getSummary() {
		return this.summary;
	}

	public void setSummary( String summary ) {
		this.summary = summary;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus( short status ) {
		this.status = status;
	}

	public short getVersion() {
		return this.version;
	}

	public void setVersion( short ver ) {
		this.version = ver;
	}
	
	public Alien getAncestor() {
		return this.ancestor;
	}

	public void setAncestor( Alien ancestor ) {
		this.ancestor = ancestor;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal( Journal journal ) {
		this.journal = journal;
	}

	public List<Alien> getHistoryList() {
		return this.historyList;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof Alien ) ) return false;        
		if ( this.id != ( ( Alien ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alien: [" + String.valueOf(id) + "] " + name;
	}
}