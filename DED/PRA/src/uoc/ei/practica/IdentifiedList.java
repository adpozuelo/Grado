package uoc.ei.practica;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.ListaEncadenada;

public class IdentifiedList<E> extends ListaEncadenada<E> {

		
	/** 
	 * Busca un objeto dentro de una lista encadena y lanza una excepción
	 * en el caso que no se encuentre el objeto
	 * 
	 */
	protected  E getIdentifiedObject(String identifier, String exceptionMessage) throws EIException {
		E result=null;
		if (this.numElems()>0) {
			Iterador<? extends IdentifiedObject> iterator=(Iterador<? extends IdentifiedObject>) super.elementos();
			while (iterator.haySiguiente() && result==null) {
				IdentifiedObject current=iterator.siguiente();
				if (current.getIdentifier().equals(identifier))
					result=(E)current;
			}
		}
		if (result==null && exceptionMessage!=null)
		throw new EIException(exceptionMessage);

		return result;
	}
	
	
	/** 
	 * Busca un objeto dentro de una lista encadenada 
	 * 
	 */
	protected E getIdentifiedObject(String identifier) {
		E result=null;
		try {
			result=getIdentifiedObject(identifier, null);
		} catch (EIException e) {
		}
		return result;
	}

 	
	public Iterador<E> elements(String exceptionMessage) throws EIException {
		if (this.estaVacio()) throw new EIException(exceptionMessage);
		return this.elementos();
	}

}
