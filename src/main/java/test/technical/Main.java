package test.technical;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // take user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter File Path: ");
        String filePath = scanner.next();
        scanner.close();

        File file = new File(filePath);

        int currentAccountID = file.getCurrentAccountID();
        int savingsAccountID = file.getSavingsAccountID();

        CurrentAccount currentAccount = new CurrentAccount(currentAccountID);
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccountID);

        ArrayList<String> transactionDates = file.getDates();

        for (String date : transactionDates) {
            double currentBalance = currentAccount.calculateNetCurrentBalance(file,date);
            currentAccount.setBalance(currentBalance);

            double savingsBalance = savingsAccount.calculateTotalSavings(file,date);
            savingsAccount.setBalance(savingsBalance);

            // only fires if current acc deficit
            if (currentAccount.getBalance()<0 && savingsAccount.getBalance()!=0) {
                currentAccount.incrementBalance(savingsBalance,currentBalance,file,date);
                savingsAccount.decrementBalance(savingsBalance,currentBalance,file,date);

                System.out.println("ADJUSTMENTS MADE");
                file.printAdjustments();
            } else if (currentAccount.getBalance()<0 && savingsAccount.getBalance()==0) {
                System.out.println("NO SAVINGS");
            } else {
                System.out.println("NO CURRENT ACCOUNT DEFICIT");
            }
        }
    }
}