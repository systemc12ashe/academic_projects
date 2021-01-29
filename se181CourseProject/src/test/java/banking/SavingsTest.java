package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {
    public static final float APR = (float) 0.05;
    public static float balance = (float) 0;
    Savings savings;

    @BeforeEach
    void setUp() {
        savings = new Savings(APR, balance);
    }

    @Test
    public void savings_account_has_apr() {
        assertEquals(APR, savings.getAPR());
    }

    @Test
    public void savings_account_starts_at_zero() {
        assertEquals(balance, savings.getBalance());
    }

    @Test
    public void deposit_to_savings_account() {
        float deposit = (float) 1500.0;
        savings.deposit_to_account(deposit);
        assertEquals(deposit, savings.getBalance());
    }

    @Test
    public void withdraw_from_savings_account() {
        float deposit = (float) 1500.0;
        float withdraw = (float) 700.0;
        savings.deposit_to_account(deposit);
        savings.withdraw_from_account(withdraw);
        assertEquals((deposit - withdraw), savings.getBalance());
    }

    @Test
    public void withdraw_twice() {
        float deposit = (float) 1500.0;
        float withdraw = (float) 700.0;
        savings.deposit_to_account(deposit);
        savings.withdraw_from_account(withdraw);
        savings.withdraw_from_account(withdraw);
        assertEquals(100.0, savings.getBalance());
    }

    @Test
    public void withdraw_below_zero() {
        float deposit = (float) 1000.0;
        float withdraw = (float) 2000.0;
        savings.deposit_to_account(deposit);
        savings.withdraw_from_account(withdraw);
        assertEquals(0, savings.getBalance());
    }
}
