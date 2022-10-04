package project1.tests;

import java.util.Arrays;

import project1.bags.ArrayBag;
import project1.bags.BagInterface;
import project1.bags.LinkedBag;

public class BagDriver {

	
	public static void main(String[] args) {
		ArrayBag<String>bag1= new ArrayBag<String>();
		bag1.add("a");
		bag1.add("b");
		bag1.add("c");
		// bag1.toArray();
		ArrayBag<String>bag2= new ArrayBag<String>();
		bag2.add("b");
		bag2.add("b");
		bag2.add("d");
		bag2.add("c");

		LinkedBag<String>linkedbag1= new LinkedBag<String>();
		linkedbag1.add("a");
		linkedbag1.add("b");;
		linkedbag1.add("c");;
		LinkedBag<String>linkedbag2= new LinkedBag<String>();
		linkedbag2.add("b");
		linkedbag2.add("b");;
		linkedbag2.add("d");
		linkedbag2.add("c");
		
		System.out.println("Content of Bag1: " + Arrays.toString(bag1.toArray())); // Bag 1
		System.out.println("Content of Bag2: " + Arrays.toString(bag2.toArray()) + "\n");
		
		System.out.println("Content of Linked Bag1: " + Arrays.toString(linkedbag1.toArray()));
		System.out.println("Content of Linked Bag2: " + Arrays.toString(linkedbag2.toArray()) + "\n");


		BagInterface<String> arrayADTBag = bag1.union(bag2); // Perform Union on Bag 
		BagInterface<String> linkedADTBag = linkedbag1.union(linkedbag2); // Perform union on Linked Bag
		
		System.out.println("Result of union on Bag1 with Bag2: " + Arrays.toString(arrayADTBag.toArray()));
		System.out.println("Result of union on LinkedBag1 with LinkedBag2: " + Arrays.toString(linkedADTBag.toArray()));
		System.out.println();
		
		// arrayADTBag = bag1.intersection(bag2);
		// linkedADTBag = linkedbag1.intersection(linkedbag2);
		
		// System.out.println("Result of intersection on Bag1: " + Arrays.toString(arrayADTBag.toArray()));
		// System.out.println("Result of intersection on LinkedBag1: " + Arrays.toString(linkedADTBag.toArray()));
		
		BagInterface<String> arrayDifferenceBag1 = bag1.difference(bag2); //Perform Difference on Array Bag 1
		BagInterface<String> arrayDifferenceBag2 = bag2.difference(bag1); //Perform Difference on Array Bag 2

		BagInterface<String> linkedDifferenceBag1 = bag1.difference(bag2); //Perform Difference on Array Bag 1
		BagInterface<String> linkedDifferenceBag2 = bag2.difference(bag1); //Perform Difference on Array Bag 2
		
		System.out.println("Result of difference on Array Bag1 with Array Bag2: " + Arrays.toString(arrayDifferenceBag1.toArray()));
		System.out.println("Result of difference on Array Bag2 with Array Bag1: " + Arrays.toString(arrayDifferenceBag2.toArray()));

		System.out.println("Result of difference on Linked Bag1 with Linked Bag2: " + Arrays.toString(linkedDifferenceBag1.toArray()));
		System.out.println("Result of difference on Linked Bag2 with Linked Bag1: " + Arrays.toString(linkedDifferenceBag2.toArray()));
		
		System.out.println("\nContents of the Bags again ====================================");
		
		System.out.println("Content of Bag1: " + Arrays.toString(bag1.toArray())); // Bag 1
		System.out.println("Content of Bag2: " + Arrays.toString(bag2.toArray()) + "\n");
		
		System.out.println("Content of Linked Bag1: " + Arrays.toString(linkedbag1.toArray()));
		System.out.println("Content of Linked Bag2: " + Arrays.toString(linkedbag2.toArray()) + "\n");
		
	}
	
	
}
