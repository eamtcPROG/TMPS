import controllers.UserController;
import interfaces.IFactory;
import interfaces.Role;
import services.UserService;
import interfaces.IService;
import interfaces.IController;
import models.User;
import utils.RoleFactory;

public class Main {
    public static void main(String[] args) {
        IService<User> userService = UserService.getInstance();
        IFactory<Role> roleFactory = new RoleFactory();
        IController userController = UserController.getInstance(userService,roleFactory);


        userController.create();
        userController.display();
    }
}