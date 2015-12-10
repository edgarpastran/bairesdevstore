package bairesdev.store.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bairesdev.store.model.entity.GenericEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericDAO.
 */
public abstract class GenericDAO {

	/** The generic entity class. */
	private Class genericEntityClass;
	
	/**
	 * Instantiates a new generic dao.
	 *
	 * @param genericEntityClass the generic entity class
	 */
	public GenericDAO(Class genericEntityClass) {
		this.genericEntityClass = genericEntityClass;
	}
	
	/** The entity manager. */
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * Query all.
	 *
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List queryAll() {
		Query query = entityManager.createQuery("SELECT entity FROM "+this.genericEntityClass.getSimpleName()+" entity");
		List result = query.getResultList();
		return result;
	}

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the generic entity
	 */
	@Transactional(readOnly = true)
	public GenericEntity get(Object id) {
		return (GenericEntity)entityManager.find(this.genericEntityClass, id);
	}

	/**
	 * Save.
	 *
	 * @param genericEntity the generic entity
	 * @return the generic entity
	 */
	@Transactional
	public GenericEntity save(GenericEntity genericEntity) {
		entityManager.persist(genericEntity);
		return this.get(genericEntity.getPrimaryKey());
	}

	/**
	 * Update.
	 *
	 * @param genericEntity the generic entity
	 * @return the generic entity
	 */
	@Transactional
	public GenericEntity update(GenericEntity genericEntity) {
		entityManager.merge(genericEntity);
		return genericEntity;
	}
	
	/**
	 * Save or update.
	 *
	 * @param genericEntity the generic entity
	 * @return the generic entity
	 */
	@Transactional
	public GenericEntity saveOrUpdate(GenericEntity genericEntity) {
		try {
		    if (genericEntity.getPrimaryKey() == null) {
		    	throw new EntityNotFoundException();	
		    }
		    else if (this.get(genericEntity.getPrimaryKey()) == null) {
		    	throw new EntityNotFoundException();
		    }
		    return this.update(genericEntity);
		} 
		catch(EntityNotFoundException enfe) {
		    return this.save(genericEntity);
		}
	}
	
	/**
	 * Save or update.
	 *
	 * @param genericEntity the generic entity
	 * @return the generic entity
	 */
	@Transactional
	public GenericEntity saveOnlyIfNotExists(GenericEntity genericEntity) {
	    if (genericEntity.getPrimaryKey() == null) {
	    	return this.save(genericEntity);
	    }
    	GenericEntity object = this.get(genericEntity.getPrimaryKey()); 
    	if (object == null) {
    		return this.save(genericEntity);
    	}
    	return object;
	}
	
	/**
	 * Delete.
	 *
	 * @param genericEntity the generic entity
	 */
	@Transactional
	public void delete(GenericEntity genericEntity) {
		GenericEntity r = get(genericEntity.getPrimaryKey());
		if(r != null) {
			entityManager.remove(r);
		}
	}
	
	/**
	 * Find by query.
	 *
	 * @param queryString the query string
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List findByQuery(String queryString) {
		Query query = entityManager.createQuery(queryString);
		return query.getResultList();
	}
	
	/**
	 * Count by query.
	 *
	 * @param queryString the query string
	 * @return the long
	 */
	@Transactional(readOnly = true)
	public Long countByQuery(String queryString) {
		Query query = entityManager.createQuery(queryString);
		return (Long) query.getSingleResult();
	}
	
	/**
	 * Execute query.
	 *
	 * @param queryString the query string
	 */
	@Transactional
	public void executeQuery(String queryString) {
		Query query = entityManager.createQuery(queryString);
		query.executeUpdate();
	}
}
