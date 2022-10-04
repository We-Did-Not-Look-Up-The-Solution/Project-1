package project1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project1.bags.ArrayBag;
import project1.bags.BagInterface;
import project1.bags.LinkedBag;

class BagUnitTest {

	private ArrayBag<String> bag1= new ArrayBag<String>();
	ArrayBag<String> bag2= new ArrayBag<String>();
	LinkedBag<String> linkedbag1= new LinkedBag<String>();
	LinkedBag<String> linkedbag2= new LinkedBag<String>();
	
	public void setBags() {
		bag1.add("a");
		bag1.add("b");
		bag1.add("c");
		bag2.add("b");
		bag2.add("b");
		bag2.add("d");
		bag2.add("c");

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
		String[] expectedBag1 = {"b", "a", "b", "b", "d", "c", "c"};
		String[] expectedBag2 = {"b", "b", "b", "d", "d", "c", "c"};
		setBags();
		BagInterface<String> testBag = bag1.union(bag2);
		BagInterface<String> linkedTestBag = linkedbag1.union(linkedbag2);
		assertArrayEquals(expectedBag1, testBag.toArray());
		assertArrayEquals(expectedBag2, linkedTestBag.toArray());
	}
	
	@Test
	void testDifference() {
		String[] expectedBag = {"a"};
		String[] expectedLinkedBag = {"a"};
		setBags();
		BagInterface<String> testBag = bag1.difference(bag2);
		BagInterface<String> testLinkedBag = linkedbag1.difference(linkedbag2);
		assertArrayEquals(expectedBag, testBag.toArray());
		assertArrayEquals(expectedLinkedBag, testLinkedBag.toArray());
		
	}
	
	@Test
	void testIntersection() {
		String[] expectedBag1 = {"b"};
		String[] expectedBag2 = {"c"};
		setBags();
		BagInterface<String> testBag = bag2.intersection(bag1);
		BagInterface<String> linkedTestBag = linkedbag1.intersection(linkedbag2);
		assertArrayEquals(expectedBag1, testBag.toArray());
		assertArrayEquals(expectedBag2, linkedTestBag.toArray());
	}

}
