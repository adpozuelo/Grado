/**
 * Language class.
 * Embeddable class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Language's name.
	 */
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Language) {
			return this.language.equals(((Language) obj).getLanguage());
		} else {
			return false;
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 */
	public Language(String name) {
		super();
		this.language = name;
	}

	public Language() {
	}

}
