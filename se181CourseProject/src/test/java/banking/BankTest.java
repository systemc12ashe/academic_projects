package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BankTest {
    public static final String accountID = "12345678";
    public static final String accountIDTwo = "11111111";
    public static final String accountIDThree = "00000000";
    public static float APR = (float) 0.05;
    public static float balance = (float) 1500.0;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_accounts_initially() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void add_savings_to_bank() {
        bank.addAccount(accountID, "Savings", APR, balance);
        assertEquals("Savings", bank.getAccounts().get(accountID).getAccountType());
    }

    @Test
    void add_checking_to_bank() {
        bank.addAccount(accountID, "Checking", APR, balance);
        assertEquals("Checking", bank.getAccounts().get(accountID).getAccountType());
    }

    @Test
    void add_CD_to_bank() {
        bank.addAccount(accountID, "CD", APR, balance);
        assertEquals("CD", bank.getAccounts().get(accountID).getAccountType());
    }

    @Test
    void add_three_accounts_to_bank() {
        bank.addAccount(accountID, "Savings", APR, balance);
        bank.addAccount(accountIDTwo, "CD", APR, balance);
        bank.addAccount(accountIDThree, "Checking", APR, balance);
        assertEquals("Savings", bank.getAccounts().get(accountID).getAccountType());
        assertEquals("CD", bank.getAccounts().get(accountIDTwo).getAccountType());
        assertEquals("Checking", bank.getAccounts().get(accountIDThree).getAccountType());
    }
}
