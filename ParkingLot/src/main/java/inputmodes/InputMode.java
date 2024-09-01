package inputmodes;

import command.Command;
import command.CommandRegistry;

import java.io.IOException;

public abstract class InputMode {
    private CommandRegistry commandRegistry;
    public InputMode(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public Command processCommand(String command) {
        return commandRegistry.execute(command);
    }
    public abstract void process() throws IOException;
}
