package ru.geekbrains.java3_work4_Threads_3_1;

public class Main {

    public static void main(String[] args) {
        //Task 1
//        PrintChar printChar = new PrintChar();
//        new Thread(()-> printChar.printA()).start();
//        new Thread(()-> printChar.printB()).start();
//        new Thread(()-> printChar.printC()).start();

        //Task 2
        MultiFunctionDevice mfd = new MultiFunctionDevice();
        new Thread(()-> {
            User user1 = new User("Миша");
            try {
            mfd.print(user1.getName());
                Thread.sleep(1000);
            mfd.xerox(user1.getName());
                Thread.sleep(1000);
            mfd.scan(user1.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            User user2 = new User("Аня");
            try {
                mfd.xerox(user2.getName());
                Thread.sleep(1000);
                mfd.xerox(user2.getName());
                Thread.sleep(1000);
                mfd.scan(user2.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            User user3 = new User("Дима");
            try {
                mfd.scan(user3.getName());
                Thread.sleep(1000);
                mfd.scan(user3.getName());
                Thread.sleep(1000);
                mfd.xerox(user3.getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
