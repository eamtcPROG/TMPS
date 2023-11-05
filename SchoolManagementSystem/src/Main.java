import com.google.gson.reflect.TypeToken;
import general.datasources.xml.XMLDataSourceAdapter;
import general.datasources.xml.XMLFileHandler;
import general.interfaces.IFacade;
import general.interfaces.IRepository;
import general.interfaces.DataSource;
import general.interfaces.XMLHandler;
import general.utils.UIHelper;
import user.controllers.UserController;
import user.facade.UserManagementFacade;
import user.interfaces.IUserRepository;
import user.interfaces.IUserService;
import user.models.User;
import user.repositories.UserRepository;
import user.services.UserService;
import general.datasources.json.JsonDataSource;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import user.mediator.UserManagementMediator;  // Import the mediator

public class Main {

    public static void main(String[] args) {
        Type listType = new TypeToken<List<User>>() {}.getType();

        // Initialize JSON DataSource
        DataSource<User> jsonDataSource = new JsonDataSource<>(listType);
        IRepository<User> jsonUserRepository = UserRepository.getInstance(jsonDataSource, "../SchoolManagementSystem/resources/user/user.json");
        IUserService jsonUserService = UserService.getInstance(jsonUserRepository);
        UserController jsonUserController = new UserController(jsonUserService);




        // Initialize Facade and Mediator
        IFacade userManagementFacade = new UserManagementFacade(jsonUserController);
        UserManagementMediator mediator = new UserManagementMediator(userManagementFacade);  // Initialize the mediator

        while (true) {
            List<String> options = Arrays.asList("Add User", "Display Users", "Switch DataSource", "Exit");
            int choice = UIHelper.displayMenuAndGetChoice(options);

            switch (choice) {
                case 1:
                    mediator.createUser();  // Use the mediator
                    break;
                case 2:
                    mediator.displayUsers();  // Use the mediator
                    break;
                case 3:
                    mediator.switchDataSource();  // Use the mediator to switch data source
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
