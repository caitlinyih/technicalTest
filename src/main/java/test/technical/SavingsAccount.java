package test.technical;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SavingsAccount extends Account {
    private double balance;

    public SavingsAccount(int accountID) {
        super(accountID);
    }

    public double calculateTotalSavings(File file) {
        ArrayList<Double> values = file.getSavingsAccountValues(super.getAccountID());

        // calling superclass method
        return calculateNetBalance(values);
    }

    public void decrementBalance(double savingsBalance, double currentBalance, File file) {
        double incrementValue = calculateIncrementValue(savingsBalance,currentBalance);
        this.balance -= incrementValue;

        Transaction transaction = new Transaction(accountID,
                "SAVINGS",
                "SYSTEM",
                LocalDateTime.now(),
                -incrementValue);
        file.writeTransactionRecord(transaction);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
