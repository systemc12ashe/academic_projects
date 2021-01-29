package banking;

public class CD extends Account {
    private double APR;
    private double balance;

    public CD(double APR, double balance) {
        super("CD", APR, balance);
    }
}
