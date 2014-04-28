package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ActorsInfo database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="ActorsInfo", schema="Minos")
@NamedQuery(name="ActorsInfo.findAll", query="SELECT a FROM ActorsInfo a")
public class ActorsInfo implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="ActorsInfo_Gen", table="GenI", schema="Minos", 
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="ACTORS_INFO_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ActorsInfo_Gen") 
    private int id;    

    @Column(name="name", length=500)
	private String name;

    @Column(name="variety")
	private short variety;

	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	public static final short VARIETY_SPEC	= 0;
	public static final short VARIETY_TYPE	= 1;
	public static final short VARIETY_MODE	= 2;
	public static final short VARIETY_LEVEL = 3;
	
	public ActorsInfo() { }
	
	public ActorsInfo( String name, short variety, Journal journal ) {
		this.name = name;
		this.variety = variety;
		this.journal = journal;		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getVariety() {
		return this.variety;
	}

	public void setVariety(short variety) {
		this.variety = variety;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal( Journal journal ) {
		this.journal = journal;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof ActorsInfo ) ) return false;        
		if ( this.id != ( ( ActorsInfo ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActorsInfo: [" + String.valueOf(id) + "] " + name;
	}
}