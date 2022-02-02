
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

    private MyLinkedList listLen3, listEmpty;
    private MyLinkedList.MyListIterator listLen3Iter, listEmptyIter;
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
        listEmpty = new MyLinkedList<String>();
        listEmptyIter = listEmpty.new MyListIterator();
        exceptionThrown = false;
    }

    /**
     * test the hasNext method when the list is empty
     */
    @Test
    public void testHasNext() {
        assertFalse("Should be false", listEmptyIter.hasNext());
    }

    /**
     * test the next method when we are at the tail
     */
    @Test
    public void testNext() {
        // set iterator to tail
        listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext().getNext().getNext();
        listLen3Iter.idx = 3;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;
        try {
            listLen3Iter.next();
        }
        catch(NoSuchElementException e) {
            exceptionThrown = true;
        }
        assertTrue("Excpetion should be thrown", exceptionThrown);
    }

    /**
     * test the hasPrevious method when the list is empty
     */
    @Test
    public void testHasPrevious() {
        assertFalse("Should be false", listEmptyIter.hasPrevious());
    }

    /**
     * test the previous method when the iterator is at the head
     */
    @Test
    public void testPrevious() {
        // set iterator to the head
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = 
            listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;
        try {
            listLen3Iter.previous();
        }
        catch(NoSuchElementException e) {
            exceptionThrown = true;
        }
        assertTrue("Exception should be thrown", exceptionThrown);
    }

    /**
     * test the nextIndex method when the iterator is at the tail
     */
    @Test
    public void testNextIndex() {
        // set the iterator to the tail
        listLen3Iter.left = listLen3.tail.getPrev();
        listLen3Iter.right = listLen3.tail;
        listLen3Iter.idx = 3;
        listLen3Iter.forward = true;
        listLen3Iter.canRemoveOrSet = true;
        assertEquals(3, listLen3Iter.nextIndex()); 
        
        // go back 1 idx
        listLen3Iter.previous();
        assertEquals(2, listLen3Iter.nextIndex());

        // go back to idx 1
        listLen3Iter.previous();
        assertEquals(1, listLen3Iter.nextIndex());

        // go back to idx 0
        listLen3Iter.previous();
        assertEquals(0, listLen3Iter.nextIndex());
    }

    /**
     * test the previousIndex method when we are at the head and there is 
     * no previous index
     */
    @Test
    public void testPreviousIndex() {
        // set list to head
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.forward = true;
        listLen3Iter.canRemoveOrSet = true;
        assertEquals(-1, listLen3Iter.previousIndex());

        // go to idx 1
        listLen3Iter.next();
        assertEquals(0, listLen3Iter.previousIndex());

        // go to idx 2
        listLen3Iter.next();
        assertEquals(1, listLen3Iter.previousIndex());

        // go to idx 3
        listLen3Iter.next();
        assertEquals(2, listLen3Iter.previousIndex());
    }

    /**
     * test the set method with different scenarios
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
     * test the remove method when the iterator is on the tail and forward is 
     * false
     */
    @Test
    public void testRemoveTestOne() {
        listLen3Iter.left = listLen3.tail.getPrev();
        listLen3Iter.right = listLen3.tail;
        listLen3Iter.idx = 3;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = true;
        try {
            listLen3Iter.remove();
        }
        catch (IllegalStateException e) {
            exceptionThrown = true;
        }
        assertTrue("Exception should be thrown", exceptionThrown);
    }

    /**
     * test the remove method when the iterator is on head and both forward and
     * canRemoveOrSet are true
     */
    @Test
    public void testRemoveTestTwo() {   
        // set list to idx 2
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.forward = true;
        listLen3Iter.canRemoveOrSet = true;
        try {
            listLen3Iter.remove();
        }
        catch(IllegalStateException e) {
            exceptionThrown = true;
        }
        assertTrue("Exception should be thrown", exceptionThrown);
        
                
    }

    /**
     * test the add method when we are at the tail
     */
    @Test
    public void testAdd() {
        listLen3Iter.left = listLen3.tail.getPrev();
        listLen3Iter.right = listLen3.tail;
        listLen3Iter.idx = 3;
        listLen3Iter.forward = false;
        listLen3Iter.canRemoveOrSet = false;
        listLen3Iter.add("this string was added!!!");
        assertEquals("this string was added!!!", 
            listLen3.tail.getPrev().getElement());

        assertFalse("canRemoveOrSet should be false", 
            listLen3Iter.canRemoveOrSet);

    }

}