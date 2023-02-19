package tqs.lab1;

import java.util.LinkedList;

public class TqsStack <T> {
    private LinkedList<T> stack;
    private int capacity = -1;

    public TqsStack() {
        this.stack = new LinkedList<T>();
    }

    public TqsStack(int capacity) {
        this.stack = new LinkedList<>();
        this.capacity = capacity;
    }

    public T pop() {
        return this.stack.removeLast();
    }

    public int size() {
        return this.stack.size();
    }

    public T peek() {
        return this.stack.getLast();
    }

    public void push(T t) {
        if ((this.capacity != -1) & (this.size() == this.capacity)) {
            throw new IllegalStateException("Stack is full!");
        } else {
            this.stack.addLast(t);
        }
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
