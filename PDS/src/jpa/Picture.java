/**
 * Picture class.
 * Embeddable class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.xml.bind.DatatypeConverter;

@Embeddable
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Picture data bytes
	 */
	private byte[] pictureData;

	@Basic(fetch = FetchType.LAZY)
	public byte[] getPictureData() {
		return pictureData;
	}

	public void setPictureData(byte[] pictureData) {
		this.pictureData = pictureData;
	}

	/**
	 * Picture data type
	 */
	private String pictureDataType;

	public String getPictureDataType() {
		return pictureDataType;
	}

	public void setPictureDataType(String pictureDataType) {
		this.pictureDataType = pictureDataType;
	}

	public String toBase64() {

		return "data:" + getPictureDataType() + ";base64,"
				+ DatatypeConverter.printBase64Binary(getPictureData());
	}

	public Picture() {
	}
}
