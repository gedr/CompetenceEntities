package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang.NullArgumentException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Actors database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Actors", schema="Minos")
@NamedQuery(name="Actors.findAll", query="SELECT a FROM Actors a")
public class Actors implements Serializable, StatusConst {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="Actors_Gen", table="GenL", schema="Minos", 
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="ACTORS_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Actors_Gen") 
    private long id;    

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="minos_id", referencedColumnName="tPersonaId")
	private Person minos;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="internalSinner_id", referencedColumnName="tPersonaId")
	private Person internalSinner;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="alienSinner_id", referencedColumnName="id")
	private Alien alienSinner;

	@Column(name="alienSinnerVer")
	private short alienSinnerVersion;

	@Column(name="sinnerType")
	private short sinnerType;

	@Column(name="gauge")
	private short gauge;
	
	@Column(name="finish")
	private Timestamp finish;

	@Column(name="assembly")
	private Timestamp assembly;

	@Column(name="status")
	private short status;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="measure_id", referencedColumnName="id")	
	private Measure measure;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="profile_id", referencedColumnName="id")	
	private Profile profile;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="testMode_id", referencedColumnName="id")	
	private ActorsInfo testMode;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="reserveLevel_id", referencedColumnName="id")	
	private ActorsInfo reserveLevel;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="reserveType_id", referencedColumnName="id")	
	private ActorsInfo reserveType;

	@OneToMany(mappedBy="actors", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="id")
	private List<ActorsPerformance> actorsPerformances;

	private boolean checkActorsInfo( ActorsInfo val, short flag ) {
		return ( ( val != null ) && 
				( ( val.getVariety() == ActorsInfo.VARIETY_SPEC ) || 
						( val.getVariety() == flag ) ) ); 
	}
	
	private Actors( short gauge, Timestamp finish, short status, Measure measure, Profile profile, 
			Journal journal, ActorsInfo testMode, ActorsInfo reserveLevel, ActorsInfo reserveType, 
			List<ActorsPerformance> lap ) {
		if ( ( finish == null ) || ( measure == null ) || 
				( journal == null ) ) throw new NullArgumentException( "Actors.Actors() have null argument" );
		if ( !checkActorsInfo( testMode, ActorsInfo.VARIETY_MODE ) ) throw new IllegalArgumentException( "testMode have illegal value" );
		if ( !checkActorsInfo( reserveLevel, ActorsInfo.VARIETY_LEVEL ) ) throw new IllegalArgumentException( "reserveLevel have illegal value" );
		if ( !checkActorsInfo( reserveType, ActorsInfo.VARIETY_TYPE ) ) throw new IllegalArgumentException( "reserveType have illegal value" );
		this.gauge = gauge;
		this.finish = finish;
		this.status = status;
		this.measure = measure;
		this.profile = profile;
		this.journal = journal;
		this.testMode = testMode;
		this.reserveLevel = reserveLevel;
		this.reserveType = reserveType;
		this.actorsPerformances = lap;		
	}

	public static final short SINNER_TYPE_UNKNOWN	= 0;
	public static final short SINNER_TYPE_INNER 	= 1;
	public static final short SINNER_TYPE_ALIEN 	= 2;

	public Actors() { }
	
	public Actors( Person minos, Person sinner, short gauge, Timestamp finish, short status, Measure measure, Profile profile, 
			Journal journal, ActorsInfo testMode, ActorsInfo reserveLevel, ActorsInfo reserveType, List<ActorsPerformance> lap ) {
		this(gauge, finish, status, measure, profile, journal, testMode, reserveLevel, reserveType, lap);
		this.minos = minos;
		this.internalSinner = sinner;
		this.alienSinnerVersion = 0;
		this.alienSinner = null;
		this.sinnerType = SINNER_TYPE_INNER;
	}

	public Actors( Person minos, Alien sinner, short sinnerVer, short gauge, Timestamp finish, short status, Measure measure, Profile profile, 
			Journal journal, ActorsInfo testMode, ActorsInfo reserveLevel, ActorsInfo reserveType, List<ActorsPerformance> lap ) {
		this(gauge, finish, status, measure, profile, journal, testMode, reserveLevel, reserveType, lap);
		this.minos = minos;
		this.internalSinner = null;
		this.alienSinnerVersion = sinnerVer;
		this.alienSinner = sinner;
		this.sinnerType = SINNER_TYPE_ALIEN;
	}

	public long getId() {
		return this.id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public Measure getMeasure() {
		return this.measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public Person getMinos() {
		return minos;
	}

	public void setMinos(Person minos) {
		this.minos = minos;
	}

	public Person getInternalSinner() {
		return internalSinner;
	}

	public void setInternalSinner(Person internalSinner) {
		this.internalSinner = internalSinner;
	}

	public Alien getAlienSinner() {
		return this.alienSinner;
	}

	public void setAlienSinner(Alien alien) {
		this.alienSinner = alien;
	}

	public short getAlienSinnerVersion() {
		return this.alienSinnerVersion;
	}

	public void setAlienSinnerVersion( short ver ) {
		this.alienSinnerVersion = ver;
	}

	public short getSinnerType() {
		return this.sinnerType;
	}

	public void setSinnerType( short sinnerType ) {
		this.sinnerType = sinnerType;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus( short status ) {
		this.status = status;
	}

	public short getGauge() {
		return gauge;
	}

	public void setGauge( short gauge ) {
		this.gauge = gauge;
	}

	public Timestamp getFinish() {
		return this.finish;
	}

	public void setFinish( Timestamp finish ) {
		this.finish = finish;
	}

	public Timestamp getAssembly() {
		return this.assembly;
	}

	public void setAssembly( Timestamp assembly ) {
		this.assembly = assembly;
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile( Profile profile ) {
		this.profile = profile;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal( Journal journal ) {
		if ( journal == null ) throw new IllegalArgumentException();
		this.journal = journal;
	}

	public ActorsInfo getTestMode() {
		return testMode;
	}

	public void setTestMode( ActorsInfo testMode ) {
		if ( !checkActorsInfo( testMode, ActorsInfo.VARIETY_MODE ) ) throw new IllegalArgumentException( "testMode have illegal value" );
		this.testMode = testMode;
	}

	public ActorsInfo getReserveLevel() {
		return reserveLevel;
	}

	public void setReserveLevel( ActorsInfo reserveLevel ) {
		if ( !checkActorsInfo( reserveLevel, ActorsInfo.VARIETY_LEVEL ) ) throw new IllegalArgumentException( "reserveLevel have illegal value" );
		this.reserveLevel = reserveLevel;
	}

	public ActorsInfo getReserveType() {
		return reserveType;
	}

	public void setReserveType( ActorsInfo reserveType ) {
		if ( !checkActorsInfo( reserveType, ActorsInfo.VARIETY_TYPE ) ) throw new IllegalArgumentException( "reserveType have illegal value" );
		this.reserveType = reserveType;
	}

	public List<ActorsPerformance> getActorsPerformances() {
		return this.actorsPerformances;
	}

	public void setActorsPerformances( List<ActorsPerformance> lst ) {
		this.actorsPerformances = lst;
	}
	
	public ActorsPerformance addActorsPerformance( ActorsPerformance val ) {
		if ( val == null ) return null;
		if ( actorsPerformances == null) actorsPerformances = new ArrayList<ActorsPerformance>();
		actorsPerformances.add( val );
		val.setActor( this );
		return val;
	}

	public ActorsPerformance removeActorsPerformance( ActorsPerformance val ) {
		if ( (actorsPerformances == null) || (val == null) ) return val;
		if ( actorsPerformances.remove( val ) ) val.setActor( null );
		return val;
	}

	@Override
	public int hashCode() {
		return Long.valueOf( id ).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof Actors ) ) return false;        
		if ( this.id != ( ( Actors ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Actors: [" + String.valueOf(id) + "] ";
	}
}