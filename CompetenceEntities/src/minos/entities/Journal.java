package minos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the Journal database table.
 * 
 */
@Entity
@NamedQuery(name="Journal.findAll", query="SELECT j FROM Journal j")
public class Journal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Timestamp createMoment;

	@Column(name="creator_id")
	private int creatorId;

	private String creatorHost;

	private Timestamp editMoment;

	@Column(name="editor_id")
	private int editorId;

	private String editorHost;

	private Timestamp removeMoment;

	public Journal() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateMoment() {
		return this.createMoment;
	}

	public void setCreateMoment(Timestamp createMoment) {
		this.createMoment = createMoment;
	}

	public int getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorHost() {
		return this.creatorHost;
	}

	public void setCreatorHost(String creatorHost) {
		this.creatorHost = creatorHost;
	}

	public Timestamp getEditMoment() {
		return this.editMoment;
	}

	public void setEditMoment(Timestamp editMoment) {
		this.editMoment = editMoment;
	}

	public int getEditorId() {
		return this.editorId;
	}

	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}

	public String getEditorHost() {
		return this.editorHost;
	}

	public void setEditorHost(String editorHost) {
		this.editorHost = editorHost;
	}

	public Timestamp getRemoveMoment() {
		return this.removeMoment;
	}

	public void setRemoveMoment(Timestamp removeMoment) {
		this.removeMoment = removeMoment;
	}

}