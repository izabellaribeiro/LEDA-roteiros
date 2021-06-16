package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex == 1 || array.length == 0 || rightIndex < leftIndex) {
			return;
		}

		for (int i = 0; i < array.length; i++) {
			int indiceMenor = i;

			for (int j = i + 1; j < array.length; j++) {
				if (array[j].compareTo(array[indiceMenor]) == -1) {
					indiceMenor = j;
				}
			}
			Util.swap(array, i, indiceMenor);
		}
	}
}
