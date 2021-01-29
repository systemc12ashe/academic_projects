package banking;

public class DepositCommandValidator {
    private Bank bank;

    public DepositCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String[] command, int arrayLength) {
        String deposit = command[0].toLowerCase();

        if (validate_length(arrayLength)) {
            if (deposit.equals("deposit")) {
                if (validate_exists(command[1])) {
                    String accountType = bank.getAccounts().get(command[1]).getAccountType();
                    if (validate_accountType(accountType.toLowerCase())) {
                        if (validate_deposit_amount(accountType.toLowerCase(), Double.parseDouble(command[2]))) {
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
        } else {
            return false;
        }
    }

    public boolean validate_length(int arrayLength) {
        if (arrayLength == 3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validate_exists(String ID) {
        Object type = (bank.getAccounts().get(ID));

        if (type != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validate_accountType(String accountType) {
        if ((accountType.equals("savings")) || (accountType.equals("checking"))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validate_deposit_amount(String accountType, double amount) {
        if (accountType.equals("savings")) {
            if ((amount <= 2500) && (amount >= 0)) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((amount <= 1000) && (amount >= 0)) {
                return true;
            } else {
                return false;
            }
        }
    }

}
