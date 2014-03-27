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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_id", referencedColumnName="tPersonaId")
	private Person creator;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="editor_id", referencedColumnName="tPersonaId")
	private Person editor;

	@Column(name="creatorHost", length=250)
	private String creatorHost;

	@Column(name="editorHost", length=250)
	private String editorHost;

	public Journal() { }

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

	public Person getCreator() {
		return this.creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Person getEditor() {
		return this.editor;
	}

	public void setEditor(Person editor) {
		this.editor = editor;
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