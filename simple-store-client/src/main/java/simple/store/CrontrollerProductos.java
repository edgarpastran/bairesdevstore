package simple.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

import simple.store.model.entity.Categoria;
import simple.store.model.entity.Producto;
import simple.store.model.entity.list.CategoriaList;
import simple.store.model.entity.list.MedidaList;
import simple.store.model.entity.list.ProductoList;


// TODO: Auto-generated Javadoc
/**
 * The Class CrontrollerProductos.
 */
@Controller
@RequestMapping(value = "/productos")
public class CrontrollerProductos {

	private String serverURL = "http://localhost:8080/simple-store-server/store";
	
	/** The logger. */
	private static Logger logger = Logger.getLogger("controllerCategorias");
	
	/** The rest template. */
	private RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Index.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(@ModelAttribute("model") ModelMap model) {
		this.logger.debug("solicitando al servidor todos los productos");
		
		// Preparamos los tipos de datos a trabajar
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_XML);
		
		// Preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(mediaTypes);
		HttpEntity<Categoria> entity = new HttpEntity<Categoria>(headers);
		
		// Enviamos las solicitudes via GET
		try {
			// Productos
			ResponseEntity<ProductoList> result1 = restTemplate.exchange(
					this.serverURL+"/productos", 
					HttpMethod.GET, entity, ProductoList.class);				
			model.addAttribute("productoList", result1.getBody().getData());
			// Categorias
			ResponseEntity<CategoriaList> result2 = restTemplate.exchange(
					this.serverURL+"/categorias", 
					HttpMethod.GET, entity, CategoriaList.class);				
			model.addAttribute("categoriaList", result2.getBody().getData());
			// Medidas	
			ResponseEntity<MedidaList> result3 = restTemplate.exchange(
					this.serverURL+"/medidas", 
					HttpMethod.GET, entity, MedidaList.class);				
			model.addAttribute("medidaList", result3.getBody().getData());
		}
		catch(Exception e) {
			this.logger.error(e);
		}
		return "productos";
	}
	
	/**
	 * Incluir.
	 *
	 * @param producto the producto
	 * @return the string
	 */
	@RequestMapping(value = "/incluir", method = RequestMethod.POST)
	@ResponseBody
	public String incluir(@ModelAttribute("producto") Producto producto) {
		if (producto != null &&  
			producto.getNombre() != null &&
			!producto.getNombre().isEmpty()) {
			
			this.logger.debug("solicitando al servidor que incluya un nuevo producto");
			
			// Preparamos los tipos de datos a trabajar
			List<MediaType> mediaTypes = new ArrayList<MediaType>();
			mediaTypes.add(MediaType.APPLICATION_XML);
			
			// Preparamos el header
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(mediaTypes);			
			// Ponemos el nuevo objeto en el header 
			HttpEntity<Producto> entity = new HttpEntity<Producto>(producto, headers);
			
			// Enviamos la solicitud via POST
			try {
				ResponseEntity<Producto> result = restTemplate.exchange(
						this.serverURL+"/productos/incluir", 
						HttpMethod.POST, entity, Producto.class);				
				Producto nuevo = result.getBody();
				if (nuevo != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					try {
						return objectMapper.writeValueAsString(nuevo);
					} 
					catch (Exception e) {
						return "error:"+e.getMessage();
					}
				}
				else {
					return "error:No se puedo incluir el nuevo item";
				}
			}
			catch(Exception e) {
				this.logger.error(e);
				e.printStackTrace();
				return "error:"+e.getMessage();
			}
		}
		return "error:Debe indicar los valores requeridos";
	}
	
	/**
	 * Modificar.
	 *
	 * @param producto the producto
	 * @param idString the id string
	 * @return the string
	 */
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	@ResponseBody
	public String modificar(@ModelAttribute("producto") Producto producto, @RequestParam(value="idString",  required=true) String idString) {
		if (producto != null &&  
			idString != null) {
			producto.setId(new Integer(idString));
			
			this.logger.debug("solicitando al servidor que modifique el producto con id = "+idString);
				
			// Preparamos los tipos de datos a trabajar
			List<MediaType> mediaTypes = new ArrayList<MediaType>();
			mediaTypes.add(MediaType.APPLICATION_XML);
			
			// Preparamos el header
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(mediaTypes);			
			// Ponemos el objeto en el header 
			HttpEntity<Producto> entity = new HttpEntity<Producto>(producto, headers);
				
			// Enviamos la solicitud via PUT
			try {
				ResponseEntity<Producto> result = restTemplate.exchange(
						this.serverURL+"/productos/modificar", 
						HttpMethod.PUT, entity, Producto.class);				
				Producto nuevo = result.getBody();
				if (nuevo != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					try {
						return objectMapper.writeValueAsString(nuevo);
					} 
					catch (Exception e) {
						return "error:"+e.getMessage();
					}
				}
				else {
					return "error:No se pudo modificar el item";
				}
			}
			catch(Exception e) {
				this.logger.error(e);
				e.printStackTrace();
				return "error:"+e.getMessage();
			}
		}
		return "error:Debe indicar los valores requeridos";
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String eliminar(@PathVariable Integer id) {
		this.logger.debug("solicitando al servidor que elimine el producto con id = "+id);
		
		// Preparamos los tipos de datos a trabajar
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_XML);
		
		// Preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(mediaTypes);			
		// Ponemos el objeto en el header 
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		// Enviamos la solicitud via DELETE
		ResponseEntity<String> result = restTemplate.exchange(
								this.serverURL+"/productos/eliminar/"+id, 
								HttpMethod.DELETE, entity, String.class);
		return result.getBody();
	}
		
}
