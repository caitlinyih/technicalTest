package test.technical;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

public class File {
    // private for encapsulation? --> OO design
    private String file;

    public File(String fileName) {
        this.file = fileName;
    }

    // private scope for methods used only as helper methods by class public methods
    private List<String[]> generateTransactionRecords() {
        try {
            CSVReader reader = new CSVReader(new FileReader(this.file));
            List<String[]> transactions = reader.readAll();     // .readAll() return type List<> interface
            reader.close();

            return transactions;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (CsvException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Can't read CSV file!");
        }

        return null;   // should never reach
    }

    private List<String[]> generateDailyTransactionRecords(String date) {
        List<String[]> transactions = generateTransactionRecords();
        ArrayList<String[]> dailyTransactions = new ArrayList<>();

        for (int i=1; i<transactions.size(); i++) {
            if (isValid(transactions.get(i))) {
                String transactionDateTime = transactions.get(i)[3];
                String transactionDate = transactionDateTime.substring(0, 10);
                if (transactionDate.equals(date)) {
                    dailyTransactions.add(transactions.get(i));
                }
            }
        }

        return dailyTransactions;
    }

    private boolean isValid(String[] transaction) {
        if (transaction.length == 5) {
            return true;
        }
        return false;   // not a valid transaction; missing information or blank row
    }

    public int getCurrentAccountID() {
        List<String[]> transactions = generateTransactionRecords();

        for (String[] transaction : transactions) {     // could start at i=1 to skip header row but no need
            if (isValid(transaction)) {
                if (transaction[1].equals("CURRENT")) {
                    return Integer.parseInt(transaction[0]);
                }
            } else {
                continue;
            }
        }

        return 0;
    }

    public int getSavingsAccountID() {
        List<String[]> transactions = generateTransactionRecords();

        for (String[] transaction : transactions) {     // could start at i=1 to skip header row but no need
            if (isValid(transaction)) {
                if (transaction[1].equals("SAVINGS")) {
                    return Integer.parseInt(transaction[0]);
                }
            } else {
                continue;
            }
        }

        return 0;
    }

    public ArrayList<Double> getCurrentAccountValues(Integer accountID,String date) {
        ArrayList<Double> currentAccountValues = new ArrayList<Double>();
        List<String[]> transactions = generateDailyTransactionRecords(date);

        for (String[] transaction : transactions) {
            if (transaction[0].equals(accountID.toString())) {
                double transactionValue = Double.parseDouble(transaction[4]);
                currentAccountValues.add(transactionValue);
            }
        }

        return currentAccountValues;
    }

    public ArrayList<Double> getSavingsAccountValues(Integer accountID,String date) {
        ArrayList<Double> savingsAccountValues = new ArrayList<Double>();
        List<String[]> transactions = generateDailyTransactionRecords(date);

        for (String[] transaction : transactions) {
            if (transaction[0].equals(accountID.toString())) {
                double transactionValue = Double.parseDouble(transaction[4]);
                savingsAccountValues.add(transactionValue);
            }
        }

        return savingsAccountValues;
    }

    public void writeTransactionRecord(Transaction transaction) {
        try {
            // public CSVWriter(Writer writer, char separator, char quotechar, char escapechar, String lineEnd)
            // initially just had the Writer param arg, but this left quotation marks in the CSV file entries
            CSVWriter writer = new CSVWriter(new FileWriter(file, true), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            String accountID = Integer.toString(transaction.getAccountID());
            String accountType = transaction.getAccountType();
            String initiatorType = transaction.getInitiatorType();
            String dateTime = transaction.getDateTime().toString();
            String transactionValue = Double.toString(transaction.getTransactionValue());

            String transactionString = accountID + "," + accountType + "," + initiatorType + "," + dateTime + "," + transactionValue;
            String[] transactionWrite = transactionString.split(",");

            writer.writeNext(transactionWrite);
            writer.close();
        } catch (IOException e) {
            System.out.println("CSV file can not be opened. Check that it exists and is not a directory.");
        }
    }

    public void printAdjustments() {
        List<String[]> transactions = generateTransactionRecords();

        String[] header = transactions.get(0);
        String[] currentAdjustment = transactions.get(transactions.size()-2);
        String[] savingsAdjustment = transactions.get(transactions.size()-1);

        for (int i=0; i<header.length; i++) {
            System.out.print(currentAdjustment[i] + " ");
        }

        System.out.println();

        for (int i=0; i<header.length; i++) {
            System.out.print(savingsAdjustment[i] + " ");
        }
    }

    private int getFirstValidTransactionIndex(List<String[]> transactions) {
        for (int i=1; i<transactions.size(); i++) {     // skip header
            if (isValid(transactions.get(i))) {
                return i;
            }
        }
        return 0;
    }

    private String extractDate(String dateTime) {
        return dateTime.substring(0,10);    // first 10 chars i.e. the date
    }

    private String getInitialDate(List<String[]> transactions) {
        int firstValidTransactionIndex = getFirstValidTransactionIndex(transactions);
        String initialDateTime = transactions.get(firstValidTransactionIndex)[3];
        String initialDate = extractDate(initialDateTime);

        return initialDate;
    }

    public ArrayList<String> getDates() {
        List<String[]> transactions = generateTransactionRecords();
        ArrayList<String> dates = new ArrayList<>();

        String initialDate = getInitialDate(transactions);

        dates.add(initialDate);

        for (int i=1; i<transactions.size(); i++) {
            if (isValid(transactions.get(i))) {
                String transactionDateTime = transactions.get(i)[3];
                String transactionDate = extractDate(transactionDateTime);
                if (!transactionDate.equals(initialDate)) {
                    dates.add(transactionDate);
                    initialDate = transactionDate;
                }
            }
        }

        return dates;
    }
}