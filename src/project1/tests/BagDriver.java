package project1.tests;

import java.util.Arrays;
import java.util.LinkedList;

import project1.bags.ArrayBag;
import project1.bags.BagInterface;
import project1.bags.LinkedBag;

public class BagDriver {

	
	public static void main(String[] args) {
		ArrayBag<String>bag1= new ArrayBag<String>();
		bag1.add("a");
		bag1.add("b");
		bag1.add("c");
		bag1.toArray();
		ArrayBag<String>bag2= new ArrayBag<String>();
		bag2.add("b");
		bag2.add("b");
		bag2.add("d");
		bag2.add("e");

		LinkedBag<String>linkedbag1= new LinkedBag<String>();
		linkedbag1.add("a");
		linkedbag1.add("b");;
		linkedbag1.add("c");;
		LinkedBag<String>linkedbag2= new LinkedBag<String>();
		linkedbag1.add("b");
		linkedbag1.add("b");;
		linkedbag1.add("d");
		linkedbag1.add("e");
		
		System.out.println(Arrays.toString(bag1.toArray()));

		BagInterface<String>adtbag = bag1.union(bag2);

		System.out.println(Arrays.toString(bag2.toArray()));
	}
	
	
}
