package project1.bags;

import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T> {

	private T[] bag; // Was final, but doubleCapacity needs this to be changeable
	private static final int DEFAULT_CAPCITY = 20;
	private int numberOfEntries;
	private boolean integrityOK = false;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayBag() {
		this(DEFAULT_CAPCITY);
		
	}
	
	public ArrayBag(int capacity) {
		if (capacity <= MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempBag = (T[]) new Object[capacity]; // All classes are objects
			bag = tempBag;
			numberOfEntries = 0;
			integrityOK = true;
		} else {
			throw new IllegalStateException("Attempted to create a bag whose capacity exceeds allowed maximum.");
		}
	}
	
	private void checkIntegrity() {
		if (!integrityOK) throw new SecurityException("ArrayBag object is corrupt");
	}
	
	/**
	 * Returns the current size of this bag
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	}
	
	/**
	 * Get whether this bag is Empty
	 * 
	 * @return Whether the number of Entries is 0
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	/**
	 * Add an entry to this bag.
	 * @return True if successful; False if unsuccessful.
	 */
	public boolean add(T newEntry) {
		checkIntegrity();
		boolean wasAdded = true;
		if (isFull()) {
			doubleCapacity();
		}
		bag[numberOfEntries] = newEntry;
		numberOfEntries++;
		
		return wasAdded;
	}
	
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempted to make a bag whose capacity exceeds allowed max of " + MAX_CAPACITY);
	}
	
	private void doubleCapacity() {
		int newLength = 2 * bag.length;
		checkCapacity(newLength);
      	this.bag = Arrays.copyOf(bag, newLength);
	}

	/**
	 * Removes an unspecified entry in the bag (The last one/most recent entry)
	 * @return True if successful; False if unsuccessful
	 */
	public T remove() {
		checkIntegrity();
		T result = removeEntry(numberOfEntries - 1);
		return result;
	}

	public boolean remove(T entry) {
		checkIntegrity();
		int index = getIndexOf(entry);
		T result = removeEntry(index);
		return entry.equals(result); // if the removed item is equal to the one in the argument, return true
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
			bag[numberOfEntries - 1] = null;
			numberOfEntries--;
		}
		return result;
	}
	
	/**
	 * Removes all entries from this bag
	 */
	public void clear() {
		while (!isEmpty())
			remove();
	}
	
	/**
	 * Tests whether this bag contains a given entry
	 * @param anEntry : The Entry to locate
	 * @return True if this bag contains anEntry, false otherwise
	 */
	public boolean contains(T anEntry) {
		checkIntegrity();
		return getIndexOf(anEntry) > -1;
	}
	
	public int getFrequencyOf(Object anEntry) {
		checkIntegrity();
		int count = 0;
		for (int i = 0; i < numberOfEntries; i++) {
			if (bag[i].equals(anEntry)) count++;
		}
		return count;
	}
	
	/**
	 * Makes a copy of this bag
	 * @return Copy of this bag
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Object[this.numberOfEntries];
		for (int i = 0; i < numberOfEntries; i++) {
			arrayResult[i] = bag[i];
		}
		return arrayResult;
	}
	
	/**
	 * Unite two bags together
	 * Array does not need to be in order; can have empty slots anywhere
	 * @param arrayToUnite
	 * @return new bag if union was successful, else throw exception (Null should never be returned)
	 *
	public T[] union(T[] arrayToUnite) {
		checkIntegrity(); // Verify
		checkCapacity(arrayToUnite.length + this.numberOfEntries); // Check Capacity (Don't actually know numberOfEntries, so worst case is the array has 1 entry)
		ArrayBag<T> result = this; // Copy of this bag (Contents of both bags should not change)
			
		for (int index = 0; index < arrayToUnite.length; index++) { 
			// Iterate through this array (We use the length instead of numOfEntries, as array can be out of order, like having empty slots before filled ones)
			if (arrayToUnite[index] != null) // If the current slot is not empty
				result.add(arrayToUnite[index]); // Add slot contents to the bag (using add method, which will auto-resize bag if new entry doesn't fit)
		}
		
		return result.toArray(); // Can never return this bag w/o changes (Failsafes in place)
	}*/
	
	/**
	 * Union of two bags using BagInterface<T> instead of an array
	 * Array does not need to be in order; can have empty slots anywhere
	 * @param bagToUnite
	 * @return new bag if union was successful, else throw exception (Null should never be returned)
	 */
	@Override
	public BagInterface<T> union(BagInterface<T> bagToUnite) {
		checkIntegrity();
		checkCapacity(bagToUnite.getCurrentSize() + this.getCurrentSize());
		ArrayBag<T> result = new ArrayBag<T>();
		T[] temp = bagToUnite.toArray();
		
		for (int index = 0; index < bagToUnite.getCurrentSize(); index++) {
			if (temp[index] != null) {
				result.add(temp[index]);
			}
		}

		return result;
	}
	
	

	public T[] intersection(T[] intersectTarget) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public T[] diference(T[] differenceTarget) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Get whether this bag is full or not
	 * 
	 * @return Whether the number of entries is equal to the Capacity of this bag
	 */
	public boolean isFull() {
		return numberOfEntries == bag.length;
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

	@Override
	public BagInterface<T> intersection(BagInterface<T> bag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BagInterface<T> difference(BagInterface<T> bag) {

		BagInterface<T> output = new ArrayBag<T>();
		T[] bag1 =this.toArray();
		T[] bag2 = bag.toArray();

		return output;
	}
}
