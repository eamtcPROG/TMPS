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

public class Main {

    public static void main(String[] args) {

        Type listType = new TypeToken<List<User>>() {}.getType();

        // For JSON
        DataSource<User> jsonDataSource = new JsonDataSource<>(listType);
        IRepository<User> jsonUserRepository = UserRepository.getInstance(jsonDataSource, "../SchoolManagementSystem/resources/user/user.json");
        IUserService jsonUserService = UserService.getInstance(jsonUserRepository);
        UserController jsonUserController = new UserController(jsonUserService);

        // For XML through Adapter
        XMLHandler xmlHandler = new XMLFileHandler();
        DataSource<User> xmlDataSource = new XMLDataSourceAdapter<>(xmlHandler, listType);
        IRepository<User> xmlUserRepository = UserRepository.getInstance(xmlDataSource, "../SchoolManagementSystem/resources/user/user.xml");
        IUserService xmlUserService = UserService.getInstance(xmlUserRepository);
        UserController xmlUserController = new UserController(xmlUserService);


        IFacade userManagementFacade = new UserManagementFacade(jsonUserController);

        while (true) {
            List<String> options = Arrays.asList("Add User", "Display Users", "Switch DataSource", "Exit");
            int choice = UIHelper.displayMenuAndGetChoice(options);

            switch (choice) {
                case 1:
                    userManagementFacade.create();
                    break;
                case 2:
                    userManagementFacade.display();
                    break;
                case 3:
                    if (jsonUserController == userManagementFacade) {
                        userManagementFacade = new UserManagementFacade(xmlUserController);
                        System.out.println("Switched to XML data source.");
                    } else {
                        userManagementFacade = new UserManagementFacade(jsonUserController);
                        System.out.println("Switched to JSON data source.");
                    }
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
