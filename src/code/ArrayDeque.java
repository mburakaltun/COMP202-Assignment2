package code;

/*
 * ASSIGNMENT 2
 * AUTHOR: Muhammed Burak Altun
 * Class : ArrayDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the Array Deque yourself
 *
 * MODIFY
 *
 * */

import given.iDeque;

import java.util.Arrays;
import java.util.Iterator;

import given.Util;


/*
 * You must have a circular implementation.
 *
 * WARNING: Modulo operator (%) might create issues with negative numbers.
 * Use Math.floorMod instead if you are having issues
 */
public class ArrayDeque<E> implements iDeque<E> {

    private E[] A; //Do not change this name!

    /*
     * ADD FIELDS IF NEEDED
     */

    private int size = 0;
    private int front = -1;
    private int rear = -1;

    public ArrayDeque() {
        this(1000);
        /*
         * ADD CODE IF NEEDED
         */
    }

    public ArrayDeque(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        A = createNewArrayWithSize(initialCapacity);

        /*
         * ADD CODE IF NEEDED
         */
    }

    // This is given to you for your convenience since creating arrays of generics is not straightforward in Java
    @SuppressWarnings({"unchecked"})
    private E[] createNewArrayWithSize(int size) {
        return (E[]) new Object[size];
    }

    //Modify this such that the dequeue prints from front to back!
    //Hint, after you implement the iterator, use that!
    public String toString() {

        /*
         * MODIFY THE BELOW CODE
         */
        if (size == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("[");
            Iterator<E> iter = this.iterator();
            while (iter.hasNext()) {
                E e = iter.next();
                if (e == null)
                    continue;
                sb.append(e);
                if (!iter.hasNext())
                    sb.append("]");
                else
                    sb.append(", ");
            }
            return sb.toString();
        }
    }

    /*
     * ADD METHODS IF NEEDED
     */

    /*
     * Below are the interface methods to be overriden
     */

    @Override
    public int size() {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        return size == 0;
    }

    @Override
    public void addFront(E o) {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        if (size == A.length) {
            E[] temp = createNewArrayWithSize(size * 2);
            for (int i = 0; i < size; i++) {
                temp[i + 1] = A[front];
                front = (front + 1) % size;
            }
            front = 0;
            rear = size;
            A = temp;
            A[front] = o;
        } else if (size == 0) {
            front = 0;
            rear = 0;
            A[front] = o;
        } else {
            front--;
            if (front < 0) {
                front += A.length;
            }
            A[front] = o;
        }
        size++;
    }

    @Override
    public E removeFront() {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        if (size == 0) {
            return null;
        } else {
            E element = A[front];
            A[front] = null;
            front = (front + 1) % A.length;
            size--;
            return element;
        }
    }

    @Override
    public E front() {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        if (size == 0) {
            return null;
        } else {
            return A[front];
        }
    }

    @Override
    public void addBehind(E o) {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        if (size == A.length) {
            E[] temp = createNewArrayWithSize(size * 2);
            for (int i = 0; i < size; i++) {
                temp[i] = A[front];
                front = (front + 1) % A.length;
            }
            front = 0;
            rear = size;
            A = temp;
            A[rear] = o;
        } else if (size == 0) {
            front = 0;
            rear = 0;
            A[rear] = o;
        } else {
            rear = (rear + 1) % A.length;
            A[rear] = o;
        }
        size++;
    }

    @Override
    public E removeBehind() {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        if (size == 0) {
            return null;
        } else {
            E element = A[rear];
            A[rear] = null;
            rear--;
            if (rear < 0) {
                rear += A.length;
            }
            size--;
            return element;
        }
    }

    @Override
    public E behind() {
        // TODO Auto-generated method stub
        //Util.NotImplementedYetSoft();
        if (size == 0) {
            return null;
        } else {
            return A[rear];
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                A[front] = null;
                front = (front + 1) % size;
            }
        }

        front = -1;
        rear = -1;
        size = 0;
    }

    //Must print from front to back
    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        //Hint: Fill in the ArrayDequeIterator given below and return a new instance of it
        return new ArrayDequeIterator();
    }

    private final class ArrayDequeIterator implements Iterator<E> {

        /*
         *
         * ADD A CONSTRUCTOR IF NEEDED
         * Note that you can freely access everything about the outer class!
         *
         */
        private int tempFront;

        public ArrayDequeIterator() {
            tempFront = front;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return A[tempFront] != null;
        }

        @Override
        public E next() {
            // TODO Auto-generated method stub
            if (hasNext()) {
                E element = A[tempFront];
                tempFront = (tempFront + 1) % A.length;
                return element;
            }
            return null;
        }
    }
}
