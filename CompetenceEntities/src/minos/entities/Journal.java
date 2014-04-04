package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the Journal database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Journal", schema="Minos")
@NamedQuery(name="Journal.findAll", query="SELECT j FROM Journal j")
public class Journal implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableGenerator(name="Journal_Gen", table="GenL", schema="Minos",
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="JOURNAL_GEN", allocationSize=10)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Journal_Gen") 
    private long id;    

	@Column(name="createMoment")	
	private Timestamp createMoment;

	@Column(name="editMoment")
	private Timestamp editMoment;
	
	@Column(name="removeMoment")
	private Timestamp removeMoment;
	
	@Column(name="creator_id")
	private int creatorID;

	@Column(name="editor_id")
	private int editorID;

	@Column(name="creatorHost", length=250)
	private String creatorHost;

	@Column(name="editorHost", length=250)
	private String editorHost;

	public Journal() { }
	
	public Journal(Timestamp createMoment, Timestamp editMoment, Timestamp removeMoment, 
			int creatorID, int editorID, String creatorHost, String editorHost) { 
		this.createMoment = createMoment;
		this.editMoment = editMoment;
		this.removeMoment = removeMoment;
		this.creatorID = creatorID;
		this.editorID = editorID;
		this.creatorHost = creatorHost;
		this.editorHost = editorHost;		
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreateMoment() {
		return this.createMoment;
	}

	public void setCreateMoment(Timestamp createMoment) {
		this.createMoment = createMoment;
	}

	public Timestamp getEditMoment() {
		return this.editMoment;
	}

	public void setEditMoment(Timestamp editMoment) {
		this.editMoment = editMoment;
	}

	public Timestamp getRemoveMoment() {
		return this.removeMoment;
	}

	public void setRemoveMoment(Timestamp removeMoment) {
		this.removeMoment = removeMoment;
	}

	public int getCreatorID() {
		return this.creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public int getEditorID() {
		return this.editorID;
	}

	public void setEditorID(int editorID) {
		this.editorID = editorID;
	}

	public String getCreatorHost() {
		return this.creatorHost;
	}

	public void setCreatorHost(String creatorHost) {
		this.creatorHost = creatorHost;
	}

	public String getEditorHost() {
		return this.editorHost;
	}

	public void setEditorHost(String editorHost) {
		this.editorHost = editorHost;
	}
	
	@Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
        if(obj == null) return false;        
        if (!(obj instanceof Journal)) return false;
        if(this.id != ((Journal) obj).id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Journal: [" + String.valueOf(id) + " ] ";
    }
}