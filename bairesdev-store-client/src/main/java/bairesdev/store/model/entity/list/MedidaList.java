package bairesdev.store.model.entity.list;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import bairesdev.store.model.beans.Medida;

// TODO: Auto-generated Javadoc
/**
 * The Class MedidaList.
 */
@XmlRootElement(name="medidas")
public class MedidaList {

	/** The data. */
	private List<Medida> data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<Medida> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(List<Medida> data) {
		this.data = data;
	}
	
}
