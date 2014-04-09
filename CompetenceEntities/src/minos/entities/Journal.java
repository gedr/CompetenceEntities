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
    		pkColumnValue="JOURNAL_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Journal_Gen") 
    private long id;    

	@Column(name="createMoment")	
	private Timestamp createMoment;

	@Column(name="editMoment")
	private Timestamp editMoment;
	
	@Column(name="deleteMoment")
	private Timestamp deleteMoment;
	
	@Column(name="creator_id")
	private int creatorID;

	@Column(name="editor_id")
	private int editorID;

	@Column(name="deleter_id")
	private int deleterID;
	
	@Column(name="creatorHost", length=250)
	private String creatorHost;

	@Column(name="editorHost", length=250)
	private String editorHost;

	@Column(name="deleterHost", length=250)
	private String deleterHost;

	@Column(name="creatorLogin", length=250)
	private String creatorLogin;

	@Column(name="editorLogin", length=250)
	private String editorLogin;

	@Column(name="deleterLogin", length=250)
	private String deleterLogin;

	public Journal() { }
	
	public Journal(Timestamp createMoment, int creatorID, String creatorHost,  String creatorLogin,
			Timestamp editMoment, int editorID, String editorHost, String editorLogin,
			Timestamp deleteMoment, int deleterID, String deleterHost, String deleterLogin ) { 
		this.createMoment = createMoment;
		this.editMoment = editMoment;
		this.deleteMoment = deleteMoment;
		
		this.creatorID = creatorID;
		this.editorID = editorID;
		this.deleterID = deleterID;
		
		this.creatorHost = creatorHost;
		this.editorHost = editorHost;		
		this.deleterHost = deleterHost;

		this.creatorLogin = creatorLogin;
		this.editorLogin = editorLogin;		
		this.deleterLogin = deleterLogin;
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

	public Timestamp getDeleteMoment() {
		return this.deleteMoment;
	}

	public void setDeleteMoment(Timestamp deleteMoment) {
		this.deleteMoment = deleteMoment;
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
	
	public int getDeleterID() {
		return deleterID;
	}

	public void setDeleterID(int deleterID) {
		this.deleterID = deleterID;
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

	public String getDeleterHost() {
		return this.deleterHost;
	}

	public void setDeleterHost(String deleterHost) {
		this.deleterHost = deleterHost;
	}	
	
	public String getCreatorLogin() {
		return this.creatorLogin;
	}

	public void setCreatorLogin(String creatorLogin) {
		this.creatorLogin = creatorLogin;
	}

	public String getEditorLogin() {
		return this.editorLogin;
	}

	public void setEditorLogin(String editorLogin) {
		this.editorLogin = editorLogin;
	}

	public String getDeleterLogin() {
		return this.deleterLogin;
	}

	public void setDeleterLogin(String deleterLogin) {
		this.deleterLogin = deleterLogin;
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