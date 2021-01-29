package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositCommandValidatorTest {
    public static String validDepositCommand = "Deposit 12345678 100";
    public float APR = (float) 0.06;
    public float balance = (float) 1000;
    CommandValidator commandValidator;
    Bank bank;
    CreateCommandValidator createCommandValidator;
    DepositCommandValidator depositCommandValidator;
    WithdrawalCommandValidator withdrawalCommandValidator;
    TransferCommandValidator transferCommandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createCommandValidator = new CreateCommandValidator(bank);
        depositCommandValidator = new DepositCommandValidator(bank);
        withdrawalCommandValidator = new WithdrawalCommandValidator(bank);
        transferCommandValidator = new TransferCommandValidator(bank);
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("12345678", "Savings", APR, 0);
        bank.addAccount("99999999", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
    }

    @Test
    void deposit_to_savings() {
        boolean actual = commandValidator.validate("Deposit 12345678 100");
        assertTrue(actual);
    }

    @Test
    void deposit_to_checking() {
        boolean actual = commandValidator.validate("Deposit 99999999 100");
        assertTrue(actual);
    }

    @Test
    void attempt_deposit_to_cd() {
        boolean actual = commandValidator.validate("Deposit 00000000 100");
        assertFalse(actual);
    }

    @Test
    void amount_checking() {
        boolean zero = commandValidator.validate("Deposit 99999999 0");
        assertTrue(zero);
        boolean one = commandValidator.validate("Deposit 99999999 1");
        assertTrue(one);
        boolean below = commandValidator.validate("Deposit 99999999 999");
        assertTrue(below);
        boolean equal = commandValidator.validate("Deposit 99999999 1000");
        assertTrue(equal);
        boolean above = commandValidator.validate("Deposit 99999999 1001");
        assertFalse(above);
        boolean negative = commandValidator.validate("Deposit 99999999 -100");
        assertFalse(negative);
        boolean high = commandValidator.validate("Deposit 99999999 1000000");
        assertFalse(high);
    }

    @Test
    void amount_savings() {
        boolean zero = commandValidator.validate("Deposit 12345678 0");
        assertTrue(zero);
        boolean one = commandValidator.validate("Deposit 12345678 1");
        assertTrue(one);
        boolean below = commandValidator.validate("Deposit 12345678 2499");
        assertTrue(below);
        boolean equal = commandValidator.validate("Deposit 12345678 2500");
        assertTrue(equal);
        boolean above = commandValidator.validate("Deposit 12345678 2501");
        assertFalse(above);
        boolean negative = commandValidator.validate("Deposit 12345678 -100");
        assertFalse(negative);
        boolean high = commandValidator.validate("Deposit 12345678 1000000");
        assertFalse(high);
    }

    @Test
    void invalid_ID() {
        boolean zero = commandValidator.validate("Deposit 123455678 0");
        assertFalse(zero);
        boolean one = commandValidator.validate("Deposit 55555 1");
        assertFalse(one);
        boolean between = commandValidator.validate("Deposit 55555555555 1500");
        assertFalse(between);
        boolean above = commandValidator.validate("Deposit 5555555A 100000000");
        assertFalse(above);
        boolean negative = commandValidator.validate("Deposit 5555555 -10");
        assertFalse(negative);
    }

    @Test
    void invalid_command_name() {
        boolean one = commandValidator.validate("Depositt 12345678 100");
        assertFalse(one);
        boolean two = commandValidator.validate("Deepositt 12345678 100");
        assertFalse(two);
    }

    @Test
    void invalid_input_length() {
        boolean actual = commandValidator.validate("Deposit 12345678 100 5");
        assertFalse(actual);
    }


}
