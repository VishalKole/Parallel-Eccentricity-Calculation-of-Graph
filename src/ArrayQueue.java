//******************************************************************************
//
// File:    ArrayQueue.java
//
// This Java source file is part of the parallel programming assignment 2 for the
// partial completion of the coursework
//
//******************************************************************************

/**
 * ArrayQueue is a custom implementation of the queue for the Graph.
 * It is to save computations and time to search
 * <p>
 *
 * @author Vishal Kole (vvk3025@rit.edu)
 * @version 15-October-2017
 */
public class ArrayQueue {

    int size = 0, front = 0, back = 0,  arr_length;
    int arr[];

    /**
     * constructor to initialize
     * @param size size of the array to create
     */
    ArrayQueue(int size) {
        this.arr = new int[size];
        this.arr_length = size;
    }

    /**
     * adding an element to the queue
     * @param element element to add
     */
    public void add(int element) {
        arr[back++] = element;
        ++size;
        if (back >= arr_length)
            back = 0;
    }

    /**
     * clear all the parameters to use for
     */
    public void clear() {
        this.size = 0;
        front = 0;
        back = -1;
    }

    /**
     * removes the first item from the queue and returns the item
     * @return int element on top
     */
    public int poll() {
        --size;
        int ret = this.arr[front++];
        if (front >= arr_length)
            front = 0;
        return ret;
    }

    /**
     * check if the array is empty
     * @return true if the array is empty
     */
    public boolean isEmpty(){
        return size==0;
    }



}
