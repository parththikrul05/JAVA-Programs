package Exp25.Exercise;

class Bank {
    int balance = 1000;

    void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Balance: " + balance);
    }

    void withdraw(int amount) {
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
        System.out.println("Balance: " + balance);
    }
}

class DepositThread extends Thread {
    Bank bank;

    DepositThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            bank.deposit(500);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class WithdrawThread extends Thread {
    Bank bank;

    WithdrawThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            bank.withdraw(200);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class Exp2 {
    public static void main(String[] args) {
        Bank bank = new Bank();

        DepositThread deposit = new DepositThread(bank);
        WithdrawThread withdraw = new WithdrawThread(bank);

        deposit.start();
        withdraw.start();
    }
}