package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreateCommandProcessorTest {
    public static String validCheckingCommand = "Create Checking 12345678 1.0";
    public static String validSavingsCommand = "Create Savings 87654321 0.8";
    public static String validCDCommand = "Create CD 00000000 0.6 100";

    CreateCommandProcessor commandProcessor;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CreateCommandProcessor(bank);
    }

    @Test
    void create_checking() {
        commandProcessor.create(validCheckingCommand);
        assertEquals("Checking", bank.getAccounts().get("12345678").getAccountType());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPR());
    }

    @Test
    void create_savings() {
        commandProcessor.create(validSavingsCommand);
        assertEquals("Savings", bank.getAccounts().get("87654321").getAccountType());
        assertEquals(0.8, bank.getAccounts().get("87654321").getAPR());
    }

    @Test
    void create_cd() {
        commandProcessor.create(validCDCommand);
        assertEquals("CD", bank.getAccounts().get("00000000").getAccountType());
        assertEquals(0.6, bank.getAccounts().get("00000000").getAPR());
        assertEquals(100, bank.getAccounts().get("00000000").getBalance());

    }

    @Test
    void create_multiple_accounts() {
        commandProcessor.create(validCheckingCommand);
        assertEquals("Checking", bank.getAccounts().get("12345678").getAccountType());
        commandProcessor.create(validSavingsCommand);
        assertEquals("Savings", bank.getAccounts().get("87654321").getAccountType());
        commandProcessor.create(validCDCommand);
        assertEquals("CD", bank.getAccounts().get("00000000").getAccountType());
    }
}
