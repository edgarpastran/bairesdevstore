package simple.store.model.service;

import java.util.List;

import simple.store.model.entity.Categoria;

// TODO: Auto-generated Javadoc
/**
 * The Interface IServiceCategorias.
 */
public interface IServiceCategorias {

	/**
	 * Gets the categorias.
	 *
	 * @return the categorias
	 */
	public abstract List<Categoria> getCategorias();
	
	/**
	 * Gets the categoria por id.
	 *
	 * @param id the id
	 * @return the categoria por id
	 */
	public abstract Categoria getCategoriaPorId(Object id);
	
	/**
	 * Incluir categoria.
	 *
	 * @param categoria the categoria
	 * @return the categoria
	 */
	public abstract Categoria incluirCategoria(Categoria categoria);
	
	/**
	 * Modificar categoria.
	 *
	 * @param categoria the categoria
	 * @return the categoria
	 */
	public abstract Categoria modificarCategoria(Categoria categoria);
	
	/**
	 * Eliminar categoria.
	 *
	 * @param id the id
	 * @return the string
	 */
	public abstract String eliminarCategoria(Integer id);
}
