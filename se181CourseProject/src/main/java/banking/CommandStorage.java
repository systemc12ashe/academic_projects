package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
    private static List<String> invalidCommands;
    Bank bank;
    CommandValidator commandValidator;
    CreateCommandValidator createCommandValidator;
    DepositCommandValidator depositCommandValidator;
    WithdrawalCommandValidator withdrawalCommandValidator;
    TransferCommandValidator transferCommandValidator;

    CommandStorage() {
        invalidCommands = new ArrayList<>();
        bank = new Bank();
        createCommandValidator = new CreateCommandValidator(bank);
        depositCommandValidator = new DepositCommandValidator(bank);
        withdrawalCommandValidator = new WithdrawalCommandValidator(bank);
        transferCommandValidator = new TransferCommandValidator(bank);
        commandValidator = new CommandValidator(bank, createCommandValidator, depositCommandValidator, withdrawalCommandValidator, transferCommandValidator);
    }

    public static List<String> getInvalidCommands() {
        return invalidCommands;
    }

    public void addCommand(String command) {
        if (commandValidator.validate(command)) {
            String[] commandAsArray = command.split(" ");
            if (commandAsArray[0].toLowerCase().equals("deposit") || commandAsArray[0].toLowerCase().equals("withdraw")) {
                bank.getAccounts().get(commandAsArray[1]).addCommand(command);
            }
            if (commandAsArray[0].toLowerCase().equals("transfer")) {
                bank.getAccounts().get(commandAsArray[1]).addCommand(command);
                bank.getAccounts().get(commandAsArray[2]).addCommand(command);
            }
        } else {
            invalidCommands.add(command);
        }
    }

    public List<String> createOutput() {
        List<String> output = new ArrayList<String>();
        output.addAll(bank.accountOutput());
        output.addAll(invalidCommands);
        return output;
    }
}


