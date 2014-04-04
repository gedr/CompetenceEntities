package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Competence database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Competence", schema="Minos")
@NamedQuery(name="Competence.findAll", query="SELECT c FROM Competence c")
public class Competence implements Serializable, VarietyConst, StatusConst {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="Competence_Gen", table="GenI", schema="Minos",  
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="COMPETENCE_GEN", allocationSize=3)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Competence_Gen") 
    private int id;    

	@Column(name="name", length=1000)
	private String name;

	@Basic(fetch=FetchType.LAZY)
	@Column(name="descr", length=8000)
	private String description;

	@Column(name="item")
	private short item;

	@Column(name="status")
 	private short status;

	@Column(name="variety")
	private short variety;

	@Column(name="ver")
	private short version;

	//bi-directional many-to-one association to Catalog
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="catalog_id", referencedColumnName="id")
	private Catalog catalog;

	//bi-directional many-to-one association to Competence
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ancestor", referencedColumnName="id")
	private Competence ancestorCompetence;

	//bi-directional many-to-one association to Competence
	@OneToMany(mappedBy="ancestorCompetence", fetch=FetchType.LAZY, 
			cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="ver")
	private List<Competence> historyCompetences;
	
	//bi-directional many-to-one association to MinosIndicator
	@OneToMany(mappedBy="competence", fetch=FetchType.LAZY, 
			cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="level item")
	private List<Indicator> indicators;
	
	//uni-directional many-to-one association to Journal
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	public Competence() { }
	
	public Competence(String name, String descr, short item, short status, short variety, short version, 
			Catalog catalog, Competence ancestorCompetence, List<Indicator> indicators, Journal journal) {
		this.name = name;
		this.description = descr;
		this.item = item;
		this.status = status;
		this.variety = variety;
		this.version = version;
		this.catalog = catalog;
		this.ancestorCompetence = ancestorCompetence;
		this.indicators = indicators;
		this.journal = journal;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String descr) {
		this.description = descr;
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

	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Competence getAncestorCompetence() {
		return this.ancestorCompetence;
	}

	public void setAncestorCompetence(Competence ancestorCompetence) {
		this.ancestorCompetence = ancestorCompetence;
	}

	public List<Competence> getHistoryCompetences() {
		return this.historyCompetences;
	}

	public List<Indicator> getIndicators() {
		return this.indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}
	
	public Indicator addIndicator(Indicator indicator) {
		if(indicators == null) indicators = new ArrayList<>();			
		indicators.add(indicator);
		indicator.setCompetence(this);
		return indicator;
	}

	public Indicator removeIndicator(Indicator indicator) {
		if( (indicators == null) || (indicator == null) ) return indicator;		
		if(indicators.remove(indicator)) indicator.setCompetence(null);
		return indicator;
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
		if (!(obj instanceof Competence)) return false;
		if(this.id != ((Competence) obj).id) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Competence: [" + String.valueOf(id) + "] " + name;
	}
}