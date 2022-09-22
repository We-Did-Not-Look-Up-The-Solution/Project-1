package java.io.carlthespiny.bags;

import java.util.Arrays;

public class ResizableArrayBag<T> implements IBag<T> {

	private T[] bag; // Was final, but doubleCapacity needs this to be changeable
	private int numberOfEntries;
	private static final int DEFAULT_CAPCITY = 20;
	
	public ResizableArrayBag(int capacity) {
		this.numberOfEntries = capacity;
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[capacity]; // All classes are objects
		this.bag = tempBag;
	}
	
	/**
	 * Returns the current size of this bag
	 */
	@Override
	public int getCurrentSize() {
		return this.numberOfEntries;
	}
	
	/**
	 * Add an entry to this bag.
	 * @return True if successful; False if unsuccessful.
	 */
	@Override
	public boolean add(T newEntry) {
		boolean wasAdded = false;
		if (isFull()) {
			//doubleCapacity();
		}
		this.bag[numberOfEntries] = newEntry;
		numberOfEntries++;
		
		return true;
	}

	/**
	 * Removes an unspecified entry in the bag (The last one/most recent entry)
	 * @return True if successful; False if unsuccessful
	 */
	@Override
	public boolean remove() {
		if (!this.isEmpty()) {

			return true;
		} else return false;
	}

	@Override
	public boolean remove(T entry) {
		if (!this.isEmpty()) {
			int index = getIndexOf(entry);
			T result = removeEntry(index);
			return entry.equals(result); // if the removed item is equal to the one in the argument, return true
		}
		return false;
	}
	
	/**
	 * A helper method that removes the object at the specified index, 
	 * filling that index with the object in the last index, then making the last index null
	 * 
	 * @param index
	 * @return
	 */
	private T removeEntry(int index) {
		T result = null;
		if (!this.isEmpty() && index >= 0) {
			result = bag[index];
			bag[index] = bag[numberOfEntries - 1];
			bag[numberOfEntries] = null;
			numberOfEntries--;
		}
		return result;
	}
	
	@Override
	public Object union(Object unionTarget) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object intersection(Object intersectTarget) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Clears all entries in this bag
	 */
	@Override
	public void clear() {
		for (int i = 0; i < numberOfEntries; i++) {
			bag[i] = null;
		}
	}

	@Override
	public int getFrequencyOf(Object anEntry) {
		// checkIntegrity;
		int count = 0;
		if (!this.isEmpty()) {
			for (int i = 0; i < numberOfEntries; i++) {
				if (bag[i].equals(anEntry)) count++;
			}
		}
		return count;
	}
	
	private void doubleCapacity() {
		int newLength = bag.length * 2;
		//checkCapacity();
		bag = Arrays.copyOf(bag, newLength);
	}
	

	/**
	 * Checks if this bag has the specified entry.
	 * Requires the bag to not be Empty (Checked before this is called)
	 */
	@Override
	public boolean contains(Object entry) {
		// checkIntegrity;
		
		return this.getFrequencyOf(entry) > 0;
	}
	
	/**
	 * Get whether this bag is Empty
	 * 
	 * @return Whether the number of Entries is 0
	 */
	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0 ? true : false;
	}
	
	/**
	 * Get whether this bag is full or not
	 * 
	 * @return Whether the number of entries is equal to the Capacity of this bag
	 */
	public boolean isFull() {
		return numberOfEntries == DEFAULT_CAPCITY ? true : false;
	}
	
	/**
	 * Finds the index of the entry in this bag.
	 * @param anEntry
	 * @return Index of entry if found, otherwise -1
	 */
	private int getIndexOf(T anEntry) {
		int posOfEntry = -1;
		boolean isFound = false;
		int index =0;
		
		while (!isFound && (index < numberOfEntries)) {
			if (anEntry.equals(bag[index])) {
				isFound = true;
				posOfEntry = index;
			}
			index++;
		}
		return posOfEntry;
	}
	
	/**
	 * Getter for this bag array. 
	 * <b>Remember that it is final, so it cannot be changed, but its content can
	 * 
	 * @return
	 */
	public T[] getBagArray() {
		return this.bag;
	}
	
	/**
	 * Makes a copy of this bag
	 * @return Copy of this bag
	 */
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] arrayToSend = (T[]) new Object[this.numberOfEntries];
		for (int i = 0; i < numberOfEntries; i++) {
			arrayToSend[i] = this.bag[i];
		}
		return arrayToSend;
	}

}
