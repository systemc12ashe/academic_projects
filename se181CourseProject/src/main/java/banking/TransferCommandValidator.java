package banking;

public class TransferCommandValidator {
    private static Bank bank;

    public TransferCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public static boolean validate(String[] array, int arrayLength) {
        if (validateArrayLength(arrayLength)) {
            String transferFrom = array[1];
            String transferTo = array[2];
            double amount = Double.parseDouble(array[3]);
            if (validateCommandPhrase(array[0])) {
                if (validateAccountExists(transferFrom) && validateAccountExists(transferTo)) {
                    if (CommandValidator.validate("withdraw " + transferFrom + " " + amount) && CommandValidator.validate("deposit " + transferTo + " " + amount)) {
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
        if (arrayLength == 4) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateCommandPhrase(String commandPhrase) {
        if (commandPhrase.toLowerCase().equals("transfer")) {
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
}
