package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Indicator database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Indicator", schema="Minos")
@NamedQuery(name="Indicator.findAll", query="SELECT i FROM Indicator i")
public class Indicator implements Serializable, StatusConst {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="Indicator_Gen", table="GenI", schema="Minos",
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="INDICATOR_GEN", allocationSize=3)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Indicator_Gen") 
    private int id;    

	@Column(name="item")
	private short item;

	@Column(name="name")
	private String name;

	@Column(name="status")
	private short status;

	@Column(name="ver")
	private short version;

	//bi-directional many-to-one association to Competence
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="competence_id", referencedColumnName="id")
	private Competence competence;

	//bi-directional many-to-one association to Indicator
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="ancestor", referencedColumnName="id")
	private Indicator ancestorIndicator;

	//bi-directional many-to-one association to Indicator
	@OneToMany(mappedBy="ancestorIndicator", fetch=FetchType.LAZY, 
			cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy(value="ver")
	private List<Indicator> historyIndicators;

	//uni-directional many-to-one association to Level
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="level_id", referencedColumnName="id")
	private Level level;

	//uni-directional many-to-one association to Journal
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	public Indicator() { }
	
	public Indicator(String name, short item, short status, short version, 
			Competence competence, Level level, Indicator ancestorIndicator, Journal journal) {
		this.name = name;
		this.item = item;
		this.status = status;
		this.version = version;
		this.competence = competence;		
		this.level = level;
		this.ancestorIndicator = ancestorIndicator;		
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

	public short getVersion() {
		return this.version;
	}

	public void setVersion(short ver) {
		this.version = ver;
	}

	public Competence getCompetence() {
		return this.competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public Indicator getAncestorIndicator() {
		return this.ancestorIndicator;
	}

	public void setAncestorIndicator(Indicator ancestorIndicator) {
		this.ancestorIndicator = ancestorIndicator;
	}

	public List<Indicator> getHistoryIndicators() {
		return this.historyIndicators;
	}

	public Level getLevel() {
		return this.level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
		if (!(obj instanceof Indicator)) return false;
		if(this.id != ((Indicator) obj).id) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Indicator: [" + String.valueOf(id) + "] " + name;
	}
}