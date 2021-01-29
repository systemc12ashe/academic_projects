package banking;

public class CreateCommandProcessor {
    private Bank bank;

    public CreateCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void create(String validCommand) {
        String[] command = validCommand.split(" ");
        String accountType = get_accountType_from_command(command);
        String accountID = get_account_ID_from_command(command);
        double APR = get_APR_from_command(command);

        if (accountType.toLowerCase().equals("cd")) {
            double amount = get_amount_from_command(command);
            bank.addAccount(accountID, accountType, APR, amount);
        } else {
            double amount = 0;
            bank.addAccount(accountID, accountType, APR, amount);
        }
    }

    private String get_accountType_from_command(String[] command) {
        String accountType = command[1];
        return accountType;
    }

    private String get_account_ID_from_command(String[] command) {
        String accountID = command[2];
        return accountID;
    }

    private double get_APR_from_command(String[] command) {
        double APR = Double.parseDouble(command[3]);
        return APR;
    }

    private double get_amount_from_command(String[] command) {
        double amount = Double.parseDouble(command[4]);
        return amount;
    }
}

