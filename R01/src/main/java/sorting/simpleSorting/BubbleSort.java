package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex == 1 || array.length == 0 || rightIndex < leftIndex) {
			return;
		}

		for (int i = 0; i < array.length; i++) {
			boolean ehOrdenado = false;
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j].compareTo(array[j+1]) == 1){
					Util.swap(array, j, j +1);
					ehOrdenado = true;
				}
			}
			if (!ehOrdenado) {
				return;
			}
		}
	}
}
