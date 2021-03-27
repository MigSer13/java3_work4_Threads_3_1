package ru.geekbrains.java3_work4_Threads_3_1;

public class Main {

    public static void main(String[] args) {
        //Task 1
        PrintChar printChar = new PrintChar();
        new Thread(()-> printChar.printA()).start();
        new Thread(()-> printChar.printB()).start();
        new Thread(()-> printChar.printC()).start();
    }
}
