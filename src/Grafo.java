
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: GRAFO
//PRÁCTICA 2

import java.util.*;

public class Grafo {

	// ATRIBUTOS
	private Map<Integer, List<Integer>> tabla;

	// CONSTRUCTOR
	public Grafo() {
		this.tabla = new Hashtable<Integer, List<Integer>>();
	}

	// METODOS
	public void anadirVertice(int v) {
		tabla.put(v, new ArrayList<Integer>());
	}

	public void anadirArista(int v1, int v2) {
		tabla.get(v1).add(v2);
		tabla.get(v2).add(v1);
	}

	public int numVertices() {
		return tabla.size();
	}

	public boolean sonAdyacentes(int v1, int v2) {
		if (tabla.get(v1).contains(v2)) {
			return true;
		}
		return false;
	}

	public boolean estaVertice(int v1) {
		if (tabla.containsKey(v1)) {
			return true;
		}
		return false;
	}

	public List<Integer> listaVertices() {
		return new ArrayList<Integer>(tabla.keySet());
	}

	public List<Integer> listaAdyacentes(int v1) {
		return new ArrayList<Integer>(tabla.get(v1));
	}

}
