package user.facade;

import general.interfaces.IController;
import general.interfaces.IFacade;

public class UserManagementFacade implements IFacade {

    private final IController userController;

    public UserManagementFacade(IController userController) {
        this.userController = userController;
    }

    @Override
    public void create() {
        userController.create();
    }

    @Override
    public void display() {
        userController.display();
    }

}