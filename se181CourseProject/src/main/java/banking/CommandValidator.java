package banking;

public class CommandValidator {
    private static CreateCommandValidator createCommandValidator;
    private static DepositCommandValidator depositCommandValidator;
    private static WithdrawalCommandValidator withdrawalCommandValidator;
    private static TransferCommandValidator transferCommandValidator;
    private Bank bank;

    public CommandValidator(Bank bank, CreateCommandValidator createCommandValidator, DepositCommandValidator depositCommandValidator, WithdrawalCommandValidator withdrawalCommandValidator, TransferCommandValidator transferCommandValidator) {
        this.bank = bank;
        this.createCommandValidator = createCommandValidator;
        this.depositCommandValidator = depositCommandValidator;
        this.withdrawalCommandValidator = withdrawalCommandValidator;
        this.transferCommandValidator = transferCommandValidator;
    }


    public static String[] create_array_from_command(String command) {
        String[] array = command.split(" ");
        return array;
    }

    public static boolean validate(String command) {
        String[] array = create_array_from_command(command);
        int arrayLength = array.length;
        if (transferCommandValidator.validate(array, arrayLength) || withdrawalCommandValidator.validate(array, arrayLength) || PassTimeCommandValidator.validate(array, arrayLength) || createCommandValidator.validate(array, arrayLength) || depositCommandValidator.validate(array, arrayLength)) {
            return true;
        } else {
            return false;
        }
    }
}
