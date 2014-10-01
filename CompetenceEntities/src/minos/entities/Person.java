package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.openjpa.persistence.ReadOnly;
import org.apache.openjpa.persistence.UpdateAction;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


/**
 * The persistent class for the tPersona database table.
 * 
 */
@Cacheable(true)
@Entity
@Table(name="tPersona", schema="dbo")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	// =================================================================================================================
	// Constants
	// =================================================================================================================
	private	static final long	serialVersionUID	= 1L;
	
	public	static final int	STATUS_DISMISS		= 0;
	public	static final int 	STATUS_SUSPEND		= 1;
	public	static final int 	STATUS_PENSIONER	= 2;
	public	static final int 	STATUS_ACTIVE		= 3;

	// =================================================================================================================
	// Fields
	// =================================================================================================================
	@Id
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="tPersonaId")
	private int id;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="BegDA")
	private Timestamp beginDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="EndDA")
	private Timestamp endDate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Drojd")
	private Timestamp birthdate;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="F")
	private String surname;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="I")
	private String name;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="O")
	private String patronymic;

	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Sex")
	private String sex;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="STAT2")
	private int status;
	
	@ReadOnly(value=UpdateAction.IGNORE)
	@Column(name="Tab")
	private String tab;
	
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<PersonPostRelation> personPostRelations;
	
	@OneToOne(mappedBy="person", fetch=FetchType.LAZY)
	private PersonAddon personAddon;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	public Person() { }

	// =================================================================================================================
	// Getter & Setter
	// =================================================================================================================
	public int getId() {
		return id;
	}

	public Timestamp getBeginDate() {
		return this.beginDate;
	}

	public Timestamp getBirthdate() {
		return this.birthdate;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getName() {
		return this.name;
	}

	public String getPatronymic() {
		return this.patronymic;
	}

	public String getSex() {
		return this.sex;
	}

	public int getStatus() {
		return this.status;
	}

	public String getTab() {
		return this.tab;
	}

	public List<PersonPostRelation> getPersonPostRelations() {
		return this.personPostRelations;
	}
	
	public PersonAddon getPersonAddon() {
		return personAddon;
	}

	// =================================================================================================================
	// Methods for/from SuperClass/Interfaces
	// =================================================================================================================
	@Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
    	if(this == obj) return true;
        if(obj == null) return false;        
        if (!(obj instanceof Person)) return false;
        if(this.id != ((Person) obj).id) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person: [" + String.valueOf(id) + " ] " + surname + " " + name + " " + patronymic;
    }

	// =================================================================================================================
	// Methods
	// =================================================================================================================
	/**
	 * make full name from fields surname_name_patronymic
	 * @return string contain full name
	 */
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		sb.append( surname == null ? " " : surname ).append( " " ).
		append( name == null ? " " : name ).append( " " ).
		append( patronymic == null ? " " : patronymic ).append( " " );
		return sb.toString();		
	}
	
	/**
	 * make name as surname and initials
	 * @param initialsAsFirst - initials flag (initials before surname if true)
	 * @param closelyPacket - name flag (name without dot and space if true)
	 * @return string contain surname and initials
	 */
	public String getSurnameAndInitials( boolean initialsAsFirst, boolean closelyPacket ) {
		StringBuilder sb = new StringBuilder();
		if ( !initialsAsFirst ) sb.append( surname == null ? "" : surname ).append( closelyPacket ? "" : " " );
		sb.append( name == null ? "" : String.valueOf( name.charAt( 0 ) ) ).append( closelyPacket ? "" : "." );
		sb.append( patronymic == null ? "" : String.valueOf( patronymic.charAt( 0 ) ) ).append( closelyPacket ? "" : "." );
		if ( initialsAsFirst ) sb.append( closelyPacket ? "" : " " ).append( surname == null ? "" : surname );		
		return sb.toString();		
	}
	
	/**
	 * calculate person's age
	 * @param onDate - date for calculate, if null to use current date
	 * @return person's age
	 */
	public int getAge( java.util.Date onDate ) {
		if ( getBirthdate() == null ) throw new NullPointerException( "Person birthdate is null" );
		
		Calendar today = Calendar.getInstance();
		if ( onDate != null ) today.setTime( onDate );
		
		Calendar birthday = Calendar.getInstance();		
		birthday.setTime( getBirthdate() );
		birthday.add( Calendar.DAY_OF_MONTH, -1 ); // include day of birth
		
		int age = today.get( Calendar.YEAR ) - birthday.get( Calendar.YEAR );
		if ( today.get( Calendar.DAY_OF_YEAR ) <= birthday.get( Calendar.DAY_OF_YEAR ) ) age--;
		return age;
	}
}