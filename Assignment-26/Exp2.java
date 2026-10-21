package Exp26.Exercise;

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

class Deposit implements Runnable {
    Bank bank;

    Deposit(Bank bank) {
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

class Withdraw implements Runnable {
    Bank bank;

    Withdraw(Bank bank) {
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

        Thread deposit = new Thread(new Deposit(bank));
        Thread withdraw = new Thread(new Withdraw(bank));

        deposit.start();
        withdraw.start();
    }
}