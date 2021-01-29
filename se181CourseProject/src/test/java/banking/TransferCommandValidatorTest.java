package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {
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
    }

    @Test
    void transfer_between_checking_and_savings() {
        String command = "Transfer 12345678 87654321 50";
        boolean valid = commandValidator.validate(command);
        assertTrue(valid);
    }

    @Test
    void transfer_between_checking() {
        String command = "Transfer 22222222 87654321 50";
        boolean valid = commandValidator.validate(command);
        assertTrue(valid);
    }

    @Test
    void transfer_between_savings() {
        String command = "Transfer 12345678 11111111 50";
        boolean valid = commandValidator.validate(command);
        assertTrue(valid);
    }

    @Test
    void transfer_between_cd_and_other_account_type() {
        String command = "Transfer 12345678 00000000 50";
        boolean valid = commandValidator.validate(command);
        assertFalse(valid);
    }

    @Test
    void invalid_transfer_command_phrase() {
        String command = "Transferrrrr 12345678 11111111 50";
        boolean valid = commandValidator.validate(command);
        assertFalse(valid);
    }

    @Test
    void invalid_ID() {
        String command = "Transfer 12333333333345678 11111111 50";
        boolean valid = commandValidator.validate(command);
        assertFalse(valid);
    }

    @Test
    void invalid_amount() {
        String command = "Transfer 12345678 11111111 5000000000000000000";
        boolean valid = commandValidator.validate(command);
        assertFalse(valid);
    }
}
