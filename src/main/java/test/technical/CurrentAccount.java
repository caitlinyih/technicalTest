package test.technical;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CurrentAccount extends Account {
    private double balance;

    public CurrentAccount(int accountID) {
        super(accountID);
    }

    public double calculateNetCurrentBalance(File file) {
        ArrayList<Double> values = file.getCurrentAccountValues(super.accountID);
        return calculateNetBalance(values);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void incrementBalance(double savingsBalance, double currentBalance, File file) {
        double incrementValue = calculateIncrementValue(savingsBalance,currentBalance);
        this.balance += incrementValue;  // method of parent class that calculated the increment value

        Transaction transaction = new Transaction(accountID,
                "CURRENT",
                "SYSTEM",
                LocalDateTime.now(),
                incrementValue);
        file.writeTransactionRecord(transaction);
    }
}
