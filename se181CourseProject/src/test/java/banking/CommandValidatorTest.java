package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {
    public static String validCheckingCommand = "Create Checking 12345678 1.0";
    public static String validSavingsCommand = "Create Savings 87654321 0.8";
    public static String validCDCommand = "Create CD 00000000 0.06 1000";
    public static String validDepositToCheckingCommand = "Deposit 12345678 100";
    public static String validDepositToSavingsCommand = "Deposit 87654321 100";
    public static String validPassTimeCommand = "Pass 1";

    public static String invalidCheckingCommand = "Createe Checking 12345678 1.0";
    public static String invalidSavingsCommand = "Createe Savings 87654321 0.8";
    public static String invalidCDCommand = "Createe CD 00000000 0.6 100";
    public static String invalidDepositToCheckingCommand = "Depositt 12345678 100";
    public static String invalidDepositToSavingsCommand = "Depositt 87654321 100";
    public static String invalidDepositToCDCommand = "Deposit 00000000 100";
    public static String invalidPassTimeCommand = "Passs 1";

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
    void validate_checking_create() {
        boolean valid_command = commandValidator.validate(validCheckingCommand);
        assertTrue(valid_command);
        boolean invalid_command = commandValidator.validate(invalidCheckingCommand);
        assertFalse(invalid_command);
    }

    @Test
    void validate_savings_create() {
        boolean valid_command = commandValidator.validate(validSavingsCommand);
        assertTrue(valid_command);
        boolean invalid_command = commandValidator.validate(invalidSavingsCommand);
        assertFalse(invalid_command);
    }

    @Test
    void validate_cd_create() {
        boolean valid_command = commandValidator.validate(validCDCommand);
        assertTrue(valid_command);
        boolean invalid_command = commandValidator.validate(invalidCDCommand);
        assertFalse(invalid_command);
    }

    @Test
    void validate_checking_deposit() {
        float APR = (float) 0.06;
        float balance = (float) 1000;
        createCommandValidator = new CreateCommandValidator(bank);
        depositCommandValidator = new DepositCommandValidator(bank);
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("87654321", "Savings", APR, 0);
        bank.addAccount("12345678", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        boolean valid_command = commandValidator.validate(validDepositToCheckingCommand);
        assertTrue(valid_command);
        boolean invalid_command = commandValidator.validate(invalidDepositToCheckingCommand);
        assertFalse(invalid_command);
    }

    @Test
    void validate_savings_deposit() {
        float APR = (float) 0.06;
        float balance = (float) 1000;
        createCommandValidator = new CreateCommandValidator(bank);
        depositCommandValidator = new DepositCommandValidator(bank);
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("87654321", "Savings", APR, 0);
        bank.addAccount("12345678", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        boolean valid_command = commandValidator.validate(validDepositToSavingsCommand);
        assertTrue(valid_command);
        boolean invalid_command = commandValidator.validate(invalidDepositToSavingsCommand);
        assertFalse(invalid_command);
    }

    @Test
    void validate_cd_deposit() {
        float APR = (float) 0.06;
        float balance = (float) 1000;
        createCommandValidator = new CreateCommandValidator(bank);
        depositCommandValidator = new DepositCommandValidator(bank);
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
        bank.addAccount("87654321", "Savings", APR, 0);
        bank.addAccount("12345678", "Checking", APR, 0);
        bank.addAccount("00000000", "CD", APR, balance);
        boolean invalid_command = commandValidator.validate(invalidDepositToCDCommand);
        assertFalse(invalid_command);
    }

    @Test
    void validate_pass_time() {
        boolean valid_command = commandValidator.validate(validPassTimeCommand);
        assertTrue(valid_command);
        boolean invalid_command = commandValidator.validate(invalidPassTimeCommand);
        assertFalse(invalid_command);
    }

}
