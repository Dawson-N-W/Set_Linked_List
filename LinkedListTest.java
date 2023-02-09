package Project_1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    LinkedList<Integer> list = new LinkedList<>();

    @BeforeEach
    void setUp() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
    }

    @AfterEach
    void tearDown() {
        list.clear();
    }


    @Test
    void add() {
        list.add(1); //should be ignored, 1 is already in set
        assertEquals(5, list.getSize());
        list.add(15);
        assertTrue(list.exists(15));
        assertEquals(6, list.getSize());
    }

    @Test
    void addFirst() {
        list.addFirst(9);
        assertEquals(6, list.getSize());
        assertTrue(list.exists(9));
    }

    @Test
    void addLast() {
        list.addLast(9);
        assertEquals(6, list.getSize());
        assertTrue(list.exists(9));
    }

    @Test
    void addIndex() {
        list.add(3, 10);
        assertEquals(6, list.getSize());
        assertTrue(list.exists(10));
        //list is now 1 2 3 10 4 5 after printing
    }

    @Test
    void addTime(){
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time to add 1000 elements: " + (end - start) + " milliseconds");
    }

    @Test
    void addTimeIndex(){
        //inputs 1000 elements into the middle of the list
        long start = System.currentTimeMillis();
        for(int i = 5, j = i - 3; i < 1000; i++, j++){
            list.add(j, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time to add 1000 elements at index: " + (end - start) + " milliseconds");
    }

    @Test
    void remove() {
        list.remove(1);
        assertEquals(4, list.getSize());
        assertFalse(list.exists(1));
        list.remove(5);
        list.remove(3);
        //only 2 and 4 should be left
        list.remove(9); //should be ignored, 9 is not in set
        assertEquals(2, list.getSize());
    }

    @Test
    void removeIndex(){
        list.removeIndex(2);
        assertEquals(4, list.getSize());
        //the element at index 2 that was removed was at value 3
        assertFalse(list.exists(3));
    }

    @Test
    void removeTime(){
        list.clear();
        for(int i = 0; i < 1000; i++){
            list.add(i);
        }
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            list.remove(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time to remove 1000 elements: " + (end - start) + " milliseconds");
    }

    @Test
    void removeTimeIndex(){
        list.clear();
        for(int i = 0; i < 1000; i++){
            list.add(i);
        }
        long start = System.currentTimeMillis();
        for(int i = 999; i >= 0; i--){
            list.removeIndex(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time to remove 1000 elements at index: " + (end - start) + " milliseconds");
    }

    @Test
    void exists() {
        assertTrue(list.exists(2));
        list.remove(2);
        assertFalse(list.exists(2));
    }

    @Test
    void timeExists(){
        long start = System.nanoTime();
        for(int i = 0; i < 1000; i++){
            list.exists(i);
        }
        long end = System.nanoTime();
        System.out.println("Time to check 1000 elements: " + (end - start) + " nanoseconds");
    }

    @Test
    void getNode() {
        Node<Integer> node = new Node<>(100);
        list.addNode(node);
        assertEquals(node, list.getNode(100));
        assertNotEquals(node, list.getNode(4));
    }


    @Test
    void timeGetNode(){
        list.clear();
        for(int i = 0; i < 1000; i++){
            list.add(i);
        }
        long startTime = System.nanoTime();
        Node<Integer> node = list.getNode(500);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time to get node: " + duration + " nanoseconds");
    }

    @Test
    void getSize() {
        assertEquals(5, list.getSize());
    }

    @Test
    void timeGetSize(){
        long startTime = System.nanoTime();
        list.getSize();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Time to get size: " + duration + " nanoseconds");
    }

    @Test
    void intersection() {
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        assertEquals(3, list.intersection(list, list2).getSize()); //should be 1, 2, 3

        list2.remove(1);
        list2.remove(2);
        assertEquals(1, list.intersection(list, list2).getSize()); //only will have 1 element in common

        list2.remove(3);
        assertEquals(0, list.intersection(list, list2).getSize()); //no elements in common

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        assertEquals(5, list.intersection(list, list2).getSize()); //all elements in common

        list.clear();
        list2.clear();
        assertEquals(0, list.intersection(list, list2).getSize()); //empty lists

    }

    @Test
    void timeIntersection(){
        list.clear();
        LinkedList<Integer> list2 = new LinkedList<>();
        for(int i = 0; i < 1000; i++){
            list.add(i);
            list2.add(i);
        }
        long start = System.currentTimeMillis();
        list.intersection(list, list2);
        long end = System.currentTimeMillis();
        System.out.println("Time to run intersection with 1000 items: " + (end - start) + " milliseconds");
    }

    @Test
    void union(){
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        assertEquals(5, list.union(list, list2).getSize());

        list2.remove(1);
        list2.remove(2);
        list2.remove(3);
        assertEquals(5, list.union(list, list2).getSize());

        list.clear();
        list2.clear();
        assertEquals(0, list.union(list, list2).getSize());
    }

    @Test
    void timeUnion(){
        list.clear();
        LinkedList<Integer> list2 = new LinkedList<>();
        for(int i = 0; i < 1000; i++){
            list.add(i);
            list2.add(i);
        }
        long start = System.currentTimeMillis();
        list.union(list, list2);
        long end = System.currentTimeMillis();
        System.out.println("Time to run union with 1000 items: " + (end - start) + " milliseconds");

    }

    @Test
    void isEmpty(){
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void cloneNode(){
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.cloneNode(list.getNode(2));
        assertEquals(2, list2.getNode(2).data);
    }






}