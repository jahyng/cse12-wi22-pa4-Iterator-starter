
/**
 * TODO: Add your file header
 * Name:
 * ID:
 * Email:
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * TODO: Add your class header
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
     * TODO: test the hasNext method when [fill in a possible edge case here]
     */
    @Test
    public void testHasNext() {

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
     * TODO: test the previousIndex method when [...]
     */
    @Test
    public void testPreviousIndex() {

    }

    /**
     * TODO: test the set method when [...]
     */
    @Test
    public void testSet() {

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
        catch(NoSuchElementException e) {
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