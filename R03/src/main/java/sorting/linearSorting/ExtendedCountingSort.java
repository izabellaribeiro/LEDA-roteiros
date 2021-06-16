package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex > 0) {

			int maior = array[leftIndex];
			int menor = array[leftIndex];

			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > maior) {
					maior = array[i];
				}
				if (array[i] < menor) {
					menor = array[i];
				}
			}

			Integer[] novoArr = new Integer[maior - menor + 1];

			for (int i = 0; i < novoArr.length; i++) {
				novoArr[i] = 0;
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				novoArr[array[i] - menor]++;
			}

			int i = leftIndex;
			int j = leftIndex;

			while (i < novoArr.length) {
				while (novoArr[i] != 0) {
					array[j] = i + menor;
					j++;
					novoArr[i]--;
				}
				i++;
			}
		}
	}

}