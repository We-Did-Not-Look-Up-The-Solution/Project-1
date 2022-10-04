package project1.bags;

import java.util.Arrays;
/**
	A class that implements a bag of objects by using an array.
	@author Frank M. Carrano, Timothy M. Henry
	@version 5.0
*/
public class ArrayBag<T> implements BagInterface<T> {

	/**The bag*/
	private T[] bag;
	/**Max Capacity (Soft) */
	private static final int DEFAULT_CAPCITY = 25;
	/**Tracks number of entries*/
	private int numberOfEntries;
	/**Marks if this bag's integrity is compromised*/
	private boolean integrityOK = false;
	/**Max Capacity (Hard)*/
	private static final int MAX_CAPACITY = 10000;
	
	/** Creates an empty bag whose initial capacity is 25. */
	public ArrayBag() {
		this(DEFAULT_CAPCITY);

	}

/** Creates an empty bag having a given capacity.
    @param desiredCapacity  The integer capacity desired. */
	public ArrayBag(int desiredCapacity) {
		if (desiredCapacity <= MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempBag = (T[]) new Object[desiredCapacity]; // All classes are objects
			bag = tempBag;
			numberOfEntries = 0;
			integrityOK = true;
		} else {
			throw new IllegalStateException("Attempted to create a bag whose capacity exceeds allowed maximum.");
		}
	}
	
	/** Throws an exception if this object is not initialized.*/
	   private void checkIntegrity()
	   {
	      if (!integrityOK)
	         throw new SecurityException("ArrayBag object is corrupt.");
	   } // end checkIntegrity
	
	/** Gets the current number of entries in this bag.
    @return  The integer number of entries currently in this bag. */
	public int getCurrentSize() {
		return numberOfEntries;
	}
	
