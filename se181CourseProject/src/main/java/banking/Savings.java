package banking;

public class Savings extends Account {
    private double APR;
    private double balance;

    public Savings(double APR, double balance) {
        super("Savings", APR, balance);
    }
}
