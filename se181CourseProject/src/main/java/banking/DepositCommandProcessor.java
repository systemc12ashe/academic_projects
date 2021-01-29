package banking;

public class DepositCommandProcessor {
    private Bank bank;

    public DepositCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void deposit(String validCommand) {
        String[] command = validCommand.split(" ");
        String accountID = get_account_ID_from_command(command);
        float amount = get_amount_from_command(command);
        bank.getAccounts().get(accountID).deposit_to_account(amount);
    }

    private float get_amount_from_command(String[] command) {
        float amount = Float.parseFloat(command[2]);
        return amount;
    }

    private String get_account_ID_from_command(String[] command) {
        String accountID = command[1];
        return accountID;
    }
}

