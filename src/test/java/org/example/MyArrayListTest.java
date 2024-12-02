package org.example;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void testAdd() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 1000; i++){
            list.add(i);
        }
        assertEquals(1000, list.size());
        assertEquals(Integer.valueOf(0),list.get(0));
        assertEquals(Integer.valueOf(999),list.get(999));
    }

    @Test
    public void testAddAtIndex() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1, 3);
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
                assertEquals(Integer.valueOf(2), list.get(2));
    }

    @Test
    public void testAddAtIndexBig () {
    MyArrayList<Integer> list = new MyArrayList<>();
    for (int i = 0; i < 1000; i++) {
        list.add(0, i);
    }
        assertEquals(1000, list.size());
    }

    @Test
    public void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionAdd() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1,1);
    }

    @Test
    public void testClear(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testSortComparable() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort();
        assertArrayEquals(new Integer[]{1, 2, 3}, Arrays.copyOfRange(list.array,0,list.size()));
    }
    @Test
    public void testSet(){
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.set(0,3);
        assertEquals(Integer.valueOf(3), list.get(0));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSortWithoutComparable(){
        MyArrayList<Object> list = new MyArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.sort();
    }
}