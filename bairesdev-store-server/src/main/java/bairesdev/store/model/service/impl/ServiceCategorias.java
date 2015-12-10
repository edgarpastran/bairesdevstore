package bairesdev.store.model.service.impl;

import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import bairesdev.store.model.dao.CategoriaDAO;
import bairesdev.store.model.dao.ProductoDAO;
import bairesdev.store.model.entity.Categoria;
import bairesdev.store.model.entity.GenericEntity;
import bairesdev.store.model.service.IServiceCategorias;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceCategorias.
 */
@Service("serviceCategorias")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ServiceCategorias implements IServiceCategorias {

	/** The logger. */
	protected static Logger logger = Logger.getLogger("serviceCategorias");
	
	/** The categoria dao. */
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	/** The producto dao. */
	@Autowired
	private ProductoDAO productoDAO;
	
	/* (non-Javadoc)
	 * @see bairesdev.store.model.service.IServiceCategorias#getCategorias()
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
	 * @see bairesdev.store.model.service.IServiceCategorias#getCategoriaPorId(java.lang.Object)
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
	 * @see bairesdev.store.model.service.IServiceCategorias#incluirCategoria(bairesdev.store.model.entity.Categoria)
	 */
	@Override
	public Categoria incluirCategoria(Categoria categoria) {
		this.logger.debug("incluyendo una nueva categoria");
		if (categoria.getCategoria() == null) {
			categoria.setNivel(1);
		}
		else {
			categoria.setNivel(categoria.getCategoria().getNivel()+1);
		}
		categoria.setOrden(this.getProximoOrdenPorCategoriaPadre(categoria.getCategoria()));
		Categoria nuevo = (Categoria)this.categoriaDAO.saveOrUpdate(categoria);
		if (nuevo != null) {
			this.logger.debug("categoria incluida");
		}
		else {
			this.logger.debug("categoria no incluida");
		}
		return nuevo;
	}

	/**
	 * Gets the proximo orden por categoria padre.
	 *
	 * @param categoria the categoria
	 * @return the proximo orden por categoria padre
	 */
	private Integer getProximoOrdenPorCategoriaPadre(Categoria categoria) {
		String queryString = 
		"SELECT MAX(categoria.orden) "+
		"FROM Categoria categoria ";
		if (categoria == null) {
			queryString +=
			"WHERE categoria.categoria = null";
		}
		else {
			queryString +=
			"WHERE categoria.categoria.id = "+categoria.getId();
		}
		List result = this.categoriaDAO.findByQuery(queryString);
		if (result == null || result.get(0) == null) {
			return 1;
		}
		return ((Integer)result.get(0)+1);
	}
	
	/* (non-Javadoc)
	 * @see bairesdev.store.model.service.IServiceCategorias#modificarCategoria(bairesdev.store.model.entity.Categoria)
	 */
	@Override
	public Categoria modificarCategoria(Categoria categoria) {		
		if (categoria != null && categoria.getId() != null) {
			this.logger.debug("modificando la categoria con id = "+categoria.getId());
			Categoria categoriaPadre = null;
			if (new Integer(categoria.getIdCategoriaPadre()) > 0) {
				categoriaPadre = this.getCategoriaPorId(new Integer(categoria.getIdCategoriaPadre()));
			}		
			Integer orden = this.getProximoOrdenPorCategoriaPadre(categoriaPadre);
			categoria.setCategoria(categoriaPadre);
			if (categoriaPadre == null) {
				categoria.setNivel(1);
			}
			else {
				categoria.setNivel(categoriaPadre.getNivel()+1);
			}
			categoria.setOrden(orden);
			Categoria nuevo = (Categoria)this.categoriaDAO.saveOrUpdate(categoria);
			if (nuevo != null) {
				this.logger.debug("categoria modificada");
			}
			else {
				this.logger.debug("categoria no modificada");
			}
			return nuevo;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bairesdev.store.model.service.IServiceCategorias#eliminarCategoria(java.lang.Integer)
	 */
	@Override
	public String eliminarCategoria(Integer id) {
		this.logger.debug("eliminando la categoria con id = "+id);
		Categoria categoria = this.getCategoriaPorId(id);
		if (categoria != null) {
			Long hijas = this.contarCategoriasHijas(id);
			if (hijas > 0) {
				String mensaje = "La categoria tiene "+hijas+" subcategoria(s) hija(s)";
				this.logger.debug(mensaje);
				return mensaje; 
			}
			Long productos = this.contarProductosEnCategoria(id);
			if (productos > 0) {
				String mensaje = "La categoria tiene "+productos+" producto(s) registrado(s)";
				this.logger.debug(mensaje);
				return mensaje;
			}		
			this.categoriaDAO.delete(categoria);
			this.logger.debug("categoria eliminada");
			return "ok";
		}
		return "No se pudo eliminar la categoria";
	}

	/**
	 * Contar categorias hijas.
	 *
	 * @param id the id
	 * @return the long
	 */
	public Long contarCategoriasHijas(Integer id) {
		String queryString = 
		"SELECT COUNT(categoria) "+
		"FROM Categoria categoria "+
		"WHERE categoria.categoria.id = "+id+" "+
		"AND categoria.estatus = '"+GenericEntity.DATA_ACTIVA+"'";
		return this.categoriaDAO.countByQuery(queryString);
	}
	
	/**
	 * Contar productos en categoria.
	 *
	 * @param id the id
	 * @return the long
	 */
	public Long contarProductosEnCategoria(Integer id) {
		String queryString = 
		"SELECT COUNT(producto) "+
		"FROM Producto producto "+
		"WHERE producto.categoria.id = "+id+" "+
		"AND producto.estatus = '"+GenericEntity.DATA_ACTIVA+"'";
		return this.productoDAO.countByQuery(queryString);
	}

}
