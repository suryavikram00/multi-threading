/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.multi.threading;

/**
 *
 * @author NMSLAP570
 */
public class CountIncrementThread extends Thread {

    private final Object lock = new Object();

    public static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (CountIncrementThread.class) {
                count++;
            }
        }
    }

}
