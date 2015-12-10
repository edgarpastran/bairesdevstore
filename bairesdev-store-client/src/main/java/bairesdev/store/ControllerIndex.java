package bairesdev.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerIndex.
 */
@Controller
public class ControllerIndex {

	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	/**
	 * Contacto.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/contacto", method = RequestMethod.GET)
	public String contacto() {
		return "contacto";
	}
	
}
