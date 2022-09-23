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
	T[] union(T[] unionTarget);

	/**
	 * Takes a IBag and returns a bag with only T that occurred in both
	 * 
	 * If Bag1 has carrot, cheese, napkins
	 * and Bag2 has cheese, cheese, apple, milk,
	 * then the bag returned has only cheese
	 * 
	 * @param intersectTarget
	 * @return
	 */
	T[] intersection(T[] intersectTarget);

	T[] diference(T[] differenceTarget);
}
