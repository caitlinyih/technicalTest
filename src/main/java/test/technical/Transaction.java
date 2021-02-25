package test.technical;

import java.time.LocalDateTime;

public class Transaction {
    private int accountID;
    private String accountType;
    private String initiatorType;
    private LocalDateTime dateTime;
    private double transactionValue;

    public Transaction(int accountID, String accountType, String initiatorType, LocalDateTime dateTime, double transactionValue) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.initiatorType = initiatorType;
        this.dateTime = dateTime;
        this.transactionValue = transactionValue;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getInitiatorType() {
        return initiatorType;
    }

    public void setInitiatorType(String initiatorType) {
        this.initiatorType = initiatorType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }
}
