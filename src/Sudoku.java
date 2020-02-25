
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: SUDOKU
//PRÁCTICA 2

import java.lang.Math;

public class Sudoku {

	// ATRIBUTOS
	private int[][] matrizInicial;
	private int[][] matriz;
	int tamano;

	// CONSTRUCTOR
	public Sudoku(int tamano, int[][] inicial) {
		this.matrizInicial = inicial;
		this.matriz = inicial;
		this.tamano = tamano;
	}

	// METODOS
	public int tamano() {
		return this.tamano;
	}

	public void anadirNumero(int num, int fila, int columna) {
		this.matriz[fila][columna] = num;
	}
	
	public void eliminarNumero(int fila, int columna) {
		this.matriz[fila][columna] = 0;
	}

	public int valorInicial(int fila, int columna) {
		return this.matrizInicial[fila][columna];
	}
	
	public int getNumero(int fila, int columna) {
		return this.matriz[fila][columna];
	}

	public void mostrarSudoku() {
		// Filas
		for (int i = 0; i < tamano; i++) {
			// Columnas
			for (int a = 0; a < tamano; a++) {
				System.out.print("|"+ this.matriz[i][a] + "|");
			}
			System.out.println();
		}
	}

	public void retomarEstadoInicial() {
		// Filas
		for (int i = 0; i < tamano; i++) {
			// Columnas
			for (int a = 0; a < tamano; a++) {
				// Damos a cada casilla el valor de la misma en la matriz inicial
				this.matriz[i][a] = this.valorInicial(i, a);
			}
		}
	}

	public boolean mismoSector(int f1, int c1, int f2, int c2) {
		// Definimos asi el sector (cuadrado) en el que se encunetra una casilla
		int tamanoSector = (int) Math.sqrt(this.tamano());
		if (f1 - (f1 % tamanoSector) == f2 - (f2 % tamanoSector)) {
			if (c1 - (c1 % tamanoSector) == c2 - (c2 % tamanoSector)) {
				return true;
			}
		}
		return false;
	}

	public boolean sePuedePoner(int valor, int fila, int columna) {
		// Se puede en la fila
		for (int i = 0; i < tamano; i++) {
			if (this.matriz[fila][i] == valor) {
				return false;
			}
		}

		// Se puede en la columna
		for (int i = 0; i < tamano; i++) {
			if (this.matriz[i][columna] == valor) {
				return false;
			}
		}

		// Se puede en el sector
		for (int i = 0; i < tamano; i++) {
			for (int a = 0; a < tamano; a++) {
				if ((mismoSector(fila, columna, i, a) == true) && (this.matriz[i][a] == valor)) {
					return false;
				}
			}
		}
		return true;
	}

}
