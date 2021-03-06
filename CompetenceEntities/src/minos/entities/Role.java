package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Role database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Role", schema="Minos")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int FLAG_LENGTH = 15;
	
    @TableGenerator(name="Role_Gen", table="GenI", schema="Minos",  
    		pkColumnName="TableName", valueColumnName="KeyValue",
    		pkColumnValue="ROLE_GEN", allocationSize=1)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="Role_Gen") 
    private int id;    

	@Column(name="name", length=250)
	private String name;

	@Column(name="descr", length=4000)
	private String description;

	@Column(name="flag")
	private byte[] flag;
	
	public Role() { 
		this.id = 0;
		this.name = null;
		this.description = null;
		this.flag = null;		
	}
	
	public Role(String name, String descr, byte[] flag) {
		this.id = 0;
		this.name = name;
		this.description = descr;
		this.flag = flag;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String descr) {
		this.description = descr;
	}

	public byte[] getFlag() {
		return this.flag;
	}

	public void setFlag(byte[] flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if (!(obj instanceof Role)) return false;
		if(this.id != ((Role) obj).id) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role: [" + String.valueOf(id) + "] " + name;
	}
}