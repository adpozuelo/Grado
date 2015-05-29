package uoc.ei.practica.adp_tads;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorVectorImpl;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class VectorADPAcotadoImpl<E> implements VectorADPAcotado<E> {

	protected E[] elementos; // vector de elementos
	protected int num_elementos; // numero de elementos del vector

	/**
	 * Constructor
	 * 
	 * @param size
	 *            el tamaño del vector
	 */
	public VectorADPAcotadoImpl(int size) {
		this.num_elementos = 0; // inicializo el numero de elementos a cero
		elementos = (E[]) new Object[size]; // instancio el array
	}

	@Override
	public void insertar(E elemento, int indice) {
		this.elementos[indice] = elemento; // inserto el elemento en el indice indicado
		this.num_elementos++; // sumo uno al numero de elementos
	}

	@Override
	public E obtener(int indice) {
		return this.elementos[indice]; // devuelvo el objeto ubicado en el indice indicado
	}

	@Override
	public Iterador<E> elementos() {
		// instancio el iterador del TAD de la asignatura
		Iterador<E> it = new IteradorVectorImpl<E>(this.elementos, this.num_elementos, 0);
		return it; // lo retorno
	}

	@Override
	public boolean estaVacio() {
		// si el numero de elementos es igual a cero retorno true, en caso contrario false
		return (this.num_elementos == 0);
	}

	@Override
	public int numElems() {
		// retorno el numero de elementos
		return this.num_elementos;
	}

	@Override
	public boolean estaLleno() {
		// si el numero de elementos es igual a la longitud del array retorno true, en caso contrario false
		return (this.num_elementos == this.elementos.length);
	}

	@Override
	public int longitud() {
		// retorno la longitud del array
		return this.elementos.length;
	}

	@Override
	public boolean esta(int indice) {
		// si el elemento en el indice indicado es nulo
		if (this.elementos[indice] == null)
			return false; // retorno false
		else // en caso contrario
			return true; // retorno true
	}
}
