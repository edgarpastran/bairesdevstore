package simple.store.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.store.model.beans.Medida;
import simple.store.model.entity.Categoria;
import simple.store.model.entity.Producto;
import simple.store.model.entity.list.MedidaList;
import simple.store.model.entity.list.ProductoList;
import simple.store.model.service.IServiceProductos;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerMedidas.
 */
@Controller
@RequestMapping(value = "/medidas")
public class ControllerMedidas {

	/** The logger. */
	protected static Logger logger = Logger.getLogger("controllerProductos");
	
	/** The service productos. */
	@Autowired
	private IServiceProductos serviceProductos;
	
	/**
	 * Gets the medidas.
	 *
	 * @param model the model
	 * @return the medidas
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public MedidaList getMedidas(@ModelAttribute("model") ModelMap model) {
		this.logger.debug("El servidor ha recibido una peticion para buscar todas las medidas");				
		MedidaList result = new MedidaList();
		result.setData(this.serviceProductos.getMedidas());
		return result;
	}
	
	/**
	 * Gets the medida.
	 *
	 * @param id the id
	 * @return the medida
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Medida getMedida(@PathVariable("id") String id) {
		this.logger.debug("El servidor ha recibido una peticion para buscar la medida con id = "+id);
		return this.serviceProductos.getMedidaPorId(id);
	}
		
}
