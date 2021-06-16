package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (rightIndex == 1 || array.length == 0 || rightIndex < leftIndex) {
			return;
		}
		for (int i = 1; i < array.length; i++) {

			int j = i;

			while (j > 0 && array[j].compareTo(array[j - 1]) == -1) {
				T aux = array[j];
				array[j] = array[j - 1];
				array[j - 1] = aux;
				j--;
			}
		}
	}
}
