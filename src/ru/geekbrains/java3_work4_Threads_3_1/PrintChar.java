package ru.geekbrains.java3_work4_Threads_3_1;

public class PrintChar {
    private volatile char currentChar = 'A';
//    private final Object ob = new Object();

    public void printA() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'A') {
                        this.wait();
                    }
                    System.out.print("A");
                    currentChar = 'B';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printB() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'B') {
                        this.wait();
                    }
                    System.out.print("B");
                    currentChar = 'C';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printC() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar != 'C') {
                        this.wait();
                    }
                    System.out.print("C_");
                    currentChar = 'A';
                    this.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