	/** Sees whether this bag is empty.
    @return  True if this bag is empty, or false if not. */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	/** Adds a new entry to this bag.
    @param newEntry  The object to be added as a new entry.
    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry) {
		checkIntegrity();
		boolean wasAdded = true;
		if (isArrayFull()) {
			doubleCapacity();
		}
		bag[numberOfEntries] = newEntry;
		numberOfEntries++;
		return wasAdded;
	}

	/**
	 * Check if the passed capacity is within the acceptable maximum capacity
	 * @param capacity The desired capacity to check
	 */
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempted to make a bag whose capacity exceeds allowed max of " + MAX_CAPACITY);
	}

	/**
	 * Double the capacity of the array (will use the current array length)
	 */
	private void doubleCapacity() {
		int newLength = 2 * bag.length;
		checkCapacity(newLength);
		this.bag = Arrays.copyOf(bag, newLength);
	}

	/** Removes one unspecified entry from this bag, if possible.
    @return  Either the removed entry, if the removal
             was successful, or null. */
	public T remove() {
		checkIntegrity();
		T result = removeEntry(numberOfEntries - 1);
		return result;
	}

	/** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry) {
		checkIntegrity();
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);
		return anEntry.equals(result); // if the removed item is equal to the one in the argument, return true
	}

	/**
	 * A helper method that removes the object at the specified index,filling that
	 * index with the object in the last index,
	 * Then making the last
	 * index null
	 * 
	 * @param index | The index of the entry to remove (Will check for null)
	 * @return The entry removed
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
	 * Removes all entries from this bag */
	public void clear() {
		while (!isEmpty())
			remove();
	}
	
	/** Tests whether this bag contains a given entry.
    @param anEntry  The entry to locate.
    @return  True if this bag contains anEntry, or false otherwise. */
	public boolean contains(T anEntry) {
		checkIntegrity();
		return getIndexOf(anEntry) > -1;
	}
	
	/** Counts the number of times a given entry appears in this bag.
    @param anEntry  The entry to be counted.
    @return  The number of times anEntry appears in this ba. */
	public int getFrequencyOf(Object anEntry) {
		checkIntegrity();
		int count = 0;
		for (int i = 0; i < numberOfEntries; i++) {
			if (bag[i].equals(anEntry))
				count++;
		}
		return count;
	}
	
	/** Retrieves all entries that are in this bag.
    @return  A newly allocated array of all the entries in this bag. */
	public T[] toArray() {
		return Arrays.copyOf(bag, numberOfEntries);
	}

	/**
	 * Union of two bags. Does not modify the contents of the original bag.
	 * Array does not need to be in order; can have empty slots anywhere
	 * @author Miguel
	 * @param bagToUnite The bag to unite with this bag
	 * @return new bag if union was successful, else throw exception (Null should never be returned)
	 */
	@Override
	public BagInterface<T> union(BagInterface<T> bagToUnite) {
		checkIntegrity(); // Verify Integrity
		checkCapacity(bagToUnite.getCurrentSize() + this.getCurrentSize()); // Check Cap for total length
		ArrayBag<T> result = new ArrayBag<T>(); // Make a new empty bag of this type
		T[] temp1 = this.toArray();
		T[] temp2 = bagToUnite.toArray();
		
		// If the size of bagToUnite is >= the size of this bag, use that as the highest number of iterations for the "for-loop"
		// This allows to add copies of both bags in paralell
		if (bagToUnite.getCurrentSize() >= this.getCurrentSize()) {

			for (int index = 0; index < bagToUnite.getCurrentSize(); index++) {
				if (bagToUnite.toArray()[index] != null)
					result.add(temp2[index]); // Add a copy of bagToUnite to result
				
				if (index < this.getCurrentSize()) // Index must be in bounds of smallest bag
					if (temp1[index] != null)
						result.add(temp1[index]);
			}
		} else { // else use the size of this bag for the highest index
			for (int index = 0; index < this.getCurrentSize(); index++) {
				result.add(temp1[index]); // Add a copy of this bag to result
				
				if (index < bagToUnite.getCurrentSize()) // Index must be in bounds of smallest bag
					result.add(temp2[index]);
			}
		}
		return result;
	}
	
	@Override
	public BagInterface<T> difference(BagInterface<T> bag) {
		checkIntegrity();
		checkCapacity(bag.getCurrentSize() + this.getCurrentSize());
		BagInterface<T> output = new ArrayBag<T>();

		T[] bagA = this.toArray();
		T[] bagB = bag.toArray();
		boolean duplicates;

		for(int i = 0; i < this.getCurrentSize(); i++ ){
			duplicates = false;
			for (int j = 0; j < bag.getCurrentSize(); j++){

				if (bagA[i] == bagB[j] && bagA[i] != null && bagB[j] != null){
					duplicates = true;
					bagB[j]=null;
					break;
				}				
			}

			if (duplicates == true){
					bagA[i]=null;
			}

			if (duplicates == false && bagA[i] != null){
					output.add(bagA[i]);
			}
		}
					



		return output;
	}
	
	/**
	 * Returns a bag containing all items found in both bags, of the type BagInterface.
	 * Does not modify the original bag.
	 * @return new bag with intersection performed on it
	 */
	public BagInterface<T> intersection(BagInterface<T> givenBag) {
		BagInterface<T> newBag = new ArrayBag<T>();
		T[] givenArrayCopy = givenBag.toArray();
		T[] myArrayCopy = this.toArray();

		for (int index = 0; index < this.getCurrentSize(); index++) {
			for (T element : givenArrayCopy) {
				if (myArrayCopy[index].equals(element))
					newBag.add(element);
			}
		}
		return newBag;
	}
	
	/** Returns true if the array bag is full, or false if not.
	 * @return true if this bag is full, false otherwise*/
	public boolean isArrayFull() {
		return numberOfEntries == bag.length;
	}

	/**
	 * Finds the index of the first occurrence of the desired entry in this bag.
	 * @param anEntry The entry to find
	 * @return Index of entry if found, otherwise -1
	 */
	private int getIndexOf(T anEntry) {
		int posOfEntry = -1;
		boolean isFound = false;
		int index = 0;

		while (!isFound && (index < numberOfEntries)) {
			if (anEntry != null) {
				if (anEntry.equals(bag[index])) {
					isFound = true;
					posOfEntry = index;
				}
				index++;
			} else {
				throw new NullPointerException("The entry is null, may be an empty an entry in the array...");
			}
		}
		return posOfEntry;
	}

	/**
	 * Gets this array bag.
	 * 
	 * @return this array bag
	 */
	public T[] getBagArray() {
		return this.bag;
	}
	}