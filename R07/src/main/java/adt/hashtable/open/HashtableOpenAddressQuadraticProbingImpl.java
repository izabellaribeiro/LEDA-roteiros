package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isFull()) {
				throw new HashtableOverflowException();
			}

			if (search(element) == null) {
				int cont = 0;
				int hash = getHash(element, cont);
				while (this.table[hash] != null && cont < this.table.length
						&& !this.table[hash].toString().equals("D")) {
					COLLISIONS++;
					cont++;
					hash = getHash(element, cont);
				}
				this.table[hash] = element;
				elements++;
			}

		}
	}

	private int getHash(T element, int prob) {
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, prob);
	}


	@Override
	public void remove(T element) {
		if (element != null & !this.isEmpty()) {
			if (indexOf(element) != -1) {
				this.table[indexOf(element)] = new DELETED();
				elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T out = null;
		if (element != null && !this.isEmpty()) {
			if (this.indexOf(element) != -1) {
				out = element;
			}
		}
		return out;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		int cont = 0;

		int hash = getHash(element, cont);

		while (this.table[hash] != null && cont < this.table.length) {
			if (this.table[hash].equals(element)) {
				return hash;
			}

			cont++;
			hash = getHash(element, cont);
		}

		return index;
	}
}
