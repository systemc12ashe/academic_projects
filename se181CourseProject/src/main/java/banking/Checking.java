package banking;

public class Checking extends Account {
    private double APR;
    private double balance;

    public Checking(double APR, double balance) {
        super("Checking", APR, balance);
    }
}
