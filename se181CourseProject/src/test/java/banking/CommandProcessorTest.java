package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {
    public static String validCheckingCommand = "Create banking.Checking 12345678 1.0";
    public static String validSavingsCommand = "Create banking.Savings 87654321 0.8";
    public static String validCDCommand = "Create banking.CD 00000000 0.6 100";
    public static String validDepositToCheckingCommand = "Deposit 12345678 100";
    public static String validDepositToSavingsCommand = "Deposit 87654321 100";

    Bank bank;
    CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void create_checking_command() {
        commandProcessor.process_command(validCheckingCommand);
        assertEquals("banking.Checking", bank.getAccounts().get("12345678").getAccountType());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPR());
    }

    @Test
    void create_savings() {
        commandProcessor.process_command(validSavingsCommand);
        assertEquals("banking.Savings", bank.getAccounts().get("87654321").getAccountType());
        assertEquals(0.8, bank.getAccounts().get("87654321").getAPR());
    }

    @Test
    void create_cd() {
        commandProcessor.process_command(validCDCommand);
        assertEquals("banking.CD", bank.getAccounts().get("00000000").getAccountType());
        assertEquals(0.6, bank.getAccounts().get("00000000").getAPR());
    }

    @Test
    void create_multiple_accounts() {
        commandProcessor.process_command(validCheckingCommand);
        assertEquals("banking.Checking", bank.getAccounts().get("12345678").getAccountType());
        commandProcessor.process_command(validSavingsCommand);
        assertEquals("banking.Savings", bank.getAccounts().get("87654321").getAccountType());
        commandProcessor.process_command(validCDCommand);
        assertEquals("banking.CD", bank.getAccounts().get("00000000").getAccountType());
    }

    @Test
    void deposit_to_empty_checking() {
        bank = new Bank();
        commandProcessor.process_command(validCheckingCommand);
        assertEquals("banking.Checking", bank.getAccounts().get("12345678").getAccountType());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPR());
        commandProcessor.process_command(validDepositToCheckingCommand);
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void deposit_to_empty_savings() {
        commandProcessor.process_command(validSavingsCommand);
        assertEquals("banking.Savings", bank.getAccounts().get("87654321").getAccountType());
        assertEquals(0.8, bank.getAccounts().get("87654321").getAPR());
        commandProcessor.process_command(validDepositToSavingsCommand);
        assertEquals(100, bank.getAccounts().get("87654321").getBalance());
    }

    @Test
    void deposit_to_checking_twice() {
        commandProcessor.process_command(validCheckingCommand);
        assertEquals("banking.Checking", bank.getAccounts().get("12345678").getAccountType());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPR());
        commandProcessor.process_command(validDepositToCheckingCommand);
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
        commandProcessor.process_command(validDepositToCheckingCommand);
        assertEquals(200, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void deposit_to_savings_twice() {
        commandProcessor.process_command(validSavingsCommand);
        assertEquals("banking.Savings", bank.getAccounts().get("87654321").getAccountType());
        assertEquals(0.8, bank.getAccounts().get("87654321").getAPR());
        commandProcessor.process_command(validDepositToSavingsCommand);
        assertEquals(100, bank.getAccounts().get("87654321").getBalance());
        commandProcessor.process_command(validDepositToSavingsCommand);
        assertEquals(200, bank.getAccounts().get("87654321").getBalance());
    }
}
