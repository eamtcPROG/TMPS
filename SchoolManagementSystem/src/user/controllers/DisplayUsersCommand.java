package user.controllers;

import general.interfaces.Command;
import general.interfaces.IFacade;

public class DisplayUsersCommand implements Command {
    private final IFacade userManagementFacade;

    public DisplayUsersCommand(IFacade userManagementFacade) {
        this.userManagementFacade = userManagementFacade;
    }

    @Override
    public void execute() {
        userManagementFacade.display();
    }
}
