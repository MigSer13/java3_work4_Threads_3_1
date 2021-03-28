package ru.geekbrains.java3_work4_Threads_3_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiFunctionDevice {
    private enum Action {SCAN, PRINT, XEROX}
    private volatile Set<Action> actions = new HashSet(3);

    public  void scan(User user)
    {
        synchronized(Action.SCAN){
        System.out.println(user.getName() + " хочет отсканировать документ");
        try {
            while (actions.contains(Action.XEROX)) {
                System.out.println(user.getName() + " ожидает сканирование ");
                Action.SCAN.wait();
            }
            actions.add(Action.SCAN);
            System.out.println("Выполняется сканирование " + "("+user.getName()+")");
            Thread.sleep(3000);
            actions.remove(Action.SCAN);
            System.out.println("Cканирование закончено " + "("+user.getName()+")");
            Action.SCAN.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    }

    public synchronized void print(User user)
    {
        synchronized(Action.PRINT){
        System.out.println(user.getName() + " хочет распечатать документ");
        try {
            while (actions.contains(Action.XEROX)) {
                System.out.println(user.getName() + " ожидает печать");
                Action.PRINT.wait();
            }
            actions.add(Action.PRINT);
            System.out.println("Выполняется печать " + "("+user.getName()+")");
            Thread.sleep(3000);
            actions.remove(Action.PRINT);
            System.out.println("Печать закончена " + "("+user.getName()+")");
            Action.PRINT.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } }
}

    public synchronized void xerox(User user)
    {
        synchronized(this){
        System.out.println(user.getName() + " хочет отксерокопировать документ");
        try {
            while (actions.contains(Action.PRINT) || actions.contains(Action.SCAN) ) {
                System.out.println(user.getName() + " ожидает ксерокопирование");
                this.wait();
            }
            actions.add(Action.XEROX);
            System.out.println("Выполняется ксерокопирование" + "("+user.getName()+")");
            Thread.sleep(3000);
            actions.remove(Action.XEROX);
            System.out.println("Ксерокопирование закончено" + "("+user.getName()+")");
            this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}
