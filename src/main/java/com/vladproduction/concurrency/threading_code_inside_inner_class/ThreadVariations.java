package com.vladproduction.concurrency.threading_code_inside_inner_class;

import java.util.concurrent.TimeUnit;

public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}

/**
 * using named inner class
 */
class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return getName() + " : " + countDown;
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}

/**
 * using anonymous inner class
 */
class InnerThread2 {
    private int countDown = 5;
    private Thread t;

    public InnerThread2(String name) {
        t = new Thread(name) {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return getName() + " : " + countDown;
            }
        };
        t.start();
    }
}

/**
 * using named implementation of Runnable
 */
class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner implements Runnable {
        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }

        @Override
        public void run() {
            try{
                while (true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            }catch (InterruptedException e){
                System.out.println("sleep() interrupted");
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return t.getName() + " : " + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

/**
 * using anonymous implementation of Runnable
 * */
class InnerRunnable2{
    private int countDown = 5;
    private Thread t;
    public InnerRunnable2(String name){
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                }catch (InterruptedException e){
                    System.out.println("sleep() interrupted");
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName() + " : " + countDown;
            }
        }, name);
        t.start();
    }
}

/**
 * separate method for executing code in kind of task
 * */
class ThreadMethod{
    private int countDown = 5;
    private Thread t;
    private String name;

    public ThreadMethod(String name){
        this.name = name;
    }

    public void runTask(){
        if(t == null){
            t = new Thread(name){
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (--countDown == 0) {
                                return;
                            }
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("sleep() interrupted");
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString() {
                    return getName() + " : " + countDown;
                }
            };
            t.start();
        }
    }
}

























