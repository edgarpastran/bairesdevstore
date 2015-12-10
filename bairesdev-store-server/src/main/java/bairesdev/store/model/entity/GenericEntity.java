package bairesdev.store.model.entity;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericEntity.
 */
public abstract class GenericEntity implements Serializable {
	
	/** The data activa. */
	public static String DATA_ACTIVA = "A";
	
	/** The data inactiva. */
	public static String DATA_INACTIVA = "I";
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;		

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object other) {
		if ((this == other))
		    return true;
		if ((other == null))
		    return false;					
		if (!(other.getClass().equals(this.getClass())))
		    return false;
		GenericEntity castOther = (GenericEntity) other;
		return ((this.getPrimaryKey() == castOther.getPrimaryKey()) || 
			(this.getPrimaryKey() != null && 
			castOther.getPrimaryKey() != null && 
			this.getPrimaryKey().equals(castOther.getPrimaryKey())));	
    }

	/**
	 * Gets the primary key.
	 *
	 * @return the primary key
	 */
	public abstract Object getPrimaryKey();

}
