package bairesdev.store.model.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DataEstatica.
 */
public abstract class DataEstatica implements Serializable {

    /** The id. */
    private Object id;
    
    /** The nombre. */
    private String nombre;
    
    /**
     * Instantiates a new data estatica.
     */
    protected DataEstatica() {
    	
    }
    
    /**
     * Instantiates a new data estatica.
     *
     * @param id the id
     * @param nombre the nombre
     */
    protected DataEstatica(Object id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Object getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
		if (obj != null) {
		    if (obj instanceof DataEstatica) {
		    	return ((DataEstatica) obj).getId().equals(this.id);
		    } else {
		    	return obj.equals(this.id);
		    }
		}
		return false;	    	    
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {	
    	return this.nombre;
    }
    
}
