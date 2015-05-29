package uoc.ei.practica.adp_tads;

import uoc.ei.tads.ContenedorAcotado;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public interface VectorADPAcotado<E> extends ContenedorAcotado<E> {

	/**
	 * inserta un elemento en el vector en el indice indicado
	 * 
	 * @param elemento
	 *            elemento a insertar
	 * @param indice
	 *            indice del vector donde insertar el elemento
	 */
	public void insertar(E elemento, int indice);

	/**
	 * devuelve el elemento ubicado en el indice indicado
	 * 
	 * @param indice
	 *            indice del vector de donde obtener el elemento.
	 * @return el elemento en el indice del vector
	 */
	public E obtener(int indice);
	
	/**
	 * devuelve la longitud del vector
	 * @return
	 * 		la longitud del vector
	 */
	public int longitud();
	
	/**
	 * comprueba si un elemento esta en el vector
	 * @param indice
	 * 				el indice del elemento a buscar
	 * @return
	 * 				true si el elemento existe en el vector, false en caso contrario
	 */
	public boolean esta(int indice);
	
}
