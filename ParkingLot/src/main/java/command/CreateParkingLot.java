package command;

import controller.ParkingLotController;

import java.util.Arrays;
import java.util.List;

public class CreateParkingLot implements Command {

    private static final String COMMAND_NAME = "create_parking_lot";
    private ParkingLotController parkingLotController;
    public CreateParkingLot(ParkingLotController parkingLotController) {
        this.parkingLotController = parkingLotController;
    }

    @Override
    public void execute(String command) {
        List<String> tokens = Arrays.asList(command.split(" "));
        int floors = Integer.parseInt(tokens.get(1));
        parkingLotController.createParkingLot();
    }

    @Override
    public boolean matches(String command) {
        List<String> tokens = Arrays.asList(command.split(" "));
        if(tokens.size() > 0 && tokens.get(0).equals(COMMAND_NAME)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

}
