package simple.store.model.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import simple.store.model.beans.Medida;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the producto database table.
 * 
 */
@XmlRootElement(name="producto")
public class Producto extends GenericEntity {

	/** The id. */
	private Integer id;

	/** The cantidad. */
	private Integer cantidad;

	/** The descripcion. */
	private String descripcion;

	/** The medida. */
	private String medida;

	/** The nombre. */
	private String nombre;

	/** The precio. */
	private Double precio;

	/** The estatus. */
	private String estatus = DATA_ACTIVA;
	
	/** The categoria. */
	//bi-directional many-to-one association to Categoria
	private Categoria categoria;

	/** The id categoria. */
	private String idCategoria;
	
	/**
	 * Sets the id categoria.
	 *
	 * @param idCategoria the new id categoria
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Gets the id categoria.
	 *
	 * @return the id categoria
	 */
	public String getIdCategoria() {
		if (this.idCategoria != null) {
			return this.idCategoria;
		}
		else if (this.getCategoria() != null) {
			return this.getCategoria().getIdString();
		}
		return "-1";
	}

	/**
	 * Instantiates a new producto.
	 */
	public Producto() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return this.cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the estatus.
	 *
	 * @return the estatus
	 */
	public String getEstatus() {
		return this.estatus;
	}

	/**
	 * Sets the estatus.
	 *
	 * @param estatus the new estatus
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * Gets the medida.
	 *
	 * @return the medida
	 */
	public String getMedida() {
		return this.medida;
	}

	/**
	 * Sets the medida.
	 *
	 * @param medida the new medida
	 */
	public void setMedida(String medida) {
		this.medida = medida;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public Double getPrecio() {
		return this.precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio the new precio
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return this.categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the id string.
	 *
	 * @return the id string
	 */
	public String getIdString() {
		if (this.id == null) {
			return null;
		}
		return this.id.toString().replaceAll("\\.", "");
	}

	/**
	 * Gets the medida string.
	 *
	 * @return the medida string
	 */
	public String getMedidaString() {
		return Medida.getNombrePorId(this.medida);
	}
	
	/* (non-Javadoc)
	 * @see simple.store.model.entity.GenericEntity#getPrimaryKey()
	 */
	@Override
	public Object getPrimaryKey() {
		return this.id;
	}

}