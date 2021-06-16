package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {

		Integer out = null;
		if (array != null) {
			for (Integer element : array) {
				this.insert(element);
			}
			int index = 0;
			while (index <= array.length - 1) {

				if (this.rootElement() == numero) {
					return this.rootElement();
				} else if (this.rootElement() < numero) {
					if (out == null) {
						out = this.rootElement();
					} else {
						if (this.rootElement() > out) {
							out = this.rootElement();
						}
					}

				}
				this.extractRootElement();
				index++;
			}
		}
		return out;

	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer out = null;
		if (array != null) {
			for (Integer element : array) {
				this.insert(element);
			}
			int index = 0;
			while (index <= array.length - 1) {

				if (this.rootElement() == numero) {
					return this.rootElement();
				} else if (this.rootElement() > numero) {
					if (out == null) {
						out = this.rootElement();
					} else {
						if (this.rootElement() < out) {
							out = this.rootElement();
						}
					}
				}
				this.extractRootElement();
				index++;
			}
		}
		return out;
	}

}