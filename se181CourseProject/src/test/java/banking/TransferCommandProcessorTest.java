package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCommandProcessorTest {
    public float APR = (float) 0.6;
    public float balance = (float) 1000;
    CommandValidator commandValidator;
    Bank bank;
    CreateCommandValidator createCommandValidator;
    DepositCommandValidator depositCommandValidator;
    WithdrawalCommandValidator withdrawalCommandValidator;
    CommandProcessor commandProcessor;
    TransferCommandValidator transferCommandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
        createCommandValidator = new CreateCommandValidator(bank);
        depositCommandValidator = new DepositCommandValidator(bank);
        withdrawalCommandValidator = new WithdrawalCommandValidator(bank);
        transferCommandValidator = new TransferCommandValidator(bank);
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("12345678", "Savings", APR, 0);
        bank.addAccount("11111111", "Savings", APR, 0);
        bank.addAccount("22222222", "Checking", APR, 0);
        bank.addAccount("87654321", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        commandProcessor.process_command("Deposit 12345678 50");
        commandProcessor.process_command("Deposit 11111111 50");
        commandProcessor.process_command("Deposit 22222222 50");
        commandProcessor.process_command("Deposit 87654321 50");
    }

    @Test
    void transfer_between_checking_and_savings() {
        String command = "Transfer 12345678 87654321 50";
        commandProcessor.process_command(command);
        assertEquals(100, bank.getAccounts().get("87654321").getBalance());
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void transfer_between_checking() {
        String command = "Transfer 22222222 87654321 50";
        commandProcessor.process_command(command);
        assertEquals(100, bank.getAccounts().get("87654321").getBalance());
        assertEquals(0, bank.getAccounts().get("22222222").getBalance());
    }

    @Test
    void transfer_between_savings() {
        String command = "Transfer 12345678 11111111 50";
        commandProcessor.process_command(command);
        assertEquals(100, bank.getAccounts().get("11111111").getBalance());
        assertEquals(0, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void transfer_between_cd_and_other_account_type_valid() {
        commandProcessor.process_command("Pass 13");
        String command = "Transfer 00000000 12345678 1032.2647379373607";
        commandProcessor.process_command(command);
        assertEquals(0, bank.getAccounts().get("00000000").getBalance());
        assertEquals(1032.2647379373607, bank.getAccounts().get("12345678").getBalance());
    }
}
