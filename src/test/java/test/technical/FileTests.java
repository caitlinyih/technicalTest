package test.technical;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//static so you can use the methods without qualifying the class the method belongs to
//static import allows unqualified access to static state and behaviour

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileTests {

    private static String testFile;
    private static File file;

    private static int currentAccountID;
    private static int savingsAccountID;

    private static ArrayList<Double> currentAccountValues;
    private static ArrayList<Double> savingsAccountValues;

    // must be static to be accessed as test class is not an instantiable class i.e. not object representation to be instantiated
    // A variable declared as static means that its value is shared by all instances of this class.
    // i.e. state not instantiated object-specific

    private static LocalDateTime testLocalDateTime;
    private static Transaction testTransaction;

    @BeforeAll
    public static void before() {   // why static?
        testFile = "src/main/resources/customer-1234567-ledger.csv";
        file = new File(testFile);

        currentAccountID = 789;
        savingsAccountID = 123;

        currentAccountValues = new ArrayList<Double>() {
            {
                add(50.00);
                add(-77.43);
                add(-20.23);
                add(200.23);
                add(-200.23);
            }
        };
        savingsAccountValues = new ArrayList<Double>() {
            {
                add(100.50);
                add(50.25);
                add(10.56);
                add(100.23);
            }
        };

        testLocalDateTime = LocalDateTime.now();
        testTransaction = new Transaction(999,"TEST","TEST",testLocalDateTime,999.99);
    }

    @Test
    public void whenGetCurrentAccountIDThenReturnID() {    // public because JUnit framework calls test methods from outside test class
        int returnedID = file.getCurrentAccountID();
        int expectedID = currentAccountID;
        assertEquals(expectedID,returnedID);
    }

    @Test
    public void whenGetSavingsAccountIDThenReturnID() {
        int returnedID = file.getSavingsAccountID();
        int expectedID = savingsAccountID;
        assertEquals(expectedID,returnedID);
    }

    @Test
    public void whenGetCurrentAccountValuesThenReturnValues() {
        ArrayList<Double> returned = file.getCurrentAccountValues(currentAccountID);
        ArrayList<Double> expected = currentAccountValues;
        assertEquals(expected,returned);
    }

    @Test
    public void whenGetSavingsAccountValuesThenReturnValues() {
        ArrayList<Double> returned = file.getSavingsAccountValues(savingsAccountID);
        ArrayList<Double> expected = savingsAccountValues;
        assertEquals(expected,returned);
    }

    @Test
    public void whenWriteTransactionRecordThenTransactionRecorded() throws IOException, CsvException {
        file.writeTransactionRecord(testTransaction);
        CSVReader reader = new CSVReader(new FileReader(testFile));
        List<String[]> transactions = reader.readAll();
        reader.close();

        String[] returnedTransaction = transactions.get(transactions.size()-1);
        String[] expectedTransaction = {"999","TEST","TEST",testLocalDateTime.toString(),"999.99"};

        for (int i=0; i<returnedTransaction.length; i++) {
            assertEquals(expectedTransaction[i],returnedTransaction[i]);
        }
    }

}