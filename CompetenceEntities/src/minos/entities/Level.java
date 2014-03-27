package minos.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Level database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="Level", schema="Minos")
@NamedQuery(name="Level.findAll", query="SELECT l FROM Level l")
public class Level implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="name", length=250)
	private String name;

	@Column(name="price")
	private double price;

	public static final short LEVEL_COUNT = 5;
	
	public Level() { }
	
	public Level(String name, double price) {
		this.name = name;
		this.price = price;
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

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
    	if(obj == null) return false;        
    	if (!(obj instanceof Level)) return false;
    	if(this.id != ((Level) obj).id) return false;
    	return true;
    }

    @Override
    public String toString() {
        return "Level: [" + String.valueOf(id) + " ] " + name;
    }
}