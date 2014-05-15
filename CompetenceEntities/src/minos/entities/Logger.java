package minos.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the Logger database table.
 * 
 */
@Cacheable( false )
@Entity
@Table( name="Logger", schema="Minos" )
@NamedQuery( name="Logger.findAll", query="SELECT l FROM Logger l" )
public class Logger implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column( name="id" )
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private long id;

	@Column( name="external_id" )
	private long externalId;

	@Column( name="moment" )
	private Timestamp moment;

	@Column( name="operationCode" )
	private short operationCode;

	@Column( name="tableCode" )
	private int tableCode;
	
	@Basic( fetch=FetchType.LAZY )
	@Column( name="summary", length=10000000 )
	private String summary;

	public static final int TABLE_CODE_UNKNOWN = 0;
	
	public static final short OPERATION_CODE_DML_CREATE			= 1;
	public static final short OPERATION_CODE_DML_UPDATE			= 2;
	public static final short OPERATION_CODE_DML_DELETE			= 3;
	public static final short OPERATION_CODE_DML_CREATE_BULK	= 11;
	public static final short OPERATION_CODE_DML_UPDATE_BULK	= 12;
	public static final short OPERATION_CODE_DML_DELETE_BULK	= 13;

	public Logger() { }
	
	public Logger( Timestamp moment, long externalId, short operationCode, 
			int tableCode, String summary) { 
		this.externalId = externalId;
		this.moment = ( moment != null ? moment : new Timestamp( System.currentTimeMillis() ) );
		this.operationCode = operationCode;
		this.tableCode = tableCode;
		this.summary = summary;
	}	

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getExternalId() {
		return this.externalId;
	}

	public void setExternalId( long externalId ) {
		this.externalId = externalId;
	}

	public Timestamp getMoment() {
		return this.moment;
	}

	public void setMoment( Timestamp moment ) {
		this.moment = moment;
	}

	public short getOperationCode() {
		return this.operationCode;
	}

	public void setOperationCode( short operationCode ) {
		this.operationCode = operationCode;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary( String summary ) {
		this.summary = summary;
	}

	public int getTableCode() {
		return this.tableCode;
	}

	public void setTableCode( int tableCode ) {
		this.tableCode = tableCode;
	}

	@Override
	public int hashCode() {
		return Long.valueOf( id ).hashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;        
		if ( !( obj instanceof Logger ) ) return false;        
		if ( this.id != ( ( Logger ) obj ).id ) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Logger: [" + String.valueOf( id ) + "] ";
	}
}