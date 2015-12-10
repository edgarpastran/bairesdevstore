package bairesdev.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bairesdev.store.model.entity.Categoria;
import bairesdev.store.model.entity.Producto;
import bairesdev.store.model.entity.list.CategoriaList;
import bairesdev.store.model.entity.list.MedidaList;
import bairesdev.store.model.entity.list.ProductoList;
import bairesdev.store.model.service.IServiceCategorias;
import bairesdev.store.model.service.IServiceProductos;

// TODO: Auto-generated Javadoc
/**
 * The Class CrontrollerProductos.
 */
@Controller
@RequestMapping(value = "/productos")
public class CrontrollerProductos {

	/** The logger. */
	protected static Logger logger = Logger.getLogger("controllerProductos");
	
	/** The service productos. */
	@Autowired
	private IServiceProductos serviceProductos;
	
	/**
	 * Gets the productos.
	 *
	 * @param model the model
	 * @return the productos
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public ProductoList getProductos(@ModelAttribute("model") ModelMap model) {
		this.logger.debug("El servidor ha recibido una peticion para buscar todos los productos");
		ProductoList result = new ProductoList();
		result.setData(this.serviceProductos.getProductos());		
		return result;
	}
	
	/**
	 * Gets the producto.
	 *
	 * @param id the id
	 * @return the producto
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Producto getProducto(@PathVariable("id") Integer id) {
		this.logger.debug("El servidor ha recibido una peticion para buscar el producto con id = "+id);
		return this.serviceProductos.getProductoPorId(id);
	}
	
	/**
	 * Incluir.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@RequestMapping(value = "/incluir", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Producto incluir(@RequestBody Producto producto) {
		this.logger.debug("El servidor ha recibido una peticion para incluir un producto");
		if (producto != null &&  
			producto.getNombre() != null &&
			!producto.getNombre().isEmpty()) {
			producto.setCategoria(this.serviceProductos.getCategoriaPorId(new Integer(producto.getIdCategoria())));
			return this.serviceProductos.incluirProducto(producto);			
		}
		return null;
	}
	
	/**
	 * Modificar.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@RequestMapping(value = "/modificar", method = RequestMethod.PUT, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public Producto modificar(@RequestBody Producto producto) {
		if (producto != null && producto.getId() != null) { 			
			this.logger.debug("El servidor ha recibido una peticion para modificar el producto con id = "+producto.getId());
			return this.serviceProductos.modificarProducto(producto);
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
		this.logger.debug("El servidor ha recibido una peticion para eliminar el producto con id = "+id);
		return this.serviceProductos.eliminarProducto(id);
	}
	
}
