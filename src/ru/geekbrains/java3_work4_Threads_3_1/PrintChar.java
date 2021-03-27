package ru.geekbrains.java3_work4_Threads_3_1;

public class PrintChar {
    private volatile char currentChar = 'A';
    private final Object ob = new Object();

    public void printA() {
        synchronized (ob) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'A') {
                        ob.wait();
                    }
                    System.out.print("A");
                    currentChar = 'B';
                    ob.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printB() {
        synchronized (ob) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'B') {
                        ob.wait();
                    }
                    System.out.print("B");
                    currentChar = 'C';
                    ob.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printC() {
        synchronized (ob) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'C') {
                        ob.wait();
                    }
                    System.out.print("C_");
                    currentChar = 'A';
                    ob.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
