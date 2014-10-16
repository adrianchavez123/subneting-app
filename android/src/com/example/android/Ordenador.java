package com.example.android;

public class Ordenador {

	public void ordenarQuicksort(long[] array) {

		array = quicksort1(array);
		
	}

	public long[] quicksort1(long numeros[]) {

		return quicksort2(numeros, 0, numeros.length - 1);
	}

	public long[] quicksort2(long[] numeros, int izq, int der) {

		if (izq >= der)
			return numeros;
		int i = izq, d = der;
		if (izq != der) {

			int pivote;
			long aux;
			pivote = izq;
			while (izq != der) {
				while (numeros[der] <= numeros[pivote] && izq < der)
					der--;
				while (numeros[izq] > numeros[pivote] && izq < der)
					izq++;

				if (der != izq) {

					aux = numeros[der];
					numeros[der] = numeros[izq];
					numeros[izq] = aux;
				}
			}

			if (izq == der) {

				quicksort2(numeros, i, izq - 1);
				quicksort2(numeros, izq + 1, d);
			}
		} else

			return numeros;

		return numeros;

	}

}
