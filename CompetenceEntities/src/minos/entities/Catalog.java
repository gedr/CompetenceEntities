package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Catalog database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Catalog", schema="Minos")
@NamedQuery(name="Catalog.findAll", query="SELECT c FROM Catalog c")
public class Catalog implements Serializable, VarietyConst, StatusConst {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="Catalog_Gen", table="GenI", schema="Minos", 
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="CATALOG_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Catalog_Gen") 
    private int id;    
    
    @Column(name = "name", length = 1000)
    private String name;
    
    @Column(name = "variety")
    private short variety;

    @Column(name = "item")
    private short item;

	@Column(name="status")
	private short status;	

	@Column(name="ver")
	private short version;	

	@OneToMany(mappedBy="catalog", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="item")
	private List<Competence> competences;

	@OneToMany(mappedBy="catalog", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="item")
	private List<ProfilePattern> profilePatterns;

    // catalog's tree
    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="parent_id", referencedColumnName="id")    
	private Catalog parentCatalog; 

	@OneToMany(mappedBy="parentCatalog", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="item")
	private List<Catalog> subCatalogs;

	// history tree
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ancestor_id", referencedColumnName="id")
	private Catalog ancestor;

	@OneToMany(mappedBy="ancestor", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="version")
	private List<Catalog> historyList;

	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	public Catalog() { }

   	public Catalog(String name, short item, short status, short variety, short version,
   			Catalog parentCatalog, List<Catalog> subCatalogs, Catalog ancestor,
   			List<Competence> competences, Journal journal) { 
   		this.name = name;
   		this.item = item;
   		this.status = status;
   		this.variety = variety;
   		this.version = version;
   		this.parentCatalog = parentCatalog;
   		this.subCatalogs = subCatalogs;
   		this.ancestor = ancestor;   		
   		this.competences = competences;
   		this.profilePatterns = null;
   		this.journal = journal;
   		this.historyList = null;
   	}   	

   	public Catalog(String name, short item, short status, short variety, short version,
   			Catalog parentCatalog, List<Catalog> subCatalogs, Catalog ancestor,
   			Journal journal, List<ProfilePattern> profilePatterns) { 
   		this.name = name;
   		this.item = item;
   		this.status = status;
   		this.variety = variety;
   		this.version = version;
   		this.parentCatalog = parentCatalog;
   		this.subCatalogs = subCatalogs;
   		this.ancestor = ancestor;   		
   		this.profilePatterns = profilePatterns;
   		this.competences = null;
   		this.journal = journal;
   	}   	

   	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getItem() {
		return this.item;
	}

	public void setItem(short item) {
		this.item = item;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getVariety() {
		return this.variety;
	}

	public void setVariety(short variety) {
		this.variety = variety;
	}

	public short getVersion() {
		return this.version;
	}

	public void setVersion(short ver) {
		this.version = ver;
	}

	public Catalog getParentCatalog() {
		return this.parentCatalog;
	}

	public void setParentCatalog(Catalog parentCatalog) {
		this.parentCatalog = parentCatalog;
	}

	public List<Catalog> getSubCatalogs() {
		return this.subCatalogs;
	}

	public void setSubCatalogs(List<Catalog> subCatalogs) {
		this.subCatalogs = subCatalogs;
	}
	
	public Catalog addSubCatalog(Catalog catalog) {
		if(subCatalogs == null) subCatalogs = new ArrayList<Catalog>();
		subCatalogs.add(catalog);
		catalog.setParentCatalog(this);
		return catalog;
	}

	public Catalog removeSubCatalog(Catalog catalog) {
		if( (subCatalogs == null) || (catalog == null) ) return catalog;
		if(subCatalogs.remove(catalog)) catalog.setParentCatalog(null);
		return catalog;
	}
	
	public Catalog getAncestor() {
		return this.ancestor;
	}

	public void setAncestor( Catalog ancestor ) {
		this.ancestor = ancestor;
	}

	public List<Catalog> getHistoryList() {
		return this.historyList;
	}

	public List<Competence> getCompetences() {
		return this.competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}
	
	public Competence addCompetence(Competence competence) {
		if ( competence == null ) return null;
		if(competences == null) this.competences = new ArrayList<Competence>();
		this.competences.add(competence);
		competence.setCatalog(this);
		return competence;
	}

	public Competence removeCompetence(Competence competence) {
		if( (competences == null) || (competence == null) ) return competence;
		if(competences.remove(competence)) competence.setCatalog(null);
		return competence;
	}

	public List<ProfilePattern> getProfilePatterns() {
		return profilePatterns;
	}

	public void setProfilePatterns( List<ProfilePattern> profilePatterns ) {
		this.profilePatterns = profilePatterns;
	}
	
	public ProfilePattern addProfilePattern( ProfilePattern profilePattern ) {
		if ( profilePatterns == null ) profilePatterns = new ArrayList<ProfilePattern>();
		profilePatterns.add( profilePattern );
		profilePattern.setCatalog( this );
		return profilePattern;
	}

	public ProfilePattern removeProfilePattern( ProfilePattern profilePattern ) {
		if ( ( profilePatterns == null ) || ( profilePattern == null ) ) return profilePattern;
		if ( profilePatterns.remove( profilePattern ) ) profilePattern.setCatalog( null );
		return profilePattern;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;        
		if (!(obj instanceof Catalog)) return false;        
		if(this.id != ((Catalog) obj).id) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Catalog: [" + String.valueOf(id) + "] " + name;
	}
}