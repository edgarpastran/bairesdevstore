package simple.store.model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Medida.
 */
@XmlRootElement(name="medida")
public class Medida extends DataEstatica {

	/** The Constant UNIDAD. */
	public static final Medida UNIDAD = new Medida("U", "Unidad");
	
	/** The Constant DOCENA. */
	public static final Medida DOCENA = new Medida("D", "Docena");
    
    /** The Constant METRO. */
    public static final Medida METRO = new Medida("M", "Metro");
    
    /** The Constant KILOGRAMO. */
    public static final Medida KILOGRAMO = new Medida("K", "Kilogramo");
        
    /**
     * Instantiates a new medida.
     */
    public Medida() {
    	super();
    }
    
    /**
     * Instantiates a new medida.
     *
     * @param id the id
     * @param nombre the nombre
     */
    private Medida(Object id, String nombre) {
    	super(id, nombre);
    }

    /**
     * Gets the listado.
     *
     * @return the listado
     */
    public static List<Medida> getListado() {
		List<Medida> listado = new ArrayList<Medida>();		
		listado.add(UNIDAD);
		listado.add(DOCENA);
		listado.add(METRO);
		listado.add(KILOGRAMO);
		return listado;
    }
    
    /**
     * Gets the nombre por id.
     *
     * @param id the id
     * @return the nombre por id
     */
    public static String getNombrePorId(Object id) {
		List<Medida> listado = getListado();		
		for (Medida medida: listado) {
		    if (medida.getId().equals(id)) {
		    	return medida.getNombre();
		    }
		}
		return null;
    }
    
    /**
     * Gets the id por nombre.
     *
     * @param nombre the nombre
     * @return the id por nombre
     */
    public static Object getIdPorNombre(String nombre) {
		List<Medida> listado = getListado();
		for (Medida medida: listado) {
		    if (medida.getNombre().equals(nombre)) {
		    	return medida.getId();
		    }
		}
		return null;
    }
    
    /**
     * Gets the medida por id.
     *
     * @param id the id
     * @return the medida por id
     */
    public static Medida getMedidaPorId(Object id) {
		List<Medida> listado = getListado();
		
		for (Medida medida: listado) {
		    if (medida.getId().equals(id)) {
		    	return medida;
		    }
		}
		return null;
    }

}
