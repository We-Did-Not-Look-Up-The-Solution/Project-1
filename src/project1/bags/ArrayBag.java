package project1.bags;

import java.util.Arrays;
/**
	A class that implements a bag of objects by using an array.
	@author Frank M. Carrano, Timothy M. Henry
	@version 5.0
*/
public class ArrayBag<T> implements BagInterface<T> {

<<<<<<< Updated upstream
	private T[] bag; // Was final, but doubleCapacity needs this to be changeable
	private static final int DEFAULT_CAPCITY = 20; // Max Capacity (Soft)
	private int numberOfEntries; // Tracks number of entries
	private boolean integrityOK = false; // Marks if this bag's integrity is compromised
	private static final int MAX_CAPACITY = 10000; // Max Capacity (Hard)

=======
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
>>>>>>> Stashed changes
	public ArrayBag() {
		this(DEFAULT_CAPCITY);

	}
<<<<<<< Updated upstream

	/**
	 * Make an array bag with the specified capacity. Throws IllegalStateException
	 * if capacity exceeds max capacity
	 * 
	 * @param capacity | desired capacity
	 */
	public ArrayBag(int capacity) {
		if (capacity <= MAX_CAPACITY) {
=======
	
	/** Creates an empty bag having a given capacity.
    @param desiredCapacity  The integer capacity desired. */
	public ArrayBag(int desiredCapacity) {
		if (desiredCapacity <= MAX_CAPACITY) {
>>>>>>> Stashed changes
			@SuppressWarnings("unchecked")
			T[] tempBag = (T[]) new Object[desiredCapacity]; // All classes are objects
			bag = tempBag;
			numberOfEntries = 0;
			integrityOK = true;
		} else {
			throw new IllegalStateException("Attempted to create a bag whose capacity exceeds allowed maximum.");
		}
	}
<<<<<<< Updated upstream

	/**
	 * Check if the bag's integrity is okay (Currently not ever set)
	 */
	private void checkIntegrity() {
		if (!integrityOK)
			throw new SecurityException("ArrayBag object is corrupt");
	}

	/**
	 * Returns the current size of this bag (Actual Entries)
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
	 * Add an entry to this bag. Will double the capacity of the array if adding
	 * this would exceed the current size.
	 * 
	 * @return True if successful; False if unsuccessful.
	 */
=======
	
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
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	 * 
	 * @param capacity
=======
	 * @param capacity The desired capacity to check
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
	/**
	 * Removes an unspecified entry in the bag (The last one/most recent entry)
	 * 
	 * @return True if successful; False if unsuccessful
	 */
=======
	/** Removes one unspecified entry from this bag, if possible.
    @return  Either the removed entry, if the removal
             was successful, or null. */
>>>>>>> Stashed changes
	public T remove() {
		checkIntegrity();
		T result = removeEntry(numberOfEntries - 1);
		return result;
	}

<<<<<<< Updated upstream
	/**
	 * Remove a specified entry in this array bag
	 * 
	 * @param entry
	 * @return True if removal was successful; False if it failed
	 */
	public boolean remove(T entry) {
=======
	/** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

	/**
	 * Tests whether this bag contains a given entry
	 * 
	 * @param anEntry | The Entry to locate
	 * @return True if this bag contains anEntry, false otherwise
	 */
=======
	
	/** Tests whether this bag contains a given entry.
    @param anEntry  The entry to locate.
    @return  True if this bag contains anEntry, or false otherwise. */
>>>>>>> Stashed changes
	public boolean contains(T anEntry) {
		checkIntegrity();
		return getIndexOf(anEntry) > -1;
	}
<<<<<<< Updated upstream

	/**
	 * Return the number of times the specified entry appears in this bag
	 * 
	 * @param anEntry
	 */
=======
	
	/** Counts the number of times a given entry appears in this bag.
    @param anEntry  The entry to be counted.
    @return  The number of times anEntry appears in this ba. */
>>>>>>> Stashed changes
	public int getFrequencyOf(Object anEntry) {
		checkIntegrity();
		int count = 0;
		for (int i = 0; i < numberOfEntries; i++) {
			if (bag[i].equals(anEntry))
				count++;
		}
		return count;
	}
<<<<<<< Updated upstream

	/**
	 * Makes a copy of this bag as an array
	 * 
	 * @return Copy of this bag
	 */
=======
	
	/** Retrieves all entries that are in this bag.
    @return  A newly allocated array of all the entries in this bag. */
>>>>>>> Stashed changes
	public T[] toArray() {
		return Arrays.copyOf(bag, numberOfEntries);
	}

	/**
<<<<<<< Updated upstream
	 * Union of two bags of the type BagInterface<T>. Does not modify the contents
	 * of the original bag.
	 * Array does not need to be in order; can have empty slots anywhere
	 * 
	 * @param bagToUnite
	 * @return new bag if union was successful, else throw exception (Null should
	 *         never be returned)
=======
	 * Union of two bags. Does not modify the contents of the original bag.
	 * Array does not need to be in order; can have empty slots anywhere
	 * @author Miguel
	 * @param bagToUnite The bag to unite with this bag
	 * @return new bag if union was successful, else throw exception (Null should never be returned)
>>>>>>> Stashed changes
	 */
	@Override
	public BagInterface<T> union(BagInterface<T> bagToUnite) {
		checkIntegrity(); // Verify Integrity
		checkCapacity(bagToUnite.getCurrentSize() + this.getCurrentSize()); // Check Cap for total length
		ArrayBag<T> result = new ArrayBag<T>(); // Make a new empty bag of this type
<<<<<<< Updated upstream

		// If the size of bagToUnite is >= the size of this bag, use that as the highest
		// number of iterations for the "for-loop"
=======
		T[] temp1 = this.toArray();
		T[] temp2 = bagToUnite.toArray();
		
		// If the size of bagToUnite is >= the size of this bag, use that as the highest number of iterations for the "for-loop"
>>>>>>> Stashed changes
		// This allows to add copies of both bags in paralell
		if (bagToUnite.getCurrentSize() >= this.getCurrentSize()) {

			for (int index = 0; index < bagToUnite.getCurrentSize(); index++) {
				if (bagToUnite.toArray()[index] != null)
<<<<<<< Updated upstream
					result.add(bagToUnite.toArray()[index]); // Add a copy of bagToUnite to result

=======
					result.add(temp2[index]); // Add a copy of bagToUnite to result
				
>>>>>>> Stashed changes
				if (index < this.getCurrentSize()) // Index must be in bounds of smallest bag
					if (temp1[index] != null)
						result.add(temp1[index]);
			}
		} else { // else use the size of this bag for the highest index
			for (int index = 0; index < this.getCurrentSize(); index++) {
<<<<<<< Updated upstream
				result.add(this.toArray()[index]); // Add a copy of this bag to result

=======
				result.add(temp1[index]); // Add a copy of this bag to result
				
>>>>>>> Stashed changes
				if (index < bagToUnite.getCurrentSize()) // Index must be in bounds of smallest bag
					result.add(temp2[index]);
			}
		}
		return result;
	}
<<<<<<< Updated upstream

	/**
	 * Returns a bag containing all items found in both bags, of the type
	 * BagInterface<T>.
	 * Does not modify the original bag.
	 * 
	 * @author Richard Pacheco
	 * @param givenBag the bag to combine T elements with
=======
	
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
	
	/*
	 * PROPOSED FIX FOR INTERSECTION:
	 * public BagInterface<T> intersection(BagInterface<T> givenBag) {
		BagInterface<T> newBag = new ArrayBag<T>();
		BagInterface<T> copy = new ArrayBag<T>();
		
		if (givenBag.getCurrentSize() >= this.getCurrentSize()) {
			// given bag is the biggest; Make a copy of that, iterate through smallest
			for (int index = 0; index < givenBag.getCurrentSize(); index++) {
				copy.add(givenBag.toArray()[index]);
			}
			// this bag is the smallest, to reduce time, iterate through that
 			for (int index = 0; index < this.getCurrentSize(); index++) {
 				if (copy.contains(this.toArray()[index])) {
 					newBag.add(this.toArray()[index]);
 					copy.remove(this.toArray()[index]); // givenBag contents not changed
 				}
 			}
		} else {
			// this bag is the biggest, make a copy of it
			for (int index = 0; index < this.getCurrentSize(); index++) {
				copy.add(this.toArray()[index]);
			}
			// give bag is the smallest, to reduce time, iterate through that
			for (int index = 0; index < givenBag.getCurrentSize(); index++) {
 				if (copy.contains(givenBag.toArray()[index])) {
 					newBag.add(givenBag.toArray()[index]);
 					copy.remove(givenBag.toArray()[index]);
 				}
 			}
		}
		return newBag;
	}
	 */
	
	
	/**
	 * Returns a bag containing all items found in both bags, of the type BagInterface.
	 * Does not modify the original bag.
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

	/**
	 * Get whether this bag is full or not
	 * 
	 * @return True if the number of entries is equal to the Capacity of this bag,
	 *         else false
	 */
	public boolean isFull() {
=======
	
	/** Returns true if the array bag is full, or false if not.
	 * @return true if this bag is full, false otherwise*/
	public boolean isArrayFull() {
>>>>>>> Stashed changes
		return numberOfEntries == bag.length;
	}

	/**
	 * Finds the index of the first occurrence of the desired entry in this bag.
<<<<<<< Updated upstream
	 * 
	 * @param anEntry
=======
	 * @param anEntry The entry to find
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
	@Override
	public BagInterface<T> difference(BagInterface<T> bag) {
		checkIntegrity();
		checkCapacity(bag.getCurrentSize() + this.getCurrentSize());
		// creates copy
		BagInterface<T> output = new ArrayBag<T>();

		// turns bags into an array
		T[] bagA = this.toArray();
		T[] bagB = bag.toArray();
		boolean duplicates;

		// iterates through both bags
		for (int i = 0; i < this.getCurrentSize(); i++) {
			duplicates = false;
			for (int j = 0; j < bag.getCurrentSize(); j++) {

				// if the content is the same and it is not equal to null, then set duplicate to
				// true and set whatever is in bagB to null
				if (bagA[i] == bagB[j] && bagA[i] != null && bagB[j] != null) {
					duplicates = true;
					bagB[j] = null;
					break;
				}
			}

			// if duplicates is equal to true then set whatever is in BagA to null
			if (duplicates == true) {
				bagA[i] = null;
			}

			// if duplicates is still false and bagA is not equal then add the content
			if (duplicates == false && bagA[i] != null) {
				output.add(bagA[i]);
			}
		}

		return output;
	}
}
=======
	
	}
>>>>>>> Stashed changes
