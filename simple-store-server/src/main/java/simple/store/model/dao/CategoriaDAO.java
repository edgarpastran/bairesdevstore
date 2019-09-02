package simple.store.model.dao;

import org.springframework.stereotype.Repository;

import simple.store.model.entity.Categoria;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaDAO.
 */
@Repository
public class CategoriaDAO extends GenericDAO {

	/**
	 * Instantiates a new categoria dao.
	 */
	public CategoriaDAO() {
		super(Categoria.class);
	}
	
}
