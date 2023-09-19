/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.multi.threading;

/**
 *
 * @author NMSLAP570
 */
public class MultiThreading {
    

    public static void main(String args[]) throws InterruptedException {

        MultiThreading multiThreading = new MultiThreading();

        Thread threadOne = new CountIncrementThread();
        Thread threadTwo = new CountIncrementThread();
        
        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("Output count :: " + CountIncrementThread.count);

    }

}
