package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDTest {
    public static float APR = (float) 0.05;
    public static float balance = (float) 1500.0;
    CD cd;

    @BeforeEach
    void setUp() {
        cd = new CD(APR, balance);
    }

    @Test
    public void cd_account_has_apr() {
        assertEquals(APR, cd.getAPR());
    }

    @Test
    public void cd_account_has_balance() {
        assertEquals(balance, cd.getBalance());
    }

    @Test
    public void withdraw_from_cd() {
        float withdraw = (float) 700.0;
        cd.withdraw_from_account(withdraw);
        assertEquals((balance - withdraw), cd.getBalance());
    }
}
