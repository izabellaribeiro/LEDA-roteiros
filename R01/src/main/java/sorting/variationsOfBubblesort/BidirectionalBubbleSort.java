package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex == 1 || array.length == 0 || rightIndex < leftIndex) {
			return;
		}

		boolean swapped = true;
		int start = 0, end = array.length - 1, swap = 0;

		while(swap == 0 && start < end) {
			for (int i = start; i < end ; i++) {
				if (array[i].compareTo(array[i + 1]) == 1) {
					Util.swap(array, i, i + 1);
					swap = 0;
				}
			}

			end --;

			for (int j = end; j > start; j--) {
				if (array[j].compareTo(array[j - 1]) == - 1) {
					Util.swap(array, j, j - 1);
					swap = 0;
				}
			}
			start++;
		}

	}

}
