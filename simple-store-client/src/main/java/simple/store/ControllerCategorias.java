package simple.store;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.SimpleFormController;

import simple.store.model.entity.Categoria;
import simple.store.model.entity.list.CategoriaList;


// TODO: Auto-generated Javadoc
/**
 * The Class ControllerCategorias.
 */
@Controller
@RequestMapping(value = "/categorias")
public class ControllerCategorias {

	private String serverURL = "http://localhost:8080/simple-store-server/store/categorias";
	
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
		this.logger.debug("solicitando al servidor todas las categorias");
		
		// Preparamos los tipos de datos a trabajar
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_XML);
		
		// Preparamos el header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(mediaTypes);
		HttpEntity<Categoria> entity = new HttpEntity<Categoria>(headers);
		
		// Enviamos la solicitud via GET
		try {
			ResponseEntity<CategoriaList> result = restTemplate.exchange(
					this.serverURL, 
					HttpMethod.GET, entity, CategoriaList.class);				
			model.addAttribute("categoriaList", result.getBody().getData());
		}
		catch(Exception e) {
			this.logger.error(e);
		}
		return "categorias";
	}
	
	/**
	 * Incluir.
	 *
	 * @param categoria the categoria
	 * @return the string
	 */
	@RequestMapping(value = "/incluir", method = RequestMethod.POST)
	@ResponseBody
	public String incluir(@ModelAttribute("categoria") Categoria categoria) {
		if (categoria != null &&  
			categoria.getNombre() != null &&
			!categoria.getNombre().isEmpty()) {
			
			this.logger.debug("solicitando al servidor que incluya una nueva categoria");
			
			// Preparamos los tipos de datos a trabajar
			List<MediaType> mediaTypes = new ArrayList<MediaType>();
			mediaTypes.add(MediaType.APPLICATION_XML);
			
			// Preparamos el header
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(mediaTypes);			
			// Ponemos el nuevo objeto en el header 
			HttpEntity<Categoria> entity = new HttpEntity<Categoria>(categoria, headers);
			
			// Enviamos la solicitud via POST
			try {
				ResponseEntity<Categoria> result = restTemplate.exchange(
						this.serverURL+"/incluir", 
						HttpMethod.POST, entity, Categoria.class);				
				Categoria nuevo = result.getBody();
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
	 * @param categoria the categoria
	 * @param idString the id string
	 * @return the string
	 */
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	@ResponseBody
	public String modificar(@ModelAttribute("categoria") Categoria categoria, @RequestParam(value="idString",  required=true) String idString) {
		if (categoria != null &&  
			idString != null) {
			categoria.setId(new Integer(idString));
			
			this.logger.debug("solicitando al servidor que modifique la categoria con id = "+idString);
				
			// Preparamos los tipos de datos a trabajar
			List<MediaType> mediaTypes = new ArrayList<MediaType>();
			mediaTypes.add(MediaType.APPLICATION_XML);
			
			// Preparamos el header
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(mediaTypes);			
			// Ponemos el objeto en el header 
			HttpEntity<Categoria> entity = new HttpEntity<Categoria>(categoria, headers);
				
			// Enviamos la solicitud via PUT
			try {
				ResponseEntity<Categoria> result = restTemplate.exchange(
						this.serverURL+"/modificar", 
						HttpMethod.PUT, entity, Categoria.class);				
				Categoria nuevo = result.getBody();
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
		this.logger.debug("solicitando al servidor que elimine la categoria con id = "+id);
		
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
								this.serverURL+"/eliminar/"+id, 
								HttpMethod.DELETE, entity, String.class);
		return result.getBody();
	}
	
}
