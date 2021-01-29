package banking;

public class PassTimeCommandValidator {
    private Bank bank;

    public PassTimeCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public static boolean validate(String[] command, int arrayLength) {
        if (validate_length(arrayLength)) {
            if (validate_command_phrase(command[0])) {
                if (validate_amount_of_months(command[1])) {
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

    }

    public static boolean validate_length(int arrayLength) {
        if (arrayLength == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_command_phrase(String commandPhrase) {
        if (commandPhrase.toLowerCase().equals("pass")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_amount_of_months(String months) {
        int months_as_int = Integer.parseInt(months);
        if ((months_as_int > 0) && (months_as_int <= 60)) {
            return true;
        } else {
            return false;
        }
    }
}
