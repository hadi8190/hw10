package org.example;

public class Main {
    public static void main(String[] args) {

                Object resource1 = new Object();
                Object resource2 = new Object();


                Thread t1 = new Thread(() -> {
                    synchronized (resource1) {
                        System.out.println("Thread 1");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread 1: Waiting for resource 2...");
                        synchronized (resource2) {
                            System.out.println("Thread 1: Holding");
                        }
                    }
                });


                Thread t2 = new Thread(() -> {
                    synchronized (resource2) {
                        System.out.println("Thread 2");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread 2: Waiting for resource 1...");
                        synchronized (resource1) {
                            System.out.println("Thread 2: Holding");
                        }
                    }
                });

                t1.start();
                t2.start();


                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }