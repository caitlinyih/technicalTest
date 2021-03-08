package test.technical;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CurrentAccount extends Account {
    private double balance;

    public CurrentAccount(int accountID) {
        super(accountID);
    }

    public double calculateNetCurrentBalance(File file, String date) {
        ArrayList<Double> values = file.getCurrentAccountValues(super.accountID,date);
        return calculateNetBalance(values);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void incrementBalance(double savingsBalance, double currentBalance, File file, String date) {
        double incrementValue = calculateIncrementValue(savingsBalance,currentBalance);
        //this.balance += incrementValue;  // method of parent class that calculated the increment value

        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        int day = Integer.parseInt(date.substring(8,10));

        Transaction transaction = new Transaction(accountID,
                "CURRENT",
                "SYSTEM",
                LocalDateTime.of(year,month,day,23,59,59),
                incrementValue);
        file.writeTransactionRecord(transaction);
    }
}
