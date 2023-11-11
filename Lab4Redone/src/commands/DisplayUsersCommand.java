package commands;

import interfaces.Command;
import interfaces.IController;

public class DisplayUsersCommand implements Command {
    private final IController controller;

    public DisplayUsersCommand(IController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.display();
    }
}
