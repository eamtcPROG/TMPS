import com.google.gson.reflect.TypeToken;
import general.interfaces.IRepository;
import general.utils.UIHelper;
import user.controllers.UserController;

import user.interfaces.IUserRepository;

import user.interfaces.IUserService;
import user.models.User;
import user.repositories.UserRepository;
import user.services.UserService;
import general.datasources.json.JsonDataSource;


import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Type listType = new TypeToken<List<User>>() {}.getType();
        JsonDataSource<User> dataSource = new JsonDataSource<>(listType);
//<<<<<<< HEAD
//        IRepository<User> userRepository = new UserRepository(dataSource, "../SchoolManagementSystem/resources/user/user.json");
//        IUserService userService = new UserService(userRepository);
//=======
//        UserRepository userRepository = new UserRepository(dataSource, "../SchoolManagementSystem/resources/user/user.json");
//        UserService userService = new UserService(userRepository);
        IRepository<User> userRepository = UserRepository.getInstance(dataSource, "../SchoolManagementSystem/resources/user/user.json");
        IUserService userService = UserService.getInstance(userRepository);

        UserController userController = new UserController(userService);

        while (true) {
            List<String> options = Arrays.asList("Add User", "Display Users", "Exit");
            int choice = UIHelper.displayMenuAndGetChoice(options);

            switch (choice) {
                case 1:
                    userController.create();
                    break;
                case 2:
                    userController.display();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
