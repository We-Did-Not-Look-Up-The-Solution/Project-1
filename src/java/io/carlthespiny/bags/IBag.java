package java.io.carlthespiny.bags;

public interface IBag<T> {

	abstract int getCurrentSize();
	
	abstract boolean isEmpty();
	
	abstract boolean add(T newEntry);
	
	abstract boolean remove();
	
	abstract boolean remove(T entry);
	
	abstract void clear();
	
	abstract int getFrequencyOf(T anEntry);
	
	abstract boolean contains(T entry);
	
	abstract T[] toArray();
	
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
	abstract T union(T unionTarget);
	
	/**
	 * Takes a IBag and returns a bag with only T that occurred in both
	 * 
	 * If Bag1 has carrot, cheese, napkins
	 * and Bag2 has cheese, cheese, apple, milk,
	 * then the bag returned has only cheese
	 * @param intersectTarget
	 * @return
	 */
	abstract T intersection(T intersectTarget);	
	
	
	
}
