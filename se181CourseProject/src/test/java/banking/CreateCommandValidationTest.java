package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCommandValidationTest {
    public static String validCheckingCommand = "create Checking 12345678 0.06";
    public static String validSavingsCommand = "create Savings 12345678 0.06";
    public static String validCDCommand = "create CD 12345678 0.06 1000";

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
    }

    @Test
    void duplicate_checking() {
        float APR = (float) 0.06;
        float balance = (float) 1000;
        bank = new Bank();
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("12345678", "Savings", APR, 0);
        bank.addAccount("99999999", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        boolean second = commandValidator.validate(validCheckingCommand);
        assertFalse(second);
    }

    @Test
    void duplicate_cd() {
        float APR = (float) 0.06;
        float balance = (float) 1000;
        bank = new Bank();
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("12345678", "Savings", APR, 0);
        bank.addAccount("99999999", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        boolean second = commandValidator.validate(validCDCommand);
        assertFalse(second);
    }

    @Test
    void duplicate_savings() {
        float APR = (float) 0.06;
        float balance = (float) 1000;
        bank = new Bank();
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("12345678", "Savings", APR, 0);
        bank.addAccount("99999999", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        boolean second = commandValidator.validate(validSavingsCommand);
        assertFalse(second);
    }

    @Test
    void valid_command_checking() {
        boolean actual = commandValidator.validate(validCheckingCommand);
        assertTrue(actual);
    }

    @Test
    void valid_command_savings() {
        boolean actual = commandValidator.validate(validSavingsCommand);
        assertTrue(actual);
    }

    @Test
    void command_amount_cd() {
        boolean negative = commandValidator.validate("create CD 12345678 0.06 -100");
        assertFalse(negative);
        boolean zero = commandValidator.validate("create CD 12345678 0.06 0");
        assertFalse(zero);
        boolean one = commandValidator.validate("create CD 12345678 0.06 1");
        assertFalse(one);
        boolean below = commandValidator.validate("create CD 12345678 0.06 999");
        assertFalse(below);
        boolean equal = commandValidator.validate("create CD 12345678 0.06 1000");
        assertTrue(equal);
        boolean above = commandValidator.validate("create CD 12345678 0.06 1001");
        assertTrue(above);
        boolean high = commandValidator.validate("create CD 12345678 0.06 1000000");
        assertTrue(high);
    }

    @Test
    void invalid_ID() {
        boolean too_many = commandValidator.validate("create CD 12345678888 0.06 1000000");
        assertFalse(too_many);
        boolean too_few = commandValidator.validate("create Savings 123 0.06 1000000");
        assertFalse(too_few);
        boolean bad_char = commandValidator.validate("create checking 123************* 0.06 1000000");
        assertFalse(bad_char);
    }

    @Test
    void invalid_accountType() {
        boolean bad_name = commandValidator.validate("create checkiiiiiiing 12345678 0.06 1000000");
        assertFalse(bad_name);
    }

    @Test
    void values_APR() {
        boolean zero = commandValidator.validate("create Checking 12345678 0");
        assertFalse(zero);
        boolean pointNine = commandValidator.validate("create Checking 12345678 9.9");
        assertTrue(pointNine);
        boolean one = commandValidator.validate("create Checking 12345678 10");
        assertTrue(one);
        boolean onePointOne = commandValidator.validate("create Checking 12345678 10.1");
        assertFalse(onePointOne);
        boolean negative = commandValidator.validate("create Checking 12345678 -1");
        assertFalse(negative);
        boolean high = commandValidator.validate("create Checking 12345678 10000000");
        assertFalse(high);
    }
}
