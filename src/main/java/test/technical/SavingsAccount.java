package test.technical;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SavingsAccount extends Account {
    private double balance;

    public SavingsAccount(int accountID) {
        super(accountID);
    }

    public double calculateTotalSavings(File file,String date) {
        ArrayList<Double> values = file.getSavingsAccountValues(super.getAccountID(),date);

        // calling superclass method
        return calculateNetBalance(values);
    }

    public void decrementBalance(double savingsBalance, double currentBalance, File file, String date) {
        double incrementValue = calculateIncrementValue(savingsBalance,currentBalance);
        //this.balance -= incrementValue;

        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));

        Transaction transaction = new Transaction(accountID,
                "SAVINGS",
                "SYSTEM",
                LocalDateTime.of(year,month,day,23,59,59),
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
