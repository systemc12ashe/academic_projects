package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeCommandValidatorTest {
    public static String validPassTimeCommand = "Pass 1";
    public static String invalidPassTimeCommand = "Passss 1";
    CommandValidator commandValidator;
    Bank bank;

    @Test
    void pass_time_valid() {
        boolean actual = commandValidator.validate(validPassTimeCommand);
        assertTrue(actual);
    }

    @Test
    void pass_time_invalid_pass() {
        boolean actual = commandValidator.validate(invalidPassTimeCommand);
        assertFalse(actual);
    }

    @Test
    void pass_time_various_months() {
        boolean zero = commandValidator.validate("Pass 0");
        assertFalse(zero);
        boolean one = commandValidator.validate("Pass 1");
        assertTrue(one);
        boolean below = commandValidator.validate("Pass 59");
        assertTrue(below);
        boolean equal = commandValidator.validate("Pass 60");
        assertTrue(equal);
        boolean above = commandValidator.validate("Pass 61");
        assertFalse(above);
        boolean negative = commandValidator.validate("Pass -1");
        assertFalse(negative);
        boolean high = commandValidator.validate("Pass 100000000");
        assertFalse(high);
    }

}
