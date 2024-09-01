package command;

import controller.ParkingLotController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CommandRegistry {
    Set<Command> commands = new HashSet<>();

    public void register(Command command) {
        commands.add(command);
    }

    public void deRegister(Command command){
        commands.remove(command);
    }

    public Command execute(String incomingCommand) {
        for (Command command: commands) {
            if (command.matches(incomingCommand)) {
                command.execute(incomingCommand);
                return command;
            }
        }
        throw new RuntimeException("No command found for: " + incomingCommand);
    }

}
