package project1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project1.bags.ArrayBag;
import project1.bags.BagInterface;
import project1.bags.LinkedBag;

class LinkedBagTest {

	LinkedBag<String> linkedbag1= new LinkedBag<String>();
	LinkedBag<String> linkedbag2= new LinkedBag<String>();
	
	public void setBags() {
		linkedbag1.add("a");
		linkedbag1.add("b");
		linkedbag1.add("c");
		linkedbag2.add("b");
		linkedbag2.add("b");
		linkedbag2.add("d");
		linkedbag2.add("c");
	}
	
	@Test
	void testUnion() {
		String[] expectedBag2 = {"b", "b", "b", "d", "d", "c", "c"};
		setBags();
		BagInterface<String> linkedTestBag = linkedbag1.union(linkedbag2);
		assertArrayEquals(expectedBag2, linkedTestBag.toArray());
	}
	
	@Test
	void testDifference() {
		String[] expectedLinkedBag = {"a"};
		setBags();
		BagInterface<String> testLinkedBag = linkedbag1.difference(linkedbag2);
		assertArrayEquals(expectedLinkedBag, testLinkedBag.toArray());
		
	}
	
	@Test
	void testIntersection() {
		String[] expectedBag2 = {"c"};
		setBags();
		BagInterface<String> linkedTestBag = linkedbag1.intersection(linkedbag2);
		assertArrayEquals(expectedBag2, linkedTestBag.toArray());
	}

}
