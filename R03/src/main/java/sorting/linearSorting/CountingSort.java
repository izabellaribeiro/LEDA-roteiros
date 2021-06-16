package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && array != null && leftIndex >= 0 && rightIndex < array.length
				&& rightIndex > 0) {
			int maior = array[leftIndex];

			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > maior) {
					maior = array[i];
				}
			}

			Integer[] novoArr = new Integer[maior + 1];

			for (int i = 0; i < novoArr.length; i++) {
				novoArr[i] = 0;
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				novoArr[array[i]]++;
			}

			for (int i = 1; i < novoArr.length; i++) {
				novoArr[i] += novoArr[i - 1];
			}

			Integer[] arrayOrdenado = new Integer[array.length];

			for (int i = rightIndex; i >= leftIndex; i--) {
				arrayOrdenado[novoArr[array[i]] - 1] = array[i];
				novoArr[array[i]]--;
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = arrayOrdenado[i];
			}
		}
	}
}