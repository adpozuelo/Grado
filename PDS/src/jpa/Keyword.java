/**
 * Keyword class.
 * Embeddable class.
 * @author adpozuelo@uoc.edu
 * @version 1.0
 */
package jpa;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Keyword implements Comparable<Keyword>, Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Keyword's name.
	 */
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Keyword) {
			return this.keyword.equals(((Keyword) obj).getKeyword());
		} else {
			return false;
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 */
	public Keyword(String keyword) {
		super();
		this.keyword = keyword;
	}

	public Keyword() {
	}

	@Override
	public int compareTo(Keyword o) {
		return this.keyword.compareTo(o.getKeyword());
	}

}
