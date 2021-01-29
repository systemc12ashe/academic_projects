package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawalCommandValidatorTest {
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
        bank.addAccount("87654321", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
    }

    @Test
    void withdraw_from_savings() {
        boolean zero = commandValidator.validate("Withdraw 12345678 0");
        assertFalse(zero);
        boolean one = commandValidator.validate("Withdraw 12345678 1");
        assertTrue(one);
        boolean below = commandValidator.validate("Withdraw 12345678 999");
        assertTrue(below);
        boolean equal = commandValidator.validate("Withdraw 12345678 1000");
        assertTrue(equal);
        boolean above = commandValidator.validate("Withdraw 12345678 1001");
        assertFalse(above);
        boolean negative = commandValidator.validate("Withdraw 12345678 -100");
        assertFalse(negative);
        boolean high = commandValidator.validate("Withdraw 12345678 1000000");
        assertFalse(high);
    }

    @Test
    void withdraw_from_checking() {
        boolean zero = commandValidator.validate("Withdraw 87654321 0");
        assertFalse(zero);
        boolean one = commandValidator.validate("Withdraw 87654321 1");
        assertTrue(one);
        boolean below = commandValidator.validate("Withdraw 87654321 399");
        assertTrue(below);
        boolean equal = commandValidator.validate("Withdraw 87654321 400");
        assertTrue(equal);
        boolean above = commandValidator.validate("Withdraw 87654321 401");
        assertFalse(above);
        boolean negative = commandValidator.validate("Withdraw 87654321 -100");
        assertFalse(negative);
        boolean high = commandValidator.validate("Withdraw 87654321 1000000");
        assertFalse(high);
    }

    @Test
    void withdraw_from_CD() {
        commandProcessor.process_command("Pass 13");
        boolean zero = commandValidator.validate("Withdraw 00000000 0");
        assertFalse(zero);
        boolean one = commandValidator.validate("Withdraw 00000000 1");
        assertFalse(one);
        boolean below = commandValidator.validate("Withdraw 00000000 999");
        assertFalse(below);
        boolean equal = commandValidator.validate("Withdraw 00000000 1032.2647379373607");
        assertTrue(equal);
        boolean above = commandValidator.validate("Withdraw 00000000 1036");
        assertFalse(above);
        boolean negative = commandValidator.validate("Withdraw 00000000 -100");
        assertFalse(negative);
        boolean high = commandValidator.validate("Withdraw 00000000 1000000");
        assertFalse(high);
    }

    @Test
    void withdraw_more_than_balance() {
        commandProcessor.process_command("Deposit 12345678 100");
        commandProcessor.process_command("Deposit 87654321 100");
        boolean savings_withdrawal = commandValidator.validate("Withdraw 12345678 101");
        assertTrue(savings_withdrawal);
        boolean checking_withdrawal = commandValidator.validate("Withdraw 87654321 101");
        assertTrue(checking_withdrawal);
    }

    @Test
    void withdraw_more_than_once_on_savings() {
        commandProcessor.process_command("Deposit 12345678 100");
        bank.getAccounts().get("12345678").withdraw_from_account(50);
        boolean withdrawTwo = commandValidator.validate("Withdraw 12345678 1");
        assertFalse(withdrawTwo);
    }

    @Test
    void withdraw_cd_not_enough_time_passed() {
        commandProcessor.process_command("Pass 4");
        boolean actual = commandValidator.validate("Withdraw 00000000 1000");
        assertFalse(actual);
    }


}
