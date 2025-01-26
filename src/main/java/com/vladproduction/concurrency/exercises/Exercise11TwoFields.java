package com.vladproduction.concurrency.exercises;

/**
 * Create a class with two data fields and a method that operates on those fields in several steps,
 * so that during the method the fields are in an "incorrect state" (by whatever definition you set).
 * Add methods to read the fields; create several threads in which these methods will be called,
 * and indicate that the data is in an incorrect state.
 *
 * Solve the problem with the synchronized keyword.
 * To prevent data races and ensure consistency in operations, we should synchronize the operate() method as well.
 * This way, only one thread can execute it at a time, which prevents the fields from being left in an "incorrect state" during the manipulation
 * */
public class Exercise11TwoFields {
    private int field1;
    private int field2;

    public Exercise11TwoFields(int field1, int field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    // Method that operates on fields and can be in an incorrect state
    // solution: place synchronized keyword for this method as well
    // ensure that only one thread can modify the fields at a time, thus maintaining consistency and preventing data races
    public synchronized void operate(){
        //step #1: incorrect state
        field1 += 5;
        System.out.println("Field 1 increment: " + field1);

        //delay ot increase chance to catch race condition:
        try{
            Thread.sleep(200);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //step #2: incorrect state
        field2 *= 2;
        System.out.println("Field 2 increment: " + field2);

        //step #3: final state
        int result = field1 + field2;
        System.out.println("Final result: " + result);
    }

    //synchronized method to get a final result for fields reading safely:
    public synchronized void readFields(){
        System.out.println("Read Fields: " + "Field#1: " + field1 + " Field#2: " + field2);
    }
}

class ThreadExample{
    public static void main(String[] args) {
        Exercise11TwoFields example = new Exercise11TwoFields(10, 20);

        //create two threads that operate the shared data:
        Thread thread1 = new Thread(()->{
            example.operate();
            example.readFields();

        });

        Thread thread2 = new Thread(()->{
            example.operate();
            example.readFields();
        });

        //starting threads
        thread1.start();
        thread2.start();

        //waiting threads finish jobs
        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}





















