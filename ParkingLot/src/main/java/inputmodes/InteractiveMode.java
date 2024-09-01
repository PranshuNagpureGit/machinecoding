package inputmodes;

import command.Command;
import command.CommandRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode extends InputMode {

    public InteractiveMode(CommandRegistry commandRegistry) {
        super(commandRegistry);
    }
    @Override
    public void process() throws IOException {
       final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       while(true) {
           String inputCommandLine = br.readLine();
           Command command = processCommand(inputCommandLine);
           if(command.isExitCommand()) {
               break;
           }
       }
    }
}
