package simple.store.model.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
@XmlRootElement(name="categoria")
public class Categoria extends GenericEntity implements Comparable<Categoria> {

	/** The id. */
	@Id
	@SequenceGenerator(name="CATEGORIA_ID_GENERATOR", sequenceName="categoria_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIA_ID_GENERATOR")
	private Integer id;

	/** The descripcion. */
	private String descripcion;	

	/** The nivel. */
	private Integer nivel;

	/** The nombre. */
	private String nombre;

	/** The orden. */
	private Integer orden;

	/** The estatus. */
	private String estatus = DATA_ACTIVA;
	
	
	/** The categoria. */
	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria_padre")
	private Categoria categoria;

	/** The id categoria padre. */
	@Transient
	private String idCategoriaPadre;
	
	/**
	 * Sets the id categoria padre.
	 *
	 * @param idCategoriaPadre the new id categoria padre
	 */
	public void setIdCategoriaPadre(String idCategoriaPadre) {
		this.idCategoriaPadre = idCategoriaPadre;
	}

	/**
	 * Gets the id categoria padre.
	 *
	 * @return the id categoria padre
	 */
	public String getIdCategoriaPadre() {
		if (this.idCategoriaPadre != null) {
			return this.idCategoriaPadre;
		}
		else if (this.getCategoria() != null) {
			return this.getCategoria().getIdString();
		}
		return "-1";
	}
	
	//bi-directional many-to-one association to Categoria	
	/*
	@OneToMany(mappedBy="categoria")
	private List<Categoria> categorias;
	
	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categoria")
	private List<Producto> productos;
	*/
	/**
	 * Instantiates a new categoria.
	 */
	public Categoria() {
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
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	public Integer getNivel() {
		return this.nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel the new nivel
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
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
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public Integer getOrden() {
		return this.orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden the new orden
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
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
		
	/*
	public List<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Categoria addCategoria(Categoria categoria) {
		getCategorias().add(categoria);
		categoria.setCategoria(this);

		return categoria;
	}

	public Categoria removeCategoria(Categoria categoria) {
		getCategorias().remove(categoria);
		categoria.setCategoria(null);

		return categoria;
	}
	
	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setCategoria(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setCategoria(null);

		return producto;
	}
	*/
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
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		if (this.getCategoria() == null) {
			if (this.getOrden() != null) {
				if (this.getOrden() < 10) {
					return "0"+this.getOrden().toString();
				}
				return this.getOrden().toString();
			}
			return "";
		}
		else {
			if (this.getOrden() < 10) {
				return this.getCategoria().getCodigo()+".0"+this.getOrden().toString();
			}
			return this.getCategoria().getCodigo()+"."+this.getOrden().toString();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getCodigo()+" - "+this.getNombre();
	}
	
	/* (non-Javadoc)
	 * @see simple.store.model.entity.GenericEntity#getPrimaryKey()
	 */
	@Override
	public Object getPrimaryKey() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Categoria other) {
		return this.getCodigo().compareTo(other.getCodigo());
	}

}