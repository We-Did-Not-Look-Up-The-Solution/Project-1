package project1.tests;

import java.util.Arrays;

import project1.bags.ArrayBag;
import project1.bags.BagInterface;

public class BagDriver {

	
	public static void main(String[] args) {
		ArrayBag<String>bag1= new ArrayBag<String>();
		bag1.add("item");
		bag1.add("amongus");
		bag1.add("imposter");
		bag1.toArray();
		ArrayBag<String>bag2= new ArrayBag<String>();
		bag2.add("hi");
		bag2.add("dog");
		bag2.add("19 dollar fortnite card");
		System.out.println(Arrays.toString(bag1.toArray()));

		BagInterface<String>bag3 = bag1.union(bag2);

		System.out.println(Arrays.toString(bag3.toArray()));
	}
	
	
}
