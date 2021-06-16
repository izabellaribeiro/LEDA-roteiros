package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (this.head.isNIL());
	}

	@Override
	public int size() {
		int size = 0;

		SingleLinkedListNode<T> node = this.head;

		while (!node.isNIL()) {
			size++;
			node = node.getNext();
		}

		return size;
	}

	@Override
	public T search(T element) {
		T out = null;
		if (element != null) {
			SingleLinkedListNode<T> node = this.head;

			while (!node.isNIL()) {
				if (node.getData().equals((element))) {
					out = node.getData();
				}

				node = node.getNext();
			}
		}

		return out;
	}

	@Override
	public void insert(T element) {

		if (element != null) {
			if (this.isEmpty()) {
				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<T>());
			} else {
				SingleLinkedListNode<T> node = this.head;
				while (!node.isNIL()) {
					node = node.getNext();
				}
				node.setData(element);
				node.setNext(new SingleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {

			if (this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			} else {
				SingleLinkedListNode<T> nextNode = this.head;
				SingleLinkedListNode<T> previousNode = this.head;

				while (!nextNode.isNIL() && !nextNode.getData().equals(element)) {
					previousNode = nextNode;
					nextNode = nextNode.getNext();
				}

				if (!nextNode.isNIL()) {
					previousNode.setNext(nextNode.getNext());
				}
			}

		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[size()];

		if (this.size() > 0) {
			SingleLinkedListNode<T> node = this.head;

			int i = 0;

			while (!node.isNIL()) {
				array[i] = node.getData();
				i++;
				node = node.getNext();
			}
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
