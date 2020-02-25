
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: SUDOKUCONSOLUCION
//PRÁCTICA 2

import java.util.ArrayList;

public class SudokuConSolucion extends Sudoku {

	// ATRIBUTOS
	private GrafoConColores gc;

	// CONSTRUCTOR
	public SudokuConSolucion(int tamaño, int[][] inicial) {
		super(tamaño, inicial);
		this.gc = new GrafoConColores();
		this.construirGrafoInicial();
	}

	// METODOS
	private void construirGrafoInicial() {
		int numFilas = this.tamano();
		int numVertices = numFilas * numFilas;
		for (int v = 1; v <= numVertices; v++){
			this.gc.anadirVertice(v);
		}
		// gc es el nombre del atributo de Sudoku que contiene el grafo con colores
		// Añado aristas para todas las parejas de vértices que están en la misma fila
		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numFilas; j++) {
				for (int k = j + 1; k < numFilas; k++) {
					int v1 = numFilas * i + j + 1;
					int v2 = numFilas * i + k + 1;
					this.gc.anadirArista(v1, v2);
				}
			}
		}
		// Añado aristas para todas las parejas de vértices que están en la misma columna
		for (int j = 0; j < numFilas; j++) {
			for (int i = 0; i < numFilas; i++) {
				for (int k = i + 1; k < numFilas; k++) {
					int v1 = numFilas * i + j + 1;
					int v2 = numFilas * k + j + 1;
					this.gc.anadirArista(v1, v2);
				}
			}
		}
		// Añado aristas para todas las parejas de vértices que están en la misma región
		int n = (int) Math.sqrt(numFilas);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int i0 = i * n;
				int j0 = j * n;
				// (i0,j0) es la esquina superior izquierda de la región
				for (int i1 = i0; i1 < i0 + n; i1++) {
					for (int j1 = j0; j1 < j0 + n; j1++) {
						for (int i2 = i0; i2 < i0 + n; i2++) {
							for (int j2 = j0; j2 < j0 + n; j2++) {
								int v1 = numFilas * i1 + j1 + 1;
								int v2 = numFilas * i2 + j2 + 1;
								if ((v2 != v1) && !this.gc.sonAdyacentes(v1, v2))
									this.gc.anadirArista(v1, v2);
							}
						}
					}
				}
			}
		}
		// Por último añado los colores a los vértices correspondientes a los valores iniciales del sudoku
		for (int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numFilas; j++) {
				if (this.valorInicial(i, j) != 0)
					this.gc.asignarColor(i * numFilas + j + 1, this.valorInicial(i, j));
			}
		}
	}

	//herencia de añadir numero
	public void anadirNumero(int valor, int fila, int columna) {
		super.anadirNumero(valor, fila, columna);
		this.gc.asignarColor(fila * super.tamano() + columna + 1, valor);
	}

	//herencia de eliminar numero
	public void eliminarNumero(int fila, int columna) {
		int num = 0;
		super.eliminarNumero(fila, columna);
		this.gc.elimnarColorDeVertice(fila * super.tamano() + columna + 1, num);

	}

	public boolean puedeResolverse() {
		ArrayList<Integer> vertice = gc.verticesConColor();
		if (gc.colorear(super.tamano())) {
			for (int i = 0; i < tamano(); i++) {
				for (int a = 0; i < vertice.size(); a++) {
					if (vertice.get(a) != gc.ColorVertice(i)) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	public void resolver() {
		if (gc.colorear(this.tamano())) {
			for (int fila = 0; fila < this.tamano(); fila++) {
				for (int columna = 0; columna < this.tamano(); columna++) {
					anadirNumero(this.gc.ColorVertice(fila * super.tamano() + columna + 1), fila, columna);
				}
			}
		}

	}

	public boolean estaResuelto() {
		for (int i = 0; i < this.tamano(); i++) {
			for (int a = 0; a < this.tamano(); a++) {
				if (this.getNumero(i, a) == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
