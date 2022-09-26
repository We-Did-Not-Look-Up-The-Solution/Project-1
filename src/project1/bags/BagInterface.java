package project1.bags;

public interface BagInterface<T> {

	int getCurrentSize();

	boolean isEmpty();
	
	public boolean isFull();
	
	boolean add(T newEntry);

	T remove();

	boolean remove(T entry);

	void clear();

	int getFrequencyOf(T anEntry);

	boolean contains(T entry);

	T[] toArray();

	// Project 1 Specs.

	/**
	 * Takes an IBag and returns a bag with the contents of T and the argument.
	 * <br>
	 * <br>
	 * if Bag1 has cheese and milk
	 * and Bag2 had cereal and bread,
	 * this returns a bag with cheese, milk, cereal, bread
	 * 
	 * @param unionTarget
	 * @return
	 */
public BagInterface<T> union(BagInterface<T> bag);
public BagInterface<T> intersection(BagInterface<T> bag);
public BagInterface<T> difference(BagInterface<T> bag);
}
