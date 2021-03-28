package ru.geekbrains.java3_work4_Threads_3_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiFunctionDevice {
    private enum Action {SCAN, PRINT, XEROX}

    private volatile Set<Action> actions = new HashSet(3);

    public  void scan(String userName)
    {
        synchronized(Action.XEROX){
        System.out.println(userName + " хочет отсканировать документ");
        try {
            while (actions.contains(Action.XEROX)) {
                System.out.println(userName + " ожидает сканирование ");
                Action.XEROX.wait();
            }
            actions.add(Action.SCAN);
            System.out.println("Выполняется сканирование " + "("+userName+")");
            Thread.sleep(3000);
            actions.remove(Action.SCAN);
            System.out.println("Cканирование закончено " + "("+userName+")");
            Action.XEROX.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    }

    public synchronized void print(String userName)
    {
        synchronized(Action.XEROX){
        System.out.println(userName + " хочет распечатать документ");
        try {
            while (actions.contains(Action.XEROX)) {
                System.out.println(userName + " ожидает печать");
                Action.XEROX.wait();
            }
            actions.add(Action.PRINT);
            System.out.println("Выполняется печать " + "("+userName+")");
            Thread.sleep(3000);
            actions.remove(Action.PRINT);
            System.out.println("Печать закончена " + "("+userName+")");
            Action.XEROX.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}

    public synchronized void xerox(String userName)
    {
        synchronized(Action.PRINT){
        System.out.println(userName + " хочет отксерокопировать документ");
        try {
            while (actions.contains(Action.PRINT)) {
                System.out.println(userName + " ожидает ксерокопирование");
                Action.PRINT.wait();
            }
            actions.add(Action.XEROX);
            System.out.println("Выполняется ксерокопирование" + "("+userName+")");
            Thread.sleep(3000);
            actions.remove(Action.XEROX);
            System.out.println("Ксерокопирование закончено" + "("+userName+")");
            Action.PRINT.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}
}
