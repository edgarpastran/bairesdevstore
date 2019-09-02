package simple.store.model.entity.list;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import simple.store.model.entity.Categoria;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaList.
 */
@XmlRootElement(name="categorias")
public class CategoriaList {

	/** The data. */
	private List<Categoria> data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<Categoria> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(List<Categoria> data) {
		this.data = data;
	}
}
