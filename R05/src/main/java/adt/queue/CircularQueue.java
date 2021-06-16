package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		} else if (element != null) {

			if (this.isEmpty()) {
				this.head = (this.head + 1) % this.array.length;
			}

			this.tail = (this.tail + 1) % this.array.length;
			this.array[this.tail] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T element = this.array[head];
			this.head = (this.head + 1) % this.array.length;
			this.elements--;
			return element;
		}

	}

	@Override
	public T head() {
		T head = null;
		if (!isEmpty()) {
			head = this.array[this.head];
		}
		return head;
	}

	@Override
	public boolean isEmpty() {
		return (this.elements == 0);
	}

	@Override
	public boolean isFull() {
		return (this.elements == array.length);
	}

}