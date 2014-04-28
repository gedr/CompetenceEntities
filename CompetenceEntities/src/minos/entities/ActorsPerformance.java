package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ActorsPerformance database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="ActorsPerformance", schema="Minos")
@NamedQuery(name="ActorsPerformance.findAll", query="SELECT a FROM ActorsPerformance a")
public class ActorsPerformance implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="ActorsPerf_Gen", table="GenL", schema="Minos",
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="ACTORS_PERF_GEN", allocationSize=1)
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ActorsPerf_Gen") 
    private long id;    

    @Column(name="attr", length=1000000)
	private String attributes;

	@Column(name="cost")
	private double cost;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ppe_id", referencedColumnName="id")    
	private ProfilePatternElement profilePatternElement; 

	//bi-directional many-to-one association to Actor
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="actors_id", referencedColumnName="id")
	private Actors actors;

	public ActorsPerformance() { }
	
	public ActorsPerformance( String attr, double cost, ProfilePatternElement ppe, Actors actors ) {
		this.attributes = attr;
		this.cost = cost;
		this.profilePatternElement = ppe;
		this.actors = actors;		
	}

	public long getId() {
		return this.id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getAttributes() {
		return this.attributes;
	}

	public void setAttributes( String attr ) {
		this.attributes = attr;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost( double cost ) {
		this.cost = cost;
	}

	public ProfilePatternElement getProfilePatternElement() {
		return this.profilePatternElement;
	}

	public void setProfilePatternElement( ProfilePatternElement val ) {
		this.profilePatternElement = val;
	}

	public Actors getActor() {
		return this.actors;
	}

	public void setActor( Actors actor ) {
		this.actors = actor;
	}

	@Override
	public int hashCode() {
		return Long.valueOf( id ).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof ActorsPerformance ) ) return false;        
		if ( this.id != ( ( ActorsPerformance ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActorsPerformance: [" + String.valueOf(id) + "] ";
	}
}