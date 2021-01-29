package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandStorageTest {
    public static String invalidCommandOne = "Deposittttt 12345678 100000000000";
    public static String invalidCommandTwo = "Deposit 1234567866 100000000000";
    public static String validCommandOne = "Create Checking 12345678 1";
    public static String validCommandTwo = "Create Savings 87654321 0.8";
    CommandStorage commandStorage;


    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void add_one_invalid_command() {
        commandStorage.addCommand(invalidCommandOne);
        assertEquals(invalidCommandOne, commandStorage.getInvalidCommands().get(0));
    }

    @Test
    void add_two_invalid_commands() {
        commandStorage.addCommand(invalidCommandOne);
        assertEquals(invalidCommandOne, commandStorage.getInvalidCommands().get(0));
        commandStorage.addCommand(invalidCommandTwo);
        assertEquals(invalidCommandTwo, commandStorage.getInvalidCommands().get(1));
    }

    @Test
    void add_two_invalid_commands_and_one_valid_command() {
        commandStorage.addCommand(invalidCommandOne);
        assertEquals(invalidCommandOne, commandStorage.getInvalidCommands().get(0));
        commandStorage.addCommand(invalidCommandTwo);
        assertEquals(invalidCommandTwo, commandStorage.getInvalidCommands().get(1));
        commandStorage.addCommand(validCommandOne);
        int lengthOfArray = commandStorage.getInvalidCommands().size();
        assertEquals(2, lengthOfArray);
    }
}
