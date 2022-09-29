package project1.bags;

import java.util.Iterator;

public class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
		numberOfEntries = 0;
	}

	/**
	 * Adds a new entry to this bag.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 * @return True.
	 */
	public boolean add(T newEntry) { // OutOfMemoryError possible
		// Add to beginning of chain:
		Node newNode = new Node(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
									// (firstNode is null if chain is empty)
		firstNode = newNode; // New node is at beginning of chain
		numberOfEntries++;

		return true;
	} // End add

	/**
	 * Retrieves all entries that are in this bag.
	 * 
	 * @return A newly allocated array of all the entries in this bag.
	 */
	public T[] toArray() {
		// The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast

		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		} // End while

		return result;
	} // End toArray

	/**
	 * Sees whether this bag is empty.
	 * 
	 * @return True if the bag is empty, or false if not.
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	/**
	 * Gets the number of entries currently in this bag.
	 * 
	 * @return The integer number of entries currently in the bag.
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	} // end getCurrentSize

	/**
	 * Removes one unspecified entry from this bag, if possible.
	 * 
	 * @return Either the removed entry, if the removal
	 *         was successful, or null.
	 */
	public T remove() {
		T result = null;
		if (firstNode != null) {
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
		}
		return result;
	} // end remove

	public Node getRefrenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		return currentNode;
	}

	/**
	 * Removes one occurrence of a given entry from this bag.
	 * 
	 * @param anEntry The entry to be removed.
	 * @return True if the removal was successful, or false otherwise.
	 */
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getRefrenceTo(anEntry);

		if (nodeN != null) {
			nodeN.setData(firstNode.getData());
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
			result = true;
		}
		return result;
	} // end remove

	/** Removes all entries from this bag. */
	public void clear() {
		while (!isEmpty())
			remove();
	} // end clear

	/**
	 * Counts the number of times a given entry appears in this bag.
	 * 
	 * @param anEntry The entry to be counted.
	 * @return The number of times anEntry appears in the bag.
	 */
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int counter = 0;
		Node currentNode = firstNode;
		while ((counter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				frequency++;
			counter++;
			currentNode = currentNode.getNextNode();
		}
		return frequency;
	} // End getFrequencyOf

	/**
	 * Tests whether this bag contains a given entry.
	 * 
	 * @param anEntry The entry to locate.
	 * @return True if the bag contains anEntry, or false otherwise.
	 */
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // End while
		return found; // STUB
	} // End contains

	/**
	 * Unites 2 LinkedBags together using Arrays
	 * Array does need to be in order; Can have null/empty spots
	 * 
	 * @param unionTarget the array to unite with this one
	 * @return New bag as an array, else this LinkedBag is returned
	 *
	 *         public T[] union(T[] unionTarget) {
	 *         LinkedBag<T> result = this;
	 * 
	 *         for (int index = 0; index < unionTarget.getCurrentSize(); index++) {
	 *         if (unionTarget[index] != null)
	 *         result.add(unionTarget[index]);
	 *         }
	 *         return result.toArray();
	 *         }
	 */

	/**
	 * Unites 2 LinkedBags together using BagInterface
	 * Array does need to be in order; Can have null/empty spots
	 * 
	 * @param bag the array to unite with this one
	 * @return New bag as an array, else this LinkedBag is returned
	 */
	@Override
	public BagInterface<T> union(BagInterface<T> bag) {
		LinkedBag<T> result = this;
		T[] temp = bag.toArray();

		for (int index = 0; index < bag.getCurrentSize(); ++index) {
			if (temp[index] != null) {
				result.add(temp[index]);
			}
		}
		return result;
	}

	public BagInterface<T> intersection(BagInterface<T> intersectTarget) {
		T[] tempArr = toArray();
		BagInterface<T> newBag = new LinkedBag<T>();

		for (T entry : tempArr) {
			if (intersectTarget.contains(entry)) {
				newBag.add(entry);
				intersectTarget.remove(entry);
			}
		}
		return newBag;
	}

	public T[] difference(T[] differenceTarget) {
		// TODO Auto-generated method stub
		return null;
	}

	class Node {
		private T data;
		private Node next;

		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		@SuppressWarnings("unused")
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}

	@Override
	public boolean isFull() {
		throw new UnsupportedOperationException("Method not supported");
	}

	// @Override
	// public BagInterface<T> intersection(BagInterface<T> bag) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public BagInterface<T> difference(BagInterface<T> bag) {
		// TODO Auto-generated method stub
		return null;
	}
}
