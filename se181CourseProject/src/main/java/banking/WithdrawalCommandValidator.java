package banking;

public class WithdrawalCommandValidator {
    private static Bank bank;

    public WithdrawalCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public static boolean validate(String[] array, int arrayLength) {
        if (validateArrayLength(arrayLength)) {
            if (validateCommandPhrase(array[0])) {
                if (validateAccountExists(array[1])) {
                    if (validateAmount(array[2], array[1])) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validateArrayLength(int arrayLength) {
        if (arrayLength == 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateCommandPhrase(String commandPhrase) {
        if (commandPhrase.toLowerCase().equals("withdraw")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateAccountExists(String ID) {
        Object type = (bank.getAccounts().get(ID));

        if (type != null) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAccountType(String ID) {
        String accountType = bank.getAccounts().get(ID).getAccountType().toLowerCase();
        return accountType;
    }

    public static boolean validateAmount(String amount_input, String ID) {
        double amount = Double.parseDouble(amount_input);
        if (getAccountType(ID).equals("savings")) {
            if (amount > 1000 || amount <= 0) {
                return false;
            } else {
                if (bank.getAccounts().get(ID).getWithdrawals() == 1) {
                    return false;
                } else {
                    return true;
                }
            }
        } else if (getAccountType(ID).equals("checking")) {
            if (amount > 400 || amount <= 0) {
                return false;
            } else {
                return true;
            }
        } else if (getAccountType(ID).equals("cd")) {
            if (validateTimePassed(ID) && (amount <= (bank.getAccounts().get(ID).getBalance() + 0.01) && amount >= (bank.getAccounts().get(ID).getBalance() - 0.01))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validateTimePassed(String ID) {
        int time = bank.getAccounts().get(ID).getTimePassed();
        if (time < 12) {
            return false;
        } else {
            return true;
        }
    }

}
