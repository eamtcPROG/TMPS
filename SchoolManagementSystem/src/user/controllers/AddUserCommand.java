package user.controllers;

import general.interfaces.Command;
import general.interfaces.IFacade;

public class AddUserCommand implements Command {
    private final IFacade userManagementFacade;

    public AddUserCommand(IFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }

    @Override
    public void execute() {
        userManagementFacade.create();
    }
}
