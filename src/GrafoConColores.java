
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: GRAFOCONCOLORES
//PRÁCTICA 2

import java.util.ArrayList;
import java.util.*;

public class GrafoConColores extends Grafo {

	// ATRIBUTOS
	private Map<Integer, Integer> tablaColores;

	// CONSTRUCTOR
	public GrafoConColores() {
		super();
		tablaColores = new java.util.Hashtable<Integer, Integer>();
	}

	// METODOS
	public void asignarColor(int vertice, int color) {
		if (this.colorValido(vertice, color)) {
			this.tablaColores.put(vertice, color);
		}
	}
	
	public void elimnarColorDeVertice(int v1, int color) {
		tablaColores.remove(super.listaVertices().get(v1), color);
	}
	
	public int ColorVertice(int v1) {
		return tablaColores.get(v1);
	}
	
	public void borrarColores() {
		tablaColores.clear();
	}
	
	public ArrayList<Integer> verticesConColor() {
		return (new ArrayList<Integer>(this.tablaColores.keySet()));
	}

	public boolean colorValido(int vertice, int color) {
		
		if (this.listaAdyacentes(vertice).contains(color)) {
				return false;
		}
		
		return true;
	}

	public boolean colorear(int numColores) {
		List<Integer> listaVertices;
		// lista auxiliar en la que colocaré todos los vértices
		/*
		 * Para poder aplicar el algoritmo de coloración de un grafo necesito
		 * tener los vértices almacenados en orden. En primer lugar colocaré los
		 * vértices que tienen ya un color asignado (este color no podrá
		 * modificarse). A continuación colocaré en la lista el resto de
		 * vértices, a los que el algoritmo de coloración irá asignando
		 * diferentes colores hasta dar con una combinación correcta.
		 */
		List<Integer> listaVerticesColoreados = this.verticesConColor();
		List<Integer> listaVerticesNoColoreados = super.listaVertices(); // todos
		listaVerticesNoColoreados.removeAll(listaVerticesColoreados); 
		// quito los coloreados

		// Compruebo que los colores que ya están asignados son correctos
		for (int v : listaVerticesColoreados) {
			if (!this.colorValido(v, this.ColorVertice(v)))
				return false;
		}
		// vuelco los vértices en la nueva lista, en el orden correcto
		listaVertices = new ArrayList<Integer>();
		listaVertices.addAll(listaVerticesColoreados);
		listaVertices.addAll(listaVerticesNoColoreados);
		int k = listaVerticesColoreados.size();
		boolean b = this.coloreoConRetroceso(listaVertices, k, numColores);
		if (b == false) {
			// no se ha podido colorear el grafo
			// vuelvo a la situación inicial
			for (int i = 0; i < listaVerticesNoColoreados.size(); i++) {
				this.tablaColores.remove(listaVerticesNoColoreados.get(i));
			}
		}
		return b;
	}

	private boolean aceptable(List<Integer> listaVertices, int color, int posicion) {
		/*
		 * devuelve true si al vértice que ocupa la posición k en listaVertices
		 * puedo asignarle el color k de modo que no haya ningún vértice en las
		 * posiciones anteriores que sea adyacente y que tenga el mismo color
		 * asignado.
		 */
		boolean acept = true;
		for (int i = 0; i < posicion && acept; i++) {
			if (super.sonAdyacentes(listaVertices.get(i), listaVertices.get(posicion))
					&& this.ColorVertice(listaVertices.get(i)) == color)
				acept = false;
		}
		return acept;
	}

	private boolean coloreoConRetroceso(List<Integer> listaVertices, int k, int numColores) {
		/*
		 * Supongo que a los vértices situados en las posiciones 0..k-1 de
		 * listaVertices ya les he asignado color. Busco un color para el
		 * vértice en la posición k que sea compatible con los anteriores.
		 */
		if (k == listaVertices.size())
			return true;
		else {
			for (int c = 1; c <= numColores; c++) {
				if (this.aceptable(listaVertices, c, k)) {
					this.tablaColores.put(listaVertices.get(k), c);
					boolean b = coloreoConRetroceso(listaVertices, k + 1, numColores);
					if (b)
						return b;
				}
			}
		}
		// he recorrido todas las combinaciones y ninguna es válida, devuelve falso.
		return false;
	}
}
