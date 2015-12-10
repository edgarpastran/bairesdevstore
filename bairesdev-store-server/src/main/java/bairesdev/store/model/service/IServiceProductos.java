package bairesdev.store.model.service;

import java.util.List;

import bairesdev.store.model.beans.Medida;
import bairesdev.store.model.entity.Categoria;
import bairesdev.store.model.entity.Producto;

// TODO: Auto-generated Javadoc
/**
 * The Interface IServiceProductos.
 */
public interface IServiceProductos {

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	public abstract List<Producto> getProductos();		
	
	/**
	 * Gets the categorias.
	 *
	 * @return the categorias
	 */
	public abstract List<Categoria> getCategorias();
	
	/**
	 * Gets the medidas.
	 *
	 * @return the medidas
	 */
	public abstract List<Medida> getMedidas();
	
	/**
	 * Gets the producto por id.
	 *
	 * @param id the id
	 * @return the producto por id
	 */
	public abstract Producto getProductoPorId(Object id);
	
	/**
	 * Gets the categoria por id.
	 *
	 * @param id the id
	 * @return the categoria por id
	 */
	public abstract Categoria getCategoriaPorId(Object id);
	
	/**
	 * Gets the medida por id.
	 *
	 * @param id the id
	 * @return the medida por id
	 */
	public abstract Medida getMedidaPorId(Object id);
	
	/**
	 * Incluir producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	public abstract Producto incluirProducto(Producto producto);
	
	/**
	 * Modificar producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	public abstract Producto modificarProducto(Producto producto);
	
	/**
	 * Eliminar producto.
	 *
	 * @param id the id
	 * @return the string
	 */
	public abstract String eliminarProducto(Integer id);
	
}
