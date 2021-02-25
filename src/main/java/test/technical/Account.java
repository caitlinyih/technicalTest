package test.technical;

import java.util.ArrayList;

public class Account {
    protected int accountID;

    public Account(int accountID) {
        this.accountID = accountID;
    }

    public int getAccountID() {
        return accountID;
    }

    public double calculateNetBalance(ArrayList<Double> values) {
        double netBalance = 0;
        for (Double val : values) {
            netBalance += val;
        }
        netBalance = Math.round(netBalance*100.0)/100.0;    // 100.0 not 100!!!
        return netBalance;
    }

    public double calculateIncrementValue(double savingsBalance, double currentBalance) {
        double incrementValue;
        if (savingsBalance > Math.abs(currentBalance)) {  // absolute of deficit amount
            incrementValue = Math.abs(currentBalance);
        } else {
            incrementValue = savingsBalance;    // increment as much as possible taking savings to 0
        }

        return incrementValue;
    }
}