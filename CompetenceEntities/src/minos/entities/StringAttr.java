package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the StringAttr database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="StringAttr", schema="Minos")
@NamedQuery(name="StringAttr.findAll", query="SELECT s FROM StringAttr s")
public class StringAttr implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name="StringAttr_Gen", table="GenL", schema="Minos",
	pkColumnName="TableName", valueColumnName="KeyValue",
	pkColumnValue="STRATTR_GEN", allocationSize=1)
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="StringAttr_Gen") 
	private long id;

	@Column(name="external_id")
	private Integer externalId;

	private int item;

	//uni-directional many-to-one association to Journal
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="journal_id", referencedColumnName="id")
	private Journal journal;

	@Column(name="key", length=1000)
	private String key;

	@Basic(fetch=FetchType.LAZY)
	@Column(name="value", length=1000000)
	private String value;

	@Column(name="variety")
	private short variety;

	public StringAttr() { }
	
	public StringAttr(String key, String value, int item, short variety, 
			Integer externalId, Journal journal) { 
		this.key = key;
		this.value = value;
		this.item = item;
		this.variety = variety;
		this.externalId = externalId;
		this.journal = journal;				
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getExternalId() {
		return this.externalId;
	}

	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}

	public int getItem() {
		return this.item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public Journal getJournal() {
		return this.journal;
	}

	public void setJournalId(Journal journal) {
		this.journal = journal;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public short getVariety() {
		return this.variety;
	}

	public void setVariety(short variety) {
		this.variety = variety;
	}

	@Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
        if(obj == null) return false;        
        if (!(obj instanceof StringAttr)) return false;
        if(this.id != ((StringAttr) obj).id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "StringAttr: [" + String.valueOf(id) + " ] ";
    }
}