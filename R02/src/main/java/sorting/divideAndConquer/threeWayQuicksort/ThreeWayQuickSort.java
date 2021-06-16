package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot.
	 *
	 * Considerando um array com muitos elementos repetidos, a estrategia do quicksort
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot.
	 *
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente.
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex <= leftIndex)
			return;

		i = 0; j = 0;

		partition(array, leftIndex, rightIndex);

		sort(array, leftIndex, j);
		sort(array, i, rightIndex);
	}

	int i, j;

	public void partition(T a[], int l, int r) {
		i = l - 1; j = r;
		int p = l - 1, q = r;
		T v = a[r];

		while (true)
		{

			while (a[++i].compareTo(v) < 0)
				;

			while (v.compareTo(a[--j]) < 0)
				if (j == l)
					break;

			if (i >= j)
				break;

			Util.swap(a, i, j);

			if (a[i] == v) {
				p++;
				Util.swap(a, i, p);
			}

			if (a[j] == v) {
				q--;
				Util.swap(a, q, j);
			}
		}

		Util.swap(a, i, r);

		j = i - 1;
		for (int k = l; k < p; k++, j--)
		{
			Util.swap(a, k, j);
		}

		i = i + 1;
		for (int k = r - 1; k > q; k--, i++)
		{
			Util.swap(a, i, k);
		}
	}
}

