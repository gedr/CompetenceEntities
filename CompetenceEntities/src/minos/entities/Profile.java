package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Profile database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Profile", schema="Minos")
@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p")
public class Profile implements Serializable {
	// =================================================================================================================
	// Constants
	// =================================================================================================================
	private static final long serialVersionUID = 1L;
	
	// =================================================================================================================
	// Fields
	// =================================================================================================================
    @TableGenerator(name="Profile_Gen", table="GenL", schema="Minos",
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="PROFILE_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Profile_Gen") 
    private long id;    

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="epost_id", referencedColumnName="tOrgStatDolId")    
	private EstablishedPost establishedPost; 

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="post_id", referencedColumnName="tStatDolSPId")    
	private Post post; 

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="division_id", referencedColumnName="tOrgStruID")    
	private Division division; 
    
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="pp_id", referencedColumnName="id")    
	private ProfilePattern profilePattern; 

	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public Profile() { }

	public Profile( EstablishedPost epost, ProfilePattern pp, Journal journal ) {
		this.establishedPost = epost;
		this.profilePattern = pp;
		this.journal = journal;	
		this.division = null;
		this.post = null;
	}	

	public Profile( Division division, Post post, ProfilePattern pp, Journal journal ) {
		this.establishedPost = null;
		this.profilePattern = pp;
		this.journal = journal;	
		this.division = division;
		this.post = post;
	}	

	// =================================================================================================================
	// Getter & Setter
	// =================================================================================================================
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstablishedPost getEstablishedPost() {
		return this.establishedPost;
	}

	public void setEstablishedPost( EstablishedPost epost ) {
		this.establishedPost = epost;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost( Post post ) {
		this.post = post;
	}

	public Division getDivision() {
		return this.division;
	}

	public void setDivision( Division division ) {
		this.division = division;
	}

	public ProfilePattern getProfilePattern() {
		return this.profilePattern;
	}

	public void setProfilePattern( ProfilePattern pp ) {
		if ( pp == null ) throw new IllegalArgumentException();
		this.profilePattern = pp;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal( Journal journal ) {
		if ( journal == null ) throw new IllegalArgumentException();
		this.journal = journal;
	}

	// =================================================================================================================
	// Methods for/from SuperClass/Interfaces
	// =================================================================================================================
	@Override
	public int hashCode() {
		return Long.valueOf( id ).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof Profile ) ) return false;        
		if ( this.id != ( ( Profile ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profile: [" + String.valueOf(id) + "] ";
	}
}