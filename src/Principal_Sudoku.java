
//AUTOR: LUCAS FERNÁNDEZ CEDRÓN
//CLASE: PRINCIPAL_SUDOKU
//PRÁCTICA 2

import java.util.Scanner;

public class Principal_Sudoku {

	// MENU PRINCIPAL EN EL QUE CONSTRUIMOS EL SUDOKU Y PROBAMOS EL PROGRAMA
	public static void main(String[] args) {

		System.out.println("JUEGO DE LOS SUDOKUS:");
		
		// menu de elección deli tipo de sudoku
		System.out.println("Elige un tamaño para el Sudoku:");
		System.out.println("1. 4x4. ");
		System.out.println("2. 9x9.");
		System.out.println("3. Salir del programa.");

		// creamos un scanner
		Scanner in = new Scanner(System.in);
		int opcion = in.nextInt();

		if (opcion == 1) {
			// construimos una matriz de 4x4
			int matriz[][] = new int[4][4];

			// damos valores a sus entradas
			matriz[0][0] = 3;
			matriz[0][1] = 4;
			matriz[0][2] = 1;
			matriz[1][1] = 2;
			matriz[2][2] = 2;
			matriz[3][1] = 1;
			matriz[3][2] = 4;
			matriz[3][3] = 3;

			// construimos un objeto SudokuConSolucion con la matriz anterior
			SudokuConSolucion sudoku = new SudokuConSolucion(4, matriz);

			// Menu del sudoku (mientras este no este resuelto)
			while (sudoku.estaResuelto() == false) {
				System.out.println("Introduzca una opcion:");
				System.out.println("1.Introducir numero.");
				System.out.println("2.Eliminar numero.");
				System.out.println("3.Resolver el sudoku.");

				int opcion2;
				opcion2 = in.nextInt();

				// Introducir un numero al sudoku
				if (opcion2 == 1) {
					sudoku.mostrarSudoku();

					System.out.println("Introduce el numero, fila y columna.");
					int numero = in.nextInt();
					int fila = in.nextInt();
					int columna = in.nextInt();
					sudoku.anadirNumero(numero, fila, columna);
				}

				// Eliminar un numero del sudoku
				if (opcion2 == 2) {
					sudoku.mostrarSudoku();

					System.out.println("Introduce la fila y columna del numero a eliminar.");
					int fila = in.nextInt();
					int columna = in.nextInt();

					sudoku.eliminarNumero(fila, columna);
				}

				// Programa resuelve el sudoku
				if (opcion2 == 3) {
					sudoku.resolver();
					sudoku.mostrarSudoku();
				}

			}

		}

		if (opcion == 2) {
			// construimos una matriz de 9x9
			int matriz[][] = new int[9][9];

			// damos valores a sus entradas
			matriz[0][3] = 1;
			matriz[0][5] = 5;
			matriz[0][7] = 6;
			matriz[0][8] = 8;
			matriz[1][6] = 7;
			matriz[1][8] = 1;
			matriz[2][0] = 9;
			matriz[2][2] = 1;
			matriz[2][7] = 3;
			matriz[3][2] = 7;
			matriz[3][4] = 2;
			matriz[3][5] = 6;
			matriz[4][0] = 5;
			matriz[4][8] = 3;
			matriz[5][3] = 8;
			matriz[5][4] = 7;
			matriz[5][6] = 4;
			matriz[6][1] = 3;
			matriz[6][6] = 8;
			matriz[6][8] = 5;
			matriz[7][0] = 1;
			matriz[7][2] = 5;
			matriz[8][0] = 7;
			matriz[8][1] = 9;
			matriz[8][3] = 4;
			matriz[8][5] = 1;

			// construimos un objeto SudokuConSolucion con la matriz anterior
			SudokuConSolucion sudoku = new SudokuConSolucion(9, matriz);

			// Menu del sudoku (mientras este no este resuelto)
			while (sudoku.estaResuelto() == false) {
				System.out.println("Introduzca una opcion:");
				System.out.println("1.Introducir numero.");
				System.out.println("2.Eliminar numero.");
				System.out.println("3.Resolver el sudoku.");

				int opcion2;
				opcion2 = in.nextInt();

				// Introducir un numero al sudoku
				if (opcion2 == 1) {
					sudoku.mostrarSudoku();

					System.out.println("Introduce el numero, fila y columna.");
					int numero = in.nextInt();
					int fila = in.nextInt();
					int columna = in.nextInt();
					sudoku.anadirNumero(numero, fila, columna);
				}

				// Eliminar un numero del sudoku
				if (opcion2 == 2) {
					sudoku.mostrarSudoku();

					System.out.println("Introduce la fila y columna del numero a eliminar.");
					int fila = in.nextInt();
					int columna = in.nextInt();

					sudoku.eliminarNumero(fila, columna);
				}

				// Programa resuelve el sudoku
				if (opcion2 == 3) {
					sudoku.resolver();
					sudoku.mostrarSudoku();
				}

			}

		}

		//salimos del programa
		if (opcion == 3) {
			System.out.println("Has seleccionado salir del programa.");
		}
		System.out.println("Cerrando el programa...");
	}
}
