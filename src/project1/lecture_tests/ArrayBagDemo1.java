package project1.lecture_tests;

import project1.bags.BagInterface;
import project1.bags.ArrayBag;

public class ArrayBagDemo1 {

	public static void main(String[] args) {
		BagInterface<String> aBag = new ArrayBag<String>();
		testIsFull(aBag, false);
		
		String[] contentsOfBag1 = {"A", "A", "B", "A", "C", "A"};
		testAdd(aBag, contentsOfBag1);
		testIsFull(aBag, false);
		
		aBag = new ArrayBag<String>(7);
		System.out.println("\nA new emty bag:");
		
		testIsFull(aBag, false);
		
		String[] contentsOfBag2 = {"A", "B", "A", "C", "B", "C", "D"};
		testAdd(aBag, contentsOfBag2);
		testIsFull(aBag, true);
	}
	
	private static void testAdd(BagInterface<String> aBag, String[] content) {
		System.out.println("Adding the bag: ");
		for (int index = 0; index < content.length; index++) {
			aBag.add(content[index]);
			System.out.println(content[index] + " ");
		}
		System.out.println();
		displayBag(aBag);
	}
	
	private static void testIsFull(BagInterface<String> bagIn, boolean correctResult) {
		System.out.print("Testing the method is Full with ");
		if (correctResult) {
			System.out.print("a full bag. ");
		} else {
			System.out.print("a bag that is not full: ");
		}
		System.out.print("isFull finds the bag ");
		if (correctResult && bagIn.isFull()) {
			System.out.print("full: OK");
		} else if (correctResult) {
			System.out.print("not full, but it is full: ERROR.");
		} else if (!correctResult && bagIn.isFull()) {
			System.out.print("full, but it is not full: ERROR.");
		} else {
			System.out.print("not full: OK.");
		}
		System.out.println();
	}
	
	private static void displayBag(BagInterface<String> bagIn) {
		System.out.println("The bag contains the following strings(s): ");
		Object[] bagArray = bagIn.toArray();
		for (int index = 0; index < bagArray.length; index++) {
			System.out.println(bagArray[index] + " ");
		}
		System.out.println();
	}
}
