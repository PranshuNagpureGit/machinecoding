import command.CommandRegistry;
import command.CreateParkingLot;
import command.GenerateTicket;
import controller.ParkingLotController;
import controller.TicketController;
import inputmodes.FileMode;
import inputmodes.InputMode;
import inputmodes.InteractiveMode;
import repository.*;
import repository.impl.*;
import service.TicketService;
import strategy.IParkingSpotAssignmentStrategy;
import strategy.RandomParkingSpotAssignment;

import java.io.IOException;

public class Application {
    private static InputMode inputMode;
    private static CommandRegistry commandRegistry;
    public static void main(String[] args) {
        initializeApplication();
        if(args.length > 0) {
            inputMode = new FileMode(commandRegistry, args[0]);
        } else {
            inputMode = new InteractiveMode(commandRegistry);
        }
        try {
            inputMode.process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeApplication() {
        ITicketRepository ticketRepository = new TicketRepository();
        IVehicleRepository vehicleRepository = new VehicleRepository();
        IGateRepository gateRepository = new GateRepository();
        IParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        IParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
        IParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy = new RandomParkingSpotAssignment();

        TicketService ticketService = new TicketService(ticketRepository,
                vehicleRepository, gateRepository, parkingLotRepository,
                parkingSpotRepository, parkingSpotAssignmentStrategy);

        ParkingLotController parkingLotController = new ParkingLotController();
        TicketController ticketController = new TicketController(ticketService);

        commandRegistry = new CommandRegistry();
        commandRegistry.register(new CreateParkingLot(parkingLotController));
        commandRegistry.register(new GenerateTicket(ticketController));
    }
}
