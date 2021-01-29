package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    public static final String AccountType = "Account Example";
    public static final float APR = (float) 0.05;
    public static float balance = (float) 0;
    Account account;

    @Test
    public void account_type() {
        assertEquals("Checking", Account.checking(APR, balance).getAccountType());
        assertEquals("Savings", Account.savings(APR, balance).getAccountType());
        assertEquals("CD", Account.cd(APR, balance).getAccountType());
    }


    @Test
    public void account_has_APR() {
        assertEquals(APR, Account.checking(APR, balance).getAPR());
        assertEquals(APR, Account.savings(APR, balance).getAPR());
        assertEquals(APR, Account.cd(APR, balance).getAPR());
    }

    @BeforeEach
    void setUp() {
        account = new Account(AccountType, APR, balance);
    }

    @Test
    public void deposit_to_account() {
        float deposit = (float) 1500.0;
        account.deposit_to_account(deposit);
        assertEquals(deposit, account.getBalance());
    }

    @Test
    public void withdraw_from_checking_account() {
        float deposit = (float) 1500.0;
        float withdraw = (float) 700.0;
        account.deposit_to_account(deposit);
        account.withdraw_from_account(withdraw);
        assertEquals((deposit - withdraw), account.getBalance());
    }


}
