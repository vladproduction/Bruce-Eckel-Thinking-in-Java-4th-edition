package com.vladproduction.concurrency.exercises;

/**
 * Create a class containing two data fields, and a method that manipulates
 * those fields in a multistep process so that, during the execution of that method, those fields
 * are in an "improper state" (according to some definition that you establish).
 * Add methods to read the fields, and create multiple threads to call the various methods and show that the
 * data is visible in its "improper state."
 * Fix the problem using the synchronized keyword.
 * */
public class Exercise11 {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Alice", 100); // 100 units of initial balance

        // Failure cases:
        Thread withdrawalThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bankAccount.withdraw(15); // Withdrawing 15 units
            }
        });

        Thread withdrawalThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bankAccount.withdraw(10); // Withdrawing 10 units
            }
        });

        withdrawalThread1.start();
        withdrawalThread2.start();

        try {
            withdrawalThread1.join();
            withdrawalThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final state after concurrent withdrawals
        System.out.println("Final state of bank account: " + bankAccount);
        
        // Fixing issues using synchronized methods
        BankAccount threadSafeAccount = new BankAccount("Alice", 100);

        Thread safeWithdrawalThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadSafeAccount.safeWithdraw(15); // Withdrawing 15 units
            }
        });

        Thread safeWithdrawalThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadSafeAccount.safeWithdraw(10); // Withdrawing 10 units
            }
        });

        safeWithdrawalThread1.start();
        safeWithdrawalThread2.start();

        try {
            safeWithdrawalThread1.join();
            safeWithdrawalThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final state of the thread-safe account
        System.out.println("Final state of safe bank account: " + threadSafeAccount);
    }
}

class BankAccount {
    private final String accountHolder;
    private int balance;

    public BankAccount(String accountHolder, int balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Non-synchronized method leading to improper state
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        // Simulating a delay to increase likelihood of a race condition
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Withdraw without checking for overdraft
        balance -= amount; // This can lead to an improper state if balance goes negative
        System.out.println("Withdrew: " + amount + ", New balance: " + balance);
    }

    // Synchronized method to prevent race condition
    public synchronized void safeWithdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        
        if (balance < amount) {
            System.out.println("Insufficient funds for withdrawal of: " + amount);
            return; // Prevent overdraft
        }

        // Simulate processing time
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        balance -= amount; // Safe withdrawal
        System.out.println("Safely withdrew: " + amount + ", New balance: " + balance);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                '}';
    }
}
