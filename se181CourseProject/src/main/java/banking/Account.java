package banking;

import java.util.ArrayList;
import java.util.List;

public class Account {
    protected String accountType;
    protected double APR;
    protected double balance;
    protected int timePassed;
    protected int number_of_withdraws_in_current_month;
    private List<String> validCommands = new ArrayList<>();


    public Account(String accountType, double APR, double balance) {
        this.accountType = accountType;
        this.APR = APR;
        this.balance = balance;
    }

    static Account checking(double APR, double balance) {
        return new Account("Checking", APR, 0);
    }

    static Account savings(double APR, double balance) {
        return new Account("Savings", APR, 0);
    }

    static Account cd(double APR, double balance) {
        return new Account("CD", APR, balance);
    }

    public String getAccountType() {
        return accountType;
    }


    public double getAPR() {
        return APR;
    }

    public double getBalance() {
        return balance;
    }

    public void addTime(int time) {
        timePassed += time;
        number_of_withdraws_in_current_month = 0;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public int getWithdrawals() {
        return number_of_withdraws_in_current_month;
    }

    public double deposit_to_account(double deposit) {
        balance += deposit;
        return balance;
    }

    public double withdraw_from_account(double withdraw) {
        if (withdraw > balance) {
            balance = 0;
        } else {
            balance -= withdraw;
        }
        number_of_withdraws_in_current_month += 1;
        return balance;
    }

    public void addCommand(String command) {
        validCommands.add(command);
    }

    public List<String> getCommands() {
        return validCommands;
    }
}