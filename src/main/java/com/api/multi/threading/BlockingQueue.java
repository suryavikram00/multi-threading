/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.multi.threading;

import java.util.Queue;

/**
 *
 * @author NMSLAP570
 */
public class BlockingQueue<T> {

    private T[] array;
    private int size = 0;
    private int capacity;
    private int head = 0;
    private int tail = 0;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public static void main(String args[]) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);
        
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        queue.enqueue(new Integer(i));
                        System.out.println("enqueued " + i);
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("Thread 2 dequeued: " + queue.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("Thread 3 dequeued: " + queue.dequeue());
                    }
                } catch (InterruptedException ie) {

                }
            }
        });

        t1.start();
        Thread.sleep(4000);
        t2.start();

        t2.join();

        t3.start();
        t1.join();
        t3.join();
        
    }

    private synchronized void enqueue(T item) throws InterruptedException {
        while (size == capacity) {
            // here we need to wait
            wait();            
        }

        if (tail == capacity) {
            tail = 0;
        }

        array[tail] = item;
        size++;
        tail++;
//        System.out.println("enqueued :: " + item);
        notifyAll();
    }

    private synchronized T dequeue() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        // reset head to start of array if its past the array
        if (head == capacity) {
            head = 0;
        }
        T item = array[head];
        array[head] = null;
        head++;
        size--;
        
//        System.out.println("Dequeued :: " + item);
        notifyAll();
        return item;
    }

}
