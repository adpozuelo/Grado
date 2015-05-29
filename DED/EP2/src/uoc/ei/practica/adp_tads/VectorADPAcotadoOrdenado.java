package uoc.ei.practica.adp_tads;

import uoc.ei.tads.ContenedorAcotado;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public interface VectorADPAcotadoOrdenado<E> extends ContenedorAcotado<E> {
	
	/**
	 * inserta un elemento en el vector 
	 * el metodo de ordenacion se realiza por insercion
	 * @param elemento
	 *            elemento a insertar
	 */
	public void insertar(E elemento);
	
	/**
	 * devuelve la longitud del vector
	 * @return
	 * 		la longitud del vector
	 */
	public int longitud();
	
	/**
	 * comprueba si un elemento esta en el vector
	 * se realiza una busqueda binaria
	 * @param elemento
	 * 				el elemento a buscar
	 * @return
	 * 				la bicicleta si existe, null en caso contrario
	 */
	public E obtener(E elemento);
	
} 
