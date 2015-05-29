package uoc.ei.practica.adp_tads;

import java.util.Arrays;

import uoc.ei.practica.Bicycle;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorVectorImpl;

/**
 * @author Antonio Diaz Pozuelo - adpozuelo@uoc.edu
 * @version 1.0
 */
public class VectorADPAcotadoOrdenadoImpl<E> implements
		VectorADPAcotadoOrdenado<E> {

	protected E[] elementos; // vector de elementos
	protected int num_elementos; // numero de elementos del vector
	
	public VectorADPAcotadoOrdenadoImpl(int size) {
		this.num_elementos = 0; // inicializo el numero de elementos a cero
		elementos = (E[]) new Object[size]; // instancio el array
	}

	@Override
	public boolean estaLleno() {
		// si el numero de elementos es igual a la longitud del array retorno true, en caso contrario false
		return (this.num_elementos == this.elementos.length);
	}

	@Override
	public Iterador<E> elementos() {
		// instancio el iterador del TAD de a la asignatura
		Iterador<E> it = new IteradorVectorImpl<E>(this.elementos, this.num_elementos, 0);
		return it; // lo retorno
	}

	@Override
	public boolean estaVacio() {
		// si el numero de elementos es igual a cero retorno true, el caso contrario false
		return (this.num_elementos == 0);
	}

	@Override
	public int numElems() {
		// devuelvo el numero de elementos
		return this.num_elementos;
	}

	@Override
	public void insertar(E elemento) {
		if (this.num_elementos == 0) { // si no hay elementos en el vector
			this.elementos[this.num_elementos] = elemento; // introduzco el elemento en el primer indice
			this.num_elementos++;	// sumo uno al numero de elementos
		} else { // en caso contrario, existen elementos en el vector
			E[] elementosTmp = (E[]) new Object[this.longitud()]; // creo un vector temporal para hacer la ordenacion por insercion
			// realizo una busqueda binaria sobre el vector, que ya esta ordenado
			int i =	Arrays.binarySearch(this.elementos, 0, this.num_elementos, elemento);
			i = -(i+1); // dado que la busqueda me devuelve  (-(insertion point) - 1) calculo donde deberia ir insertado el elemento
			for (int j = 0 ; j < i ; j++) { // desde el comienzo del vector origen hasta el punto donde deberia ir el elemento (no inclusive)
				elementosTmp[j] = this.elementos[j]; // asigno los elementos del vector origen al vector destino
			}
			for (int j = i; j < this.num_elementos; j++) {  // desde el punto donde deberia ir el elemento hasta el ultimo elemento del vector origen
				elementosTmp[j+1] = this.elementos[j]; // asigno los elementos del vector origen al vector destino pero una posicion (indice+1) mas
			}
			elementosTmp[i] = elemento; // inserto el elemento en su posicion ordenada
			this.num_elementos++; // aumento en uno el numero de elementos
			this.elementos = elementosTmp; // asigno al puntero del vector origen el vector destino
			elementosTmp = null; // libero el puntero del vector destino para liberar memoria (recolector de basura)
		}
	}

	@Override
	public int longitud() {
		// devuelvo la longitud del array
		return this.elementos.length;
	}

	@Override
	public E obtener(E elemento) {
		// realizo una busqueda binaria utilizando la JDK
		int i =	Arrays.binarySearch(this.elementos, 0, this.num_elementos, elemento);
		if ( i >= 0) // si el elemento existe
			return this.elementos[i]; // lo retorno
		else // en caso contrario
			return null; // devuelvo null
	}
}
