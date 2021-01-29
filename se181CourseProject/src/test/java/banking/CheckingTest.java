package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingTest {
    public static final float APR = (float) 0.05;
    public static float balance = (float) 0;
    Checking checking;

    @BeforeEach
    void setUp() {
        checking = new Checking(APR, balance);
    }

    @Test
    public void checking_account_has_apr() {
        assertEquals(APR, checking.getAPR());
    }

    @Test
    public void checking_account_starts_at_zero() {
        assertEquals(balance, checking.getBalance());
    }

    @Test
    public void deposit_to_checking_account() {
        float deposit = (float) 1500.0;
        checking.deposit_to_account(deposit);
        assertEquals(deposit, checking.getBalance());
    }

    @Test
    public void withdraw_from_checking_account() {
        float deposit = (float) 1500.0;
        float withdraw = (float) 700.0;
        checking.deposit_to_account(deposit);
        checking.withdraw_from_account(withdraw);
        assertEquals((deposit - withdraw), checking.getBalance());
    }

    @Test
    public void withdraw_twice() {
        float deposit = (float) 1500.0;
        float withdraw = (float) 700.0;
        checking.deposit_to_account(deposit);
        checking.withdraw_from_account(withdraw);
        checking.withdraw_from_account(withdraw);
        assertEquals(100.0, checking.getBalance());
    }

    @Test
    public void withdraw_below_zero() {
        float deposit = (float) 1000.0;
        float withdraw = (float) 2000.0;
        checking.deposit_to_account(deposit);
        checking.withdraw_from_account(withdraw);
        assertEquals(0, checking.getBalance());
    }
}
