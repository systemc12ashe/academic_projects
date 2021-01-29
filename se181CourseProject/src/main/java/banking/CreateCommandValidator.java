package banking;

public class CreateCommandValidator {
    private static Bank bank;

    public CreateCommandValidator(Bank bank) {
        this.bank = bank;
    }


    public static boolean validate(String[] command, int arrayLength) {
        if (validate_array_length(arrayLength)) {
            String create = command[0].toLowerCase();
            if (validate_command_type(create)) {
                String accountType = command[1].toLowerCase();
                if (validate_account_type(accountType)) {
                    String ID = command[2];
                    if (validate_ID(ID)) {
                        double APR = Double.parseDouble(command[3]);
                        if (validate_apr(APR)) {
                            if (arrayLength == 5) {
                                double balance = Double.parseDouble(command[4]);
                                if (validate_is_cd(accountType)) {
                                    if (validate_balance_for_cd(balance)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return true;
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
        } else {
            return false;
        }
    }

    private static boolean validate_is_cd(String accountType) {
        if (accountType.equals("cd")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_array_length(int length) {
        if (length == 4 || length == 5) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_command_type(String input) {
        if (input.equals("create")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_balance_for_cd(double balance) {
        if (balance >= 1000) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_apr(double APR) {
        if ((APR > 0) && (APR <= 10)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_account_type(String possibleAccountType) {
        if (possibleAccountType.equals("checking") || possibleAccountType.equals("savings") || possibleAccountType.equals("cd")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate_ID(String ID) {
        for (int i = 0; i < ID.length(); i++) {
            Boolean flag = Character.isDigit(ID.charAt(i));
            if (flag) {
                continue;
            } else {
                return false;
            }
        }
        if ((ID.length() == 8) && not_duplicate(ID)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean not_duplicate(String ID) {
        if (bank.getAccounts().get(ID) != null) {
            return false;
        } else {
            return true;
        }
    }
}