package project1.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project1.bags.ArrayBag;
import project1.bags.BagInterface;
import project1.bags.LinkedBag;

class ResizableArrayTest {

	private ArrayBag<String> bag1= new ArrayBag<String>();
	ArrayBag<String> bag2= new ArrayBag<String>();
	
	public void setBags() {
		bag1.add("a");
		bag1.add("b");
		bag1.add("c");
		bag2.add("b");
		bag2.add("b");
		bag2.add("d");
		bag2.add("c");

	}
	
	@Test
	void testUnion() {
		String[] expectedBag1 = {"b", "a", "b", "b", "d", "c", "c"};
		setBags();
		BagInterface<String> testBag = bag1.union(bag2);
		assertArrayEquals(expectedBag1, testBag.toArray());
	}
	
	@Test
	void testDifference() {
		String[] expectedBag = {"a"};
		setBags();
		BagInterface<String> testBag = bag1.difference(bag2);
		assertArrayEquals(expectedBag, testBag.toArray());
		
	}
	
	@Test
	void testIntersection() {
		String[] expectedBag1 = {"b"};
		setBags();
		BagInterface<String> testBag = bag2.intersection(bag1);
		assertArrayEquals(expectedBag1, testBag.toArray());
	}

}
