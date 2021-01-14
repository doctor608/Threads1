package com.company;

import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        new Thread(refill).start();
        //System.out.printf("Счет пополнен на %d рублей. Текущий счет - %d рублей\n", money, cash);
        new Thread(withdrawal).start();
        //System.out.printf("Со счета сняли %d рублей. Текущий счет - %d рублей\n", money, cash);
    }

    private volatile boolean flag = true;
    private volatile int cash = 0;
    private volatile int money;
    Random random = new Random();

    Runnable refill = new Runnable() {
        @Override
        public void run() {
            while (flag) {

                try {
                    Thread.sleep(2000);
                    cash = random.nextInt(100) + 1;
                    money += cash;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Текущий счет " + money);
            }

        }
    };

    Runnable withdrawal = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                cash = random.nextInt(100) + 1;
                if (money < cash) {
                    System.out.println("Ошибка снятия");
                } else {
                    money -= cash;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Текущий счет " + money);
        }
    };
}
