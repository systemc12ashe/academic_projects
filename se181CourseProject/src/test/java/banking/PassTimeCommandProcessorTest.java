package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeCommandProcessorTest {
    public static String passTimeOneMonth = "Pass 1";
    public static String passTimeMultipleMonth = "Pass 2";

    public static String validDepositLowToSavingsCommand = "Deposit 12345678 50";
    public static String validDepositLowToCheckingCommand = "Deposit 87654321 50";

    public static String validDepositToSavingsCommand = "Deposit 12345678 100";
    public static String validDepositToCheckingCommand = "Deposit 87654321 100";

    public float APR = (float) 0.6;
    public float balance = (float) 1000;

    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
        bank.addAccount("12345678", "Savings", APR, 0);
        bank.addAccount("87654321", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
    }

    @Test
    void pass_time_on_empty_accounts() {
        assertEquals(3, bank.getAccounts().size());
        commandProcessor.process_command(passTimeOneMonth);
        assertEquals(1, (bank.getAccounts()).size());
    }

    @Test
    void pass_time_on_low_accounts() {
        commandProcessor.process_command(validDepositLowToSavingsCommand);
        commandProcessor.process_command(validDepositLowToCheckingCommand);
        commandProcessor.process_command(passTimeOneMonth);
        assertEquals(25, bank.getAccounts().get("12345678").getBalance());
        assertEquals(25, bank.getAccounts().get("87654321").getBalance());
    }

    @Test
    void pass_one_month_on_accounts() {
        commandProcessor.process_command(validDepositToSavingsCommand);
        commandProcessor.process_command(validDepositToCheckingCommand);
        commandProcessor.process_command(passTimeOneMonth);
        assertEquals(100.05, bank.getAccounts().get("12345678").getBalance(), 0.05);
        assertEquals(100.05, bank.getAccounts().get("87654321").getBalance(), 0.05);
        assertEquals(1002.0, bank.getAccounts().get("00000000").getBalance(), 0.05);
        assertEquals(1, bank.getAccounts().get("12345678").getTimePassed());
    }

    @Test
    void pass_multiple_months_on_accounts() {
        commandProcessor.process_command(validDepositToSavingsCommand);
        commandProcessor.process_command(validDepositToCheckingCommand);
        commandProcessor.process_command(passTimeMultipleMonth);
        assertEquals(100.10, bank.getAccounts().get("12345678").getBalance(), 0.05);
        assertEquals(100.10, bank.getAccounts().get("87654321").getBalance(), 0.05);
        assertEquals(1004.0, bank.getAccounts().get("00000000").getBalance(), 0.05);
    }

}
