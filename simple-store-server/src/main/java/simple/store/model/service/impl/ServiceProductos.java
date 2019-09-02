package simple.store.model.service.impl;

import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import simple.store.model.beans.Medida;
import simple.store.model.dao.CategoriaDAO;
import simple.store.model.dao.ProductoDAO;
import simple.store.model.entity.Categoria;
import simple.store.model.entity.GenericEntity;
import simple.store.model.entity.Producto;
import simple.store.model.service.IServiceProductos;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceProductos.
 */
@Service("serviceProductos")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceProductos implements IServiceProductos {

	/** The logger. */
	protected static Logger logger = Logger.getLogger("serviceCategorias");
	
	/** The categoria dao. */
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	/** The producto dao. */
	@Autowired
	private ProductoDAO productoDAO;
	
	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#getProductos()
	 */
	@Override
	public List<Producto> getProductos() {
		this.logger.debug("buscando todos los productos");
		String queryString = 
		"SELECT producto "+
		"FROM Producto producto "+
		"WHERE producto.estatus = '"+GenericEntity.DATA_ACTIVA+"' ";
		List<Producto> productos = (List<Producto>)this.productoDAO.findByQuery(queryString);
		return productos;
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#getCategorias()
	 */
	@Override
	public List<Categoria> getCategorias() {
		this.logger.debug("buscando todas las categorias");
		String queryString = 
		"SELECT categoria "+
		"FROM Categoria categoria "+
		"WHERE categoria.estatus = '"+GenericEntity.DATA_ACTIVA+"' ";
		List<Categoria> categorias = (List<Categoria>)this.categoriaDAO.findByQuery(queryString);
		Collections.sort(categorias);
		return categorias; 
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#getMedidas()
	 */
	@Override
	public List<Medida> getMedidas() {
		this.logger.debug("buscando todas las medidas");
		return Medida.getListado();
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#getProductoPorId(java.lang.Object)
	 */
	@Override
	public Producto getProductoPorId(Object id) {
		this.logger.debug("buscando el producto con id = "+id);
		if (id != null) {
			GenericEntity genericEntity = this.productoDAO.get(id);
			if (genericEntity != null) {
				this.logger.debug("producto encontrado");
				return (Producto)genericEntity;
			}
		}
		this.logger.debug("producto no encontrado");
		return null;
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#getCategoriaPorId(java.lang.Object)
	 */
	@Override
	public Categoria getCategoriaPorId(Object id) {
		this.logger.debug("buscando la categoria con id = "+id);
		if (id != null) { 
			GenericEntity genericEntity = this.categoriaDAO.get(id);
			if (genericEntity != null) {
				this.logger.debug("categoria encontrada");
				return (Categoria)genericEntity;
			} 
		}
		this.logger.debug("categoria no encontrada");
		return null;
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#getMedidaPorId(java.lang.Object)
	 */
	@Override
	public Medida getMedidaPorId(Object id) {
		this.logger.debug("buscando la medida con id = "+id);
		return Medida.getMedidaPorId(id);
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#incluirProducto(simple.store.model.entity.Producto)
	 */
	@Override
	public Producto incluirProducto(Producto producto) {
		this.logger.debug("incluyendo un nuevo producto");
		Producto nuevo = (Producto)this.productoDAO.saveOrUpdate(producto);
		if (nuevo != null) {
			this.logger.debug("producto incluido");
		}
		else {
			this.logger.debug("producto no incluido");
		}
		return nuevo;
	}

	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#modificarProducto(simple.store.model.entity.Producto)
	 */
	@Override
	public Producto modificarProducto(Producto producto) {
		if (producto != null && producto.getId() != null) {
			this.logger.debug("modificando el producto con id = "+producto.getId());
			Categoria categoria = this.getCategoriaPorId(new Integer(producto.getIdCategoria()));
			producto.setCategoria(categoria);			
			Producto nuevo = (Producto)this.productoDAO.saveOrUpdate(producto);
			if (nuevo != null) {
				this.logger.debug("producto modificado");
			}
			else {
				this.logger.debug("producto no modificado");
			}
			return nuevo;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see simple.store.model.service.IServiceProductos#eliminarProducto(java.lang.Integer)
	 */
	@Override
	public String eliminarProducto(Integer id) {
		this.logger.debug("eliminando el producto con id = "+id);
		Producto producto = this.getProductoPorId(id);
		if (producto != null) {
			this.productoDAO.delete(producto);
			this.logger.debug("producto eliminado");
			return "ok";
		}
		return "No se pudo eliminar el producto";
	}

}
