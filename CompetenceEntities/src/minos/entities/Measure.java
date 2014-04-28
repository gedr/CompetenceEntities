package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Measure database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Measure", schema="Minos")
@NamedQuery(name="Measure.findAll", query="SELECT m FROM Measure m")
public class Measure implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @TableGenerator(name="Measure_Gen", table="GenI", schema="Minos", 
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="MEASURE_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Measure_Gen") 
    private int id;    

    @Column(name="name", length=250)
	private String name;    

    @Column(name="descr", length=8000)
	private String description;

    @Column(name="start")
	private Timestamp start;

    @Column(name="stop")
	private Timestamp stop;
    
	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	//bi-directional many-to-one association to Actor
	@OneToMany(mappedBy="measure", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})	
	private List<Actors> actors;

	public Measure() { }

	public Measure( String name, String descr, Timestamp start, Timestamp stop, Journal journal, List<Actors> actors ) {
		this.name = name;
		this.description = descr;
		this.start = start;
		this.stop = stop;
		this.journal = journal;
		this.actors = actors;		
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

	public void setName( String name ) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription( String descr ) {
		this.description = descr;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal( Journal journal ) {
		this.journal = journal;
	}

	public Timestamp getStart() {
		return this.start;
	}

	public void setStart( Timestamp start ) {
		this.start = start;
	}

	public Timestamp getStop() {
		return this.stop;
	}

	public void setStop( Timestamp stop ) {
		this.stop = stop;
	}

	public List<Actors> getActors() {
		return this.actors;
	}

	public void setActors( List<Actors> actors ) {
		this.actors = actors;
	}
	
	public Actors addActor( Actors actor ) {
		if ( actor == null ) return actor;
		if ( actors == null ) actors = new ArrayList<Actors>();
		actors.add( actor );
		actor.setMeasure( this );
		return actor;
	}

	public Actors removeActor( Actors actor ) {
		if ( ( actors == null ) || ( actor == null ) ) return actor;
		if ( actors.remove( actor ) ) actor.setMeasure( null );
		return actor;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof Measure ) ) return false;        
		if ( this.id != ( ( Measure ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Measure: [" + String.valueOf(id) + "] " + name;
	}
}