package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositCommandProcessorTest {
    public static String validCheckingCommand = "Create Checking 12345678 1.0";
    public static String validSavingsCommand = "Create Savings 87654321 0.9";
    public static String validDepositToCheckingCommand = "Deposit 12345678 100";
    public static String validDepositToSavingsCommand = "Deposit 87654321 100";

    CommandProcessor commandProcessor;
    CreateCommandProcessor createCommandProcessor;
    Bank bank;
    private CommandStorage commandStorage;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }


    @Test
    void deposit_to_empty_checking() {
        commandProcessor.process_command(validCheckingCommand);
        assertEquals("Checking", bank.getAccounts().get("12345678").getAccountType());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPR());
        commandProcessor.process_command(validDepositToCheckingCommand);
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void deposit_to_empty_savings() {
        commandProcessor.process_command(validSavingsCommand);
        assertEquals("Savings", bank.getAccounts().get("87654321").getAccountType());
        assertEquals(0.9, bank.getAccounts().get("87654321").getAPR());
        commandProcessor.process_command(validDepositToSavingsCommand);
        assertEquals(100, bank.getAccounts().get("87654321").getBalance());
    }

    @Test
    void deposit_to_checking_twice() {
        commandProcessor.process_command(validCheckingCommand);
        assertEquals("Checking", bank.getAccounts().get("12345678").getAccountType());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPR());
        commandProcessor.process_command(validDepositToCheckingCommand);
        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
        commandProcessor.process_command(validDepositToCheckingCommand);
        assertEquals(200, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void deposit_to_savings_twice() {
        commandProcessor.process_command(validSavingsCommand);
        assertEquals("Savings", bank.getAccounts().get("87654321").getAccountType());
        assertEquals(0.9, bank.getAccounts().get("87654321").getAPR());
        commandProcessor.process_command(validDepositToSavingsCommand);
        assertEquals(100, bank.getAccounts().get("87654321").getBalance());
        commandProcessor.process_command(validDepositToSavingsCommand);
        assertEquals(200, bank.getAccounts().get("87654321").getBalance());
    }
}
