package bairesdev.store.model.entity.list;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import bairesdev.store.model.entity.Producto;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductoList.
 */
@XmlRootElement(name="productos")
public class ProductoList {

	/** The data. */
	private List<Producto> data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<Producto> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(List<Producto> data) {
		this.data = data;
	}
	
}
