package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommandProcessor {
    private Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void process_command(String validCommand) {
        String[] command = separate_command(validCommand);
        determine_command(command[0].toLowerCase(), command);
    }

    public String[] separate_command(String validCommand) {
        String[] command = validCommand.split(" ");
        return command;
    }

    public void determine_command(String commandName, String[] command) {
        if (commandName.equals("create")) {
            create(command);
        } else if (commandName.equals("deposit")) {
            deposit(command);
        } else if (commandName.equals("pass")) {
            passTime(command);
        } else if (commandName.equals("withdraw")) {
            withdraw(command);
        } else if (commandName.equals("transfer")) {
            transfer(command);
        } else {
            return;
        }
    }

    private void transfer(String[] command) {
        String accountFrom = get_accountFrom_from_transfer(command);
        String accountTo = get_accountTo_from_transfer(command);
        double amount = get_amount_from_transfer(command);
        bank.getAccounts().get(accountFrom).withdraw_from_account(amount);
        bank.getAccounts().get(accountTo).deposit_to_account(amount);
    }

    private double get_amount_from_transfer(String[] command) {
        double amount = Double.parseDouble(command[3]);
        return amount;
    }

    private String get_accountTo_from_transfer(String[] command) {
        return command[2];
    }

    private String get_accountFrom_from_transfer(String[] command) {
        return command[1];
    }

    private void withdraw(String[] validCommand) {
        String accountID = get_account_ID_from_deposit_or_withdraw_command(validCommand);
        float amount = get_amount_from_deposit_or_withdraw_command(validCommand);
        bank.getAccounts().get(accountID).withdraw_from_account(amount);
    }

    private void passTime(String[] validCommand) {
        int months = get_months_from_pass_time(validCommand);
        List<String> toRemove = new ArrayList();

        for (String account : bank.getAccounts().keySet()) {
            bank.getAccounts().get(account).addTime(months);
            double APR = bank.getAccounts().get(account).getAPR();
            double balance = bank.getAccounts().get(account).getBalance();
            String account_type = bank.getAccounts().get(account).getAccountType();

            if (balance == 0) {
                toRemove.add(account);
            } else if (balance < 100) {
                bank.getAccounts().get(account).withdraw_from_account(25 * months);
            } else {
                double amount_added_from_apr = calculateAPRAccrual(APR, balance, months, account_type);
                bank.getAccounts().get(account).deposit_to_account(amount_added_from_apr);
            }
        }

        for (String account : toRemove) {
            bank.getAccounts().remove(account);
        }
    }

    private int get_months_from_pass_time(String[] validCommand) {
        int months = Integer.parseInt(validCommand[1]);
        return months;
    }

    private double calculateAPRAccrual(double apr, double balance, int months, String account_type) {
        double new_apr = ((apr / 100) / 12);
        double total = 0;
        if (account_type.toLowerCase().equals("cd")) {
            int i = 0;
            while (i < months) {
                total += (balance * new_apr);
                balance += total;
                total += (balance * new_apr);
                balance += total;
                total += (balance * new_apr);
                balance += total;
                total += (balance * new_apr);
                balance += total;
                i++;
            }
        } else {
            int i = 0;
            while (i < months) {
                total += (balance * new_apr);
                balance += total;
                i++;
            }
        }
        return total;
    }

    public void create(String[] validCommand) {
        String accountType = get_accountType_from_create_command(validCommand);
        String accountID = get_account_ID_from_create_command(validCommand);
        double APR = get_APR_from_create_command(validCommand);
        double amount = 0;
        if (accountType.toLowerCase(Locale.ROOT).equals("cd")) {
            amount = get_amount_from_create_command(validCommand);
        }
        bank.addAccount(accountID, accountType, APR, amount);
    }

    private String get_accountType_from_create_command(String[] command) {
        String accountType = command[1];
        return accountType;
    }

    private String get_account_ID_from_create_command(String[] command) {
        String accountID = command[2];
        return accountID;
    }

    private double get_APR_from_create_command(String[] command) {
        double APR = Double.parseDouble(command[3]);
        return APR;
    }

    private double get_amount_from_create_command(String[] command) {
        double amount = Double.parseDouble(command[4]);
        return amount;
    }

    public void deposit(String[] validCommand) {
        String accountID = get_account_ID_from_deposit_or_withdraw_command(validCommand);
        float amount = get_amount_from_deposit_or_withdraw_command(validCommand);
        bank.getAccounts().get(accountID).deposit_to_account(amount);
    }

    private float get_amount_from_deposit_or_withdraw_command(String[] command) {
        float amount = Float.parseFloat(command[2]);
        return amount;
    }

    private String get_account_ID_from_deposit_or_withdraw_command(String[] command) {
        String accountID = command[1];
        return accountID;
    }
}

