package test.technical;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class AccountTests {
    private static int testAccountID;
    private static Account account;
    private static ArrayList<Double> testTransactionValues;

    private static double currentDeficitAbsolute;
    private static double savingsBalanceHigh;
    private static double savingsBalanceLow;

    @BeforeAll
    public static void before() {
        testAccountID = 123;
        account = new Account(testAccountID);
        testTransactionValues = new ArrayList<Double>() {
            {
                add(100.50);
                add(-50.90);
                add(333.33);
                add(-78.88);
                add(-65.12);
            }
        };

        currentDeficitAbsolute = 50;
        savingsBalanceHigh = 100;
        savingsBalanceLow = 10;
    }

    @Test
    public void whenCalculateNetBalanceThenReturnNetBalance() {
        double returnedBalance = account.calculateNetBalance(testTransactionValues);
        double expectedBalance = 0;
        for (double transactionValue : testTransactionValues) {
            expectedBalance += transactionValue;
        }
        assertEquals(expectedBalance,returnedBalance);
    }

    @Test   // when savings balance > deficit
    public void whenCalculateIncrementValueThenReturnIncrementValue1() {
        double returnedValue = account.calculateIncrementValue(savingsBalanceHigh,currentDeficitAbsolute);
        double expectedValue = currentDeficitAbsolute;
        assertEquals(expectedValue,returnedValue);
    }

    @Test   // when savings balance <= deficit
    public void whenCalculateIncrementValueThenReturnIncrementValue2() {
        double returnedValue = account.calculateIncrementValue(savingsBalanceLow,currentDeficitAbsolute);
        double expectedValue = savingsBalanceLow;
        assertEquals(expectedValue,returnedValue);
    }
}
