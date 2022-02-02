
/**
 * Name: Joshua Yang
 * ID: A16667394
 * Email: jwyang@ucsd.edu
 * Sources Used: None
 * 
 * This file is a tester for linked list iterator. 
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * Tester class that tests interator. Has instance variables listLen3, 
 * listLen3Iter, exceptionThrown. 
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {

    private MyLinkedList listLen3;
    private MyLinkedList.MyListIterator listLen3Iter;
    private boolean exceptionThrown;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen3 = new MyLinkedList();
        listLen3.add("Paul");
        listLen3.add("Cao");
        listLen3.add("CSE12");
        listLen3Iter = listLen3.new MyListIterator();
        exceptionThrown = false;
    }

    /**
     * test the hasNext method when we are at the tail
     */
    @Test
    public void testHasNext() {
        listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext().getNext().getNext();
        listLen3Iter.idx = 3;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;
        assertEquals(false, listLen3Iter.hasNext());
    }

    /**
     * TODO: test the next method when [...]
     */
    @Test
    public void testNext() {

    }

    /**
     * TODO: test the hasPrevious method when [fill in another one here]
     */
    @Test
    public void testHasPrevious() {

    }

    /**
     * TODO: test the previous method when [...]
     */
    @Test
    public void testPrevious() {

    }

    /**
     * TODO: test the nextIndex method when [...]
     */
    @Test
    public void testNextIndex() {

    }

    /**
     * test the previousIndex method when we are at the head and there is 
     * no previous index
     */
    @Test
    public void testPreviousIndex() {
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.forward = true;
        listLen3Iter.canRemoveOrSet = true;
        assertEquals(-1, listLen3Iter.previousIndex());
    }

    /**
     * TODO: test the set method when [...]
     */
    @Test
    public void testSet() {
        listLen3Iter.left = listLen3.head.getNext().getNext();
        listLen3Iter.right = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.idx = 2;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.set("new string?!");
        assertEquals("new string?!", listLen3Iter.right.getElement());

        // test set after previous()
        listLen3Iter.previous();
        listLen3Iter.set("surf!!");
        assertEquals("surf!!", listLen3Iter.right.getElement());

        // test set after next()
        listLen3Iter.next();
        listLen3Iter.set("yew!");
        assertEquals("yew!", listLen3Iter.left.getElement());

        // test for set after remove
        listLen3Iter.remove();
        try {
        listLen3Iter.set("huah!!");
        }
        catch (IllegalStateException e) {
            exceptionThrown = true;
        }
        assertTrue("Exception should be thrown", exceptionThrown);
    }

    /**
     * test the remove method when the last element is removed from the 
     * linked list
     */
    @Test
    public void testRemoveTestOne() {
        listLen3Iter.left = listLen3.head.getNext().getNext();
        listLen3Iter.right = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.idx = 2;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.remove();
        assertEquals("Valid remove when forward", null,
        listLen3Iter.right.getElement());
        
        // next() should throw exception because next is the tail
        try {
            listLen3Iter.next();
        }
        catch (NoSuchElementException e) {
            exceptionThrown = true;
        }
        assertTrue("Exception should be thrown", exceptionThrown);

        try {
            listLen3Iter.remove();
        }
        catch (IllegalStateException e) {
            exceptionThrown = true;
        }
        assertTrue("Exception should be thrown", exceptionThrown);
    }

    /**
     * TODO: test the remove method when [fill in another one here]
     */
    @Test
    public void testRemoveTestTwo() {

    }

    /**
     * TODO: test the add method when [...]
     */
    @Test
    public void testAdd() {

    }

}