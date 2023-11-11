package commands;

import interfaces.Command;
import interfaces.IController;

public class CreateUserCommand implements Command {
    private final IController controller;

    public CreateUserCommand(IController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.create();
    }
}

