package by.Threads.Condition;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public final class BoundedBuffer<T> {
    private final T[] elements;
    private int size;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmty;

    @SuppressWarnings("uncheckend")
    public BoundedBuffer(final int capacity) {
        this.elements = (T[])new Object[capacity];
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmty = lock.newCondition();
    }
    private boolean isFull(){
        lock.lock();
        try {
            return this.size == this.elements.length;
        }finally {
            lock.unlock();
        }
    }
    public boolean isEmpty(){
        lock.lock();
        try {
            return size == 0;
        }finally {
            lock.unlock();
        }
    }
    public void put(final T element){
        lock.lock();
        try {
            while (isFull()){
                notFull.await();
            }
            this.elements[size] = element;
            size++;
            System.out.printf("%s was put in buffer.Result buffer:%s%n",element,this);
            notEmty.signal();
        }catch (final InterruptedException interruptedException){
            Thread.currentThread().interrupt();
            throw new RuntimeException(interruptedException);
        }
        finally {
            lock.unlock();
        }
    }
    public T take(){
        lock.lock();
        try {
            while (isEmpty()){
                notEmty.await();
            }
            final  T result = elements[size - 1];
            elements[size - 1] = null;
            size--;
            System.out.printf("%s was taken from buffer.Result buffer:%s%n",result,this);
            notFull.signal();
            return result;
        }catch (final InterruptedException interruptedException){
            Thread.currentThread().interrupt();
            throw  new RuntimeException(interruptedException);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return "{"+Arrays.stream(elements,0,size)
                    .map(Objects::toString)
                    .collect(Collectors.joining(","))+"}";
        }finally {
            lock.unlock();
        }
    }
}
