package inputmodes;

import command.Command;
import command.CommandRegistry;

import java.io.*;

public class FileMode extends InputMode{
    String fileName;
    public FileMode(CommandRegistry commandRegistry, String fileName) {
        super(commandRegistry);
        this.fileName = fileName;
    }

    @Override
    public void process() throws IOException {
        File file = new File(fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        String input = br.readLine();
        while(input != null) {
            Command command = processCommand(input);
            if(command.isExitCommand()) {
                break;
            }
        }
    }
}
