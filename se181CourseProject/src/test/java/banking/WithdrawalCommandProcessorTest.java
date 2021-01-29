package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawalCommandProcessorTest {
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
    void withdraw_from_checking() {
        commandProcessor.process_command("Deposit 87654321 100");
        commandProcessor.process_command("Withdraw 87654321 50");
        assertEquals(50, bank.getAccounts().get("87654321").getBalance());
    }

    @Test
    void withdraw_from_savings() {
        commandProcessor.process_command("Deposit 12345678 100");
        commandProcessor.process_command("Withdraw 12345678 50");
        assertEquals(50, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void withdraw_from_cd() {
        commandProcessor.process_command("Pass 13");
        commandProcessor.process_command("Withdraw 00000000 1032.2647379373607");
        assertEquals(0, bank.getAccounts().get("00000000").getBalance());
    }
}
