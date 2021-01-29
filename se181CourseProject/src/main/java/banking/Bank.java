package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private static Map<String, Account> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccount(String accountID, String type, double APR, double balance) {
        accounts.put(accountID, new Account(type, APR, balance));
    }

    public List<String> accountOutput() {
        List<String> output = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        for (String account : getAccounts().keySet()) {
            String accountTypeBefore = getAccounts().get(account).getAccountType();
            String accountType = accountTypeBefore.substring(0, 1).toUpperCase() + accountTypeBefore.substring(1);
            String ID = account;
            String balance = decimalFormat.format(getAccounts().get(account).getBalance());
            String apr = decimalFormat.format(getAccounts().get(account).getAPR());
            output.add(accountType + " " + ID + " " + balance + " " + apr);
            output.addAll(getAccounts().get(account).getCommands());
        }
        return output;
    }
}
