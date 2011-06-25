package com.bot.util.queue;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Vector;

public class SimpleQueue<T> {
    private Vector<T> vector = new Vector<T>();
    private boolean stopWaiting = false;
    private boolean waiting = false;

    /**
     * Add an object into the queue
     *
     * @param t object to be added into the queue
     */
    public synchronized void put(T t) {
        vector.addElement(t);
        notify();
    }

    /**
     * Breaks pull();
     */
    public synchronized void stop() {
        stopWaiting = true;
        if (waiting) notify();
    }

    /**
     * Gets the first item queued in the list
     *
     * @return return first item in queue
     */
    public synchronized T pull() {
        while (isEmpty()) {
            try {
                waiting = true;
                wait();
            } catch (InterruptedException ignored) { }
            waiting = false;
            if (stopWaiting) return null;
        }
        return get();
    }

    /**
     * Gets the first item in queue; returns null if the queue is empty;
     * @return t
     */
    public synchronized T get() {
        T t = peek();
        if (t != null)
            vector.removeElementAt(0);
        return t;
    }

    /**
     * "Peek" to see if anything is available
     * @return t
     */
    public T peek() {
        if (isEmpty())
            return null;
        return vector.elementAt(0);
    }

    /**
     * Checks if the queue is empty
     * @return true if queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Returns the size of the queue
     * @return size of vector
     */
    public int size() {
        return vector.size();
    }
}
