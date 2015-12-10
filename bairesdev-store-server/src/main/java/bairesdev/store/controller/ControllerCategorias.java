package bairesdev.store.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.SimpleFormController;

import bairesdev.store.model.entity.Categoria;
import bairesdev.store.model.entity.list.CategoriaList;
import bairesdev.store.model.service.IServiceCategorias;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerCategorias.
 */
@Controller
@RequestMapping(value = "/categorias")
public class ControllerCategorias {

	/** The logger. */
	protected static Logger logger = Logger.getLogger("controllerCategorias");
	
	/** The service categorias. */
	@Autowired
	private IServiceCategorias serviceCategorias;
	
	/**
	 * Gets the categorias.
	 *
	 * @return the categorias
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public CategoriaList getCategorias() {
		this.logger.debug("El servidor ha recibido una peticion para buscar todas las categorias");
		CategoriaList result = new CategoriaList();
		result.setData(this.serviceCategorias.getCategorias());
		return result;
	}

	/**
	 * Gets the categoria.
	 *
	 * @param id the id
	 * @return the categoria
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Categoria getCategoria(@PathVariable("id") Integer id) {
		this.logger.debug("El servidor ha recibido una peticion para buscar la categoria con id = "+id);
		return this.serviceCategorias.getCategoriaPorId(id);
	}

	/**
	 * Incluir.
	 *
	 * @param categoria the categoria
	 * @return the categoria
	 */
	@RequestMapping(value = "/incluir", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Categoria incluir(@RequestBody Categoria categoria) {
		this.logger.debug("El servidor ha recibido una peticion para incluir una categoria");
		if (categoria != null &&  
			categoria.getNombre() != null &&
			!categoria.getNombre().isEmpty()) {			
			categoria.setCategoria(this.serviceCategorias.getCategoriaPorId(new Integer(categoria.getIdCategoriaPadre())));
			return this.serviceCategorias.incluirCategoria(categoria);
		}
		return null;
	}
	
	/**
	 * Modificar.
	 *
	 * @param categoria the categoria
	 * @return the categoria
	 */
	@RequestMapping(value = "/modificar", method = RequestMethod.PUT, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Categoria modificar(@RequestBody Categoria categoria) {		
		if (categoria != null && categoria.getId() != null) { 			
			this.logger.debug("El servidor ha recibido una peticion para modificar la categoria con id = "+categoria.getId());
			return this.serviceCategorias.modificarCategoria(categoria);
		}
		return null;
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public String eliminar(@PathVariable Integer id) {
		this.logger.debug("El servidor ha recibido una peticion para eliminar la categoria con id = "+id);
		return this.serviceCategorias.eliminarCategoria(id);
	}
	
}